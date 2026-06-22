<?php
session_start();

if (!isset($_SESSION['username']) || $_SESSION['ruolo'] === 'veterinario') {
    header("Location: index.htm");
    exit();
}

$ruolo = $_SESSION['ruolo'];
$matricola = $_SESSION['matricola'];

$servername = "localhost";
$username_db = "root";
$password_db = "";
$dbname = "parchi_naturali";

$conn = mysqli_connect($servername, $username_db, $password_db, $dbname);
if (!$conn) {
    die("Connessione fallita: " . mysqli_connect_error());
}

$parco_assegnato = "";

if ($ruolo === 'guardiaparco') {
    $q_parco = "SELECT Parco_Assegnato FROM GUARDIAPARCO WHERE Matricola = '$matricola'";
    $res_parco = mysqli_query($conn, $q_parco);
    if ($row_parco = mysqli_fetch_assoc($res_parco)) {
        $parco_assegnato = $row_parco['Parco_Assegnato'];
    }
}

$oggi = date('Y-m-d');
$max_data_ingresso = date('Y-m-d', strtotime('+7 days'));

if ($_SERVER['REQUEST_METHOD'] === 'POST') {

    $specie = mysqli_real_escape_string($conn, $_POST['specie']);
    $nome = mysqli_real_escape_string($conn, $_POST['nome']);
    $sesso = mysqli_real_escape_string($conn, $_POST['sesso']);
    $nascita = mysqli_real_escape_string($conn, $_POST['data_nascita']);
    $salute = mysqli_real_escape_string($conn, $_POST['stato_salute']);

    $parco_destinazione = ($ruolo === 'admin') ? mysqli_real_escape_string($conn, $_POST['parco_destinazione']) : mysqli_real_escape_string($conn, $parco_assegnato);
    $data_ingresso = mysqli_real_escape_string($conn, $_POST['data_ingresso']);
    $modalita = mysqli_real_escape_string($conn, $_POST['modalita_ingresso']);

    try {
        if ($nascita > $oggi) {
            throw new Exception("Errore: Le date non possono essere nel futuro.");
        }

        if ($data_ingresso < $nascita) {
            throw new Exception("Errore: L'animale non può entrare nel parco prima di essere nato.");
        }

        $q_insert_esemplare = "INSERT INTO ESEMPLARE (Nome_Specie_Fauna, Nome_Esemplare, Sesso, Data_Nascita, Stato_Salute, Totale_Visite_Subite) 
                               VALUES ('$specie', '$nome', '$sesso', '$nascita', '$salute', 0)";

        if (!mysqli_query($conn, $q_insert_esemplare)) {
            throw new Exception("Errore nell'inserimento dell'anagrafica.");
        }

        $q_insert_permanenza = "INSERT INTO PERMANENZA (Nome_Parco, Nome_Specie_Fauna, Nome_Esemplare, Data_Inizio, Data_Fine, Modalita_Ingresso) 
                                VALUES ('$parco_destinazione', '$specie', '$nome', '$data_ingresso', NULL, '$modalita')";

        if (!mysqli_query($conn, $q_insert_permanenza)) {
            mysqli_query($conn, "DELETE FROM ESEMPLARE WHERE Nome_Specie_Fauna = '$specie' AND Nome_Esemplare = '$nome'");
            throw new Exception("Errore nell'assegnazione al parco. L'inserimento è stato annullato.");
        }

        header("Location: tabella_fauna.php");
        exit();

    } catch (Exception $e) {
        $errore_msg = $e->getMessage();
    }
}

$res_specie = mysqli_query($conn, "SELECT Nome_Specie_Fauna FROM SPECIE_FAUNA ORDER BY Nome_Specie_Fauna ASC");
$res_parchi = mysqli_query($conn, "SELECT Nome_Parco FROM PARCO ORDER BY Nome_Parco ASC");
?>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <title>Nuovo Esemplare</title>
    <style>
        @font-face {
            font-family: 'font';
            src: url('font.otf') format('opentype');
            font-weight: normal;
            font-style: normal;
        }

        body {
            zoom: 1.15;
            background-color: #032217;
            color: white;
            font-family: 'font', Arial, sans-serif;
            padding: 20px;
            text-align: center;
        }

        .form-box {
            background-color: #343331;
            border: 2px dashed white;
            max-width: 550px;
            margin: 30px auto;
            padding: 30px;
            border-radius: 8px;
            text-align: left;
        }

        h2 {
            color: #FFB100;
            text-align: center;
            margin-bottom: 25px;
        }

        .back-link {
            color: #FFB100;
            text-decoration: none;
            display: inline-block;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-top: 15px;
            color: #FFB100;
            font-size: 14px;
        }

        input[type="text"],
        input[type="date"],
        select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            background-color: #222;
            color: white;
            border: 1px dashed white;
            box-sizing: border-box;
        }

        .btn-submit {
            background-color: #FFB100;
            color: black;
            border: none;
            padding: 12px;
            width: 100%;
            margin-top: 30px;
            cursor: pointer;
            border-radius: 4px;
            font-size: 16px;
        }

        .btn-submit:hover {
            background-color: #9e6f00;
        }

        .error-msg {
            background-color: #ff4d4d;
            color: white;
            padding: 10px;
            border-radius: 4px;
            text-align: center;
            margin-bottom: 15px;
        }

        hr {
            border: 0;
            border-top: 1px dashed #FFB100;
            margin: 25px 0;
        }

        .section-title {
            text-align: center;
            color: #FFB100;
            font-size: 18px;
            margin-bottom: 10px;
        }
    </style>
</head>

<body>

    <a href="tabella_fauna.php" class="back-link">&larr; Annulla e torna al Registro Fauna</a>

    <div class="form-box">
        <h2>Registrazione Nuovo Esemplare</h2>

        <?php if (isset($errore_msg))
            echo "<div class='error-msg'>$errore_msg</div>"; ?>

        <form method="POST">

            <div class="section-title">Anagrafica Biologica</div>

            <label>Specie Animale</label>
            <select name="specie" required>
                <option value="">-- Seleziona Specie --</option>
                <?php while ($s = mysqli_fetch_assoc($res_specie)) {
                    echo "<option value=\"" . htmlspecialchars($s['Nome_Specie_Fauna']) . "\">" . htmlspecialchars($s['Nome_Specie_Fauna']) . "</option>";
                } ?>
            </select>

            <label>Nome Identificativo o Codice Tag</label>
            <input type="text" name="nome" placeholder="Es. Amarena, AQU-05, ecc." required />

            <label>Sesso</label>
            <select name="sesso" required>
                <option value="M">Maschio</option>
                <option value="F">Femmina</option>
            </select>

            <label>Data di Nascita</label>
            <input type="date" name="data_nascita" max="<?php echo $oggi; ?>" required />

            <label>Stato di Salute all'arrivo</label>
            <select name="stato_salute" required>
                <option value="Ottimo">Ottimo</option>
                <option value="Buono">Buono</option>
                <option value="Sotto osservazione">Sotto osservazione</option>
                <option value="In convalescenza">In convalescenza</option>
                <option value="Critico">Critico</option>
            </select>

            <hr>
            <div class="section-title">Dati di Collocamento (Permanenza)</div>

            <?php if ($ruolo === 'admin'): ?>
                <label>Parco di Assegnazione</label>
                <select name="parco_destinazione" required>
                    <option value="">-- Seleziona Parco --</option>
                    <?php while ($p = mysqli_fetch_assoc($res_parchi)) {
                        echo "<option value=\"" . htmlspecialchars($p['Nome_Parco']) . "\">" . htmlspecialchars($p['Nome_Parco']) . "</option>";
                    } ?>
                </select>
            <?php else: ?>
                <label>Parco di Assegnazione</label>
                <input type="text" value="<?php echo htmlspecialchars($parco_assegnato); ?>" disabled />
            <?php endif; ?>

            <label>Data di Ingresso nel Parco</label>
            <input type="date" name="data_ingresso" max="<?php echo $oggi; ?>" required />

            <label>Modalità di Ingresso</label>
            <select name="modalita_ingresso" required>
                <option value="Nascita in loco">Nascita in loco</option>
                <option value="Reintroduzione">Reintroduzione</option>
                <option value="Avvistamento spontaneo">Avvistamento spontaneo</option>
                <option value="Sconfinamento spontaneo">Sconfinamento spontaneo</option>
                <option value="Cattura e ricollocamento">Cattura e ricollocamento</option>
                <option value="Salvataggio in natura">Salvataggio in natura</option>
            </select>

            <button type="submit" class="btn-submit">Registra Esemplare nel Database</button>
        </form>
    </div>

</body>

</html>
<?php mysqli_close($conn); ?>
