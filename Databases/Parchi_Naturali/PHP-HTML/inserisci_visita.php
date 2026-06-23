<?php
session_start();

if (!isset($_SESSION['username']) || $_SESSION['ruolo'] === 'guardiaparco') {
    header("Location: index.htm");
    exit();
}

$ruolo = $_SESSION['ruolo'];
$matricola_loggata = $_SESSION['matricola'];

$servername = "localhost";
$username_db = "root";
$password_db = "";
$dbname = "parchi_naturali";

mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

$oggi = date('Y-m-d');
$ora_attuale = date('H:i');

try {
    $conn = mysqli_connect($servername, $username_db, $password_db, $dbname);

    if ($_SERVER['REQUEST_METHOD'] === 'POST') {

        $esemplare_val = $_POST['esemplare'];
        list($specie_post, $nome_post) = explode('|', $esemplare_val);

        $specie = mysqli_real_escape_string($conn, $specie_post);
        $nome = mysqli_real_escape_string($conn, $nome_post);

        $matricola_vet = ($ruolo === 'admin') ? mysqli_real_escape_string($conn, $_POST['veterinario']) : $matricola_loggata;
        $data = mysqli_real_escape_string($conn, $_POST['data']);
        $ora = mysqli_real_escape_string($conn, $_POST['ora']);
        $esito = mysqli_real_escape_string($conn, $_POST['esito']);
        $terapia = mysqli_real_escape_string($conn, $_POST['terapia']);
        $nuovo_stato = mysqli_real_escape_string($conn, $_POST['nuovo_stato']);

        if ($data > $oggi) {
            throw new Exception("Non puoi registrare una visita nel futuro.");
        }

        $q_nascita = "SELECT Data_Nascita FROM ESEMPLARE WHERE Nome_Specie_Fauna = '$specie' AND Nome_Esemplare = '$nome'";
        $res_nascita = mysqli_query($conn, $q_nascita);
        $row_nascita = mysqli_fetch_assoc($res_nascita);

        if ($row_nascita && $data < $row_nascita['Data_Nascita']) {
            throw new Exception("Errore: La data della visita ($data) non può essere precedente alla data di nascita dell'animale (" . $row_nascita['Data_Nascita'] . ").");
        }

        $q_attivo = "SELECT Data_Inizio FROM PERMANENZA WHERE Nome_Specie_Fauna = '$specie' AND Nome_Esemplare = '$nome' AND Data_Fine IS NULL";
        $res_attivo = mysqli_query($conn, $q_attivo);
        if (mysqli_num_rows($res_attivo) === 0) {
            throw new Exception("Errore Logico: Non puoi visitare un animale deceduto o rilasciato in natura (nessuna permanenza attiva trovata).");
        }

        mysqli_begin_transaction($conn);

        try {
            $query_insert = "INSERT INTO VISITA_MEDICA (Matricola_Vet, Nome_Specie_Fauna, Nome_Esemplare, Data, Ora, Esito, Terapia_Prescritta)
                             VALUES ('$matricola_vet', '$specie', '$nome', '$data', '$ora', '$esito', '$terapia')";
            mysqli_query($conn, $query_insert);

            $query_update = "UPDATE ESEMPLARE 
                             SET Stato_Salute = '$nuovo_stato', 
                                 Totale_Visite_Subite = Totale_Visite_Subite + 1
                             WHERE Nome_Specie_Fauna = '$specie' AND Nome_Esemplare = '$nome'";
            mysqli_query($conn, $query_update);

            mysqli_commit($conn);

            header("Location: registro_visite.php");
            exit();

        } catch (mysqli_sql_exception $e) {
            mysqli_rollback($conn);

            if (strpos($e->getMessage(), 'Duplicate entry') !== false) {
                throw new Exception("Esiste già una visita registrata per questo animale in questa precisa Data e Ora.");
            } else {
                throw new Exception("Errore del Database: " . $e->getMessage());
            }
        }
    }

    $query_esemplari = "SELECT E.Nome_Specie_Fauna, E.Nome_Esemplare, E.Data_Nascita 
                        FROM ESEMPLARE E
                        JOIN PERMANENZA P ON E.Nome_Specie_Fauna = P.Nome_Specie_Fauna AND E.Nome_Esemplare = P.Nome_Esemplare
                        WHERE P.Data_Fine IS NULL
                        ORDER BY E.Nome_Specie_Fauna ASC, E.Nome_Esemplare ASC";
    $res_esemplari = mysqli_query($conn, $query_esemplari);

    $query_vets = "SELECT Matricola, Nome, Cognome FROM VETERINARIO ORDER BY Cognome ASC, Nome ASC";
    $res_vets = mysqli_query($conn, $query_vets);

} catch (Exception $e) {
    $errore_msg = $e->getMessage();
}
?>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <title>Nuova Visita Medica</title>
    <style>
        body {
            zoom: 1.15;
            background-color: #032217;
            color: white;
            font-family: Arial, sans-serif;
            padding: 20px;
            text-align: center;
        }

        .form-box {
            background-color: #343331;
            border: 2px dashed white;
            max-width: 600px;
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
        input[type="time"],
        select,
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            background-color: #222;
            color: white;
            border: 1px dashed white;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
            min-height: 80px;
        }

        .btn-submit {
            background-color: #FFB100;
            color: black;
            border: none;
            padding: 12px;
            width: 100%;
            margin-top: 25px;
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
    </style>
</head>

<body>

    <a href="registro_visite.php" class="back-link">&larr; Annulla e torna al Registro</a>

    <div class="form-box">
        <h2>Registrazione Nuova Visita Medica</h2>

        <?php if (isset($errore_msg))
            echo "<div class='error-msg'>$errore_msg</div>"; ?>

        <form method="POST">

            <?php if ($ruolo === 'admin'): ?>
                <label>Seleziona Medico Veterinario</label>
                <select name="veterinario" required>
                    <option value="">-- Seleziona Veterinario --</option>
                    <?php
                    if (isset($res_vets)) {
                        while ($v = mysqli_fetch_assoc($res_vets)) {
                            echo "<option value='" . htmlspecialchars($v['Matricola']) . "'>" . htmlspecialchars($v['Matricola']) . " - " . htmlspecialchars($v['Cognome']) . " " . htmlspecialchars($v['Nome']) . "</option>";
                        }
                    }
                    ?>
                </select>
            <?php else: ?>
                <input type="hidden" name="veterinario" value="<?php echo htmlspecialchars($matricola_loggata); ?>">
            <?php endif; ?>

            <label>Seleziona Paziente</label>
            <select name="esemplare" id="tendina_esemplare" onchange="impostaDataMinima()" required>
                <option value="">-- Scegli Specie ed Esemplare --</option>
                <?php
                if (isset($res_esemplari)) {
                    mysqli_data_seek($res_esemplari, 0);
                    while ($e = mysqli_fetch_assoc($res_esemplari)) {
                        $value = $e['Nome_Specie_Fauna'] . "|" . $e['Nome_Esemplare'];
                        $label = htmlspecialchars($e['Nome_Specie_Fauna'] . " - " . $e['Nome_Esemplare']);
                        $nascita = htmlspecialchars($e['Data_Nascita']);

                        $q_stato = "SELECT Stato_Salute FROM ESEMPLARE WHERE Nome_Specie_Fauna = '" . mysqli_real_escape_string($conn, $e['Nome_Specie_Fauna']) . "' AND Nome_Esemplare = '" . mysqli_real_escape_string($conn, $e['Nome_Esemplare']) . "'";
                        $res_stato = mysqli_query($conn, $q_stato);
                        $row_stato = mysqli_fetch_assoc($res_stato);
                        $stato = htmlspecialchars($row_stato['Stato_Salute'] ?? 'Buono');
                        echo "<option value=\"$value\" data-nascita=\"$nascita\" data-stato=\"$stato\">$label</option>";
                    }
                }
                ?>
            </select>

            <label>Data Visita</label>
            <input type="date" name="data" id="campo_data" max="<?php echo $oggi; ?>" value="<?php echo $oggi; ?>"
                required />

            <label>Ora Visita</label>
            <input type="time" name="ora" value="<?php echo $ora_attuale; ?>" required />

            <hr>

            <label>Nuovo Stato di Salute</label>
            <select name="nuovo_stato" required>
                <option value="Ottimo">Ottimo</option>
                <option value="Buono" selected>Buono</option>
                <option value="Sotto osservazione">Sotto osservazione</option>
                <option value="In convalescenza">In convalescenza</option>
                <option value="Critico">Critico</option>
            </select>

            <label>Esito Visita / Diagnosi</label>
            <textarea name="esito" placeholder="Descrivi i sintomi o il risultato del controllo di routine..."
                required></textarea>

            <label>Terapia Prescritta</label>
            <textarea name="terapia"
                placeholder="Scrivi 'Nessuna' se in salute, altrimenti indica farmaci o trattamenti..."></textarea>

            <button type="submit" class="btn-submit">Registra Visita e Aggiorna Paziente</button>
        </form>
    </div>

    <script>
        function impostaDataMinima() {
            var tendina = document.getElementById('tendina_esemplare');
            var campoData = document.getElementById('campo_data');

            var opzioneSelezionata = tendina.options[tendina.selectedIndex];

            if (opzioneSelezionata && opzioneSelezionata.getAttribute('data-nascita')) {
                var dataNascita = opzioneSelezionata.getAttribute('data-nascita');
                campoData.min = dataNascita;

                if (campoData.value < dataNascita) {
                    campoData.value = "<?php echo $oggi; ?>";
                }
            } else {
                campoData.min = '';
            }

            aggiornaStatoSalute();
        }

        function aggiornaStatoSalute() {
            var tendina = document.getElementById('tendina_esemplare');
            var selectStatoSalute = document.querySelector('select[name="nuovo_stato"]');

            var opzioneSelezionata = tendina.options[tendina.selectedIndex];

            if (opzioneSelezionata && opzioneSelezionata.getAttribute('data-stato')) {
                var statoSalute = opzioneSelezionata.getAttribute('data-stato');
                selectStatoSalute.value = statoSalute;
            } else {
                selectStatoSalute.value = 'Buono';
            }
        }
    </script>
</body>

</html>
<?php
if (isset($conn) && $conn) {
    mysqli_close($conn);
}
?>
