<?php
session_start();

if (!isset($_SESSION['username']) || $_SESSION['ruolo'] === 'veterinario') {
    header("Location: index.htm");
    exit();
}

$servername = "localhost";
$username_db = "root";
$password_db = "";
$dbname = "parchi_naturali";

$conn = mysqli_connect($servername, $username_db, $password_db, $dbname);
if (!$conn) {
    die("Connessione fallita: " . mysqli_connect_error());
}

$specie_get = mysqli_real_escape_string($conn, $_GET['specie']);
$nome_get = mysqli_real_escape_string($conn, $_GET['nome']);

$oggi = date('Y-m-d');
$max_data_evento = date('Y-m-d', strtotime('+7 days'));

try {
    $query_select = "SELECT * FROM ESEMPLARE WHERE Nome_Specie_Fauna = '$specie_get' AND Nome_Esemplare = '$nome_get'";
    $res_select = mysqli_query($conn, $query_select);
    $esemplare = mysqli_fetch_assoc($res_select);

    $data_nascita = isset($esemplare['Data_Nascita']) ? $esemplare['Data_Nascita'] : $oggi;

    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $sesso_post = mysqli_real_escape_string($conn, $_POST['sesso']);
        $salute_post = mysqli_real_escape_string($conn, $_POST['stato_salute']);

        $query_update = "UPDATE ESEMPLARE 
                        SET Sesso = '$sesso_post', Stato_Salute = '$salute_post' 
                        WHERE Nome_Specie_Fauna = '$specie_get' AND Nome_Esemplare = '$nome_get'";
        mysqli_query($conn, $query_update);

        if (!empty($_POST['azione_uscita']) && !empty($_POST['data_evento'])) {
            $azione_uscita = mysqli_real_escape_string($conn, $_POST['azione_uscita']);
            $data_evento = mysqli_real_escape_string($conn, $_POST['data_evento']);

            if ($data_evento > $max_data_evento) {
                throw new Exception("Non puoi registrare un evento oltre 7 giorni nel futuro.");
            }

            $q_check = "SELECT Data_Inizio, Nome_Parco FROM PERMANENZA 
                        WHERE Nome_Specie_Fauna = '$specie_get' AND Nome_Esemplare = '$nome_get' AND Data_Fine IS NULL";
            $res_check = mysqli_query($conn, $q_check);
            $curr_perm = mysqli_fetch_assoc($res_check);

            if ($curr_perm) {
                if ($data_evento < $curr_perm['Data_Inizio']) {
                    throw new Exception("Errore: La data dell'evento ($data_evento) non può essere precedente alla data in cui è arrivato qui (" . $curr_perm['Data_Inizio'] . ").");
                }

                // OP 4 - Registrazione dello spostamento di un esemplare da un parco ad un'altro. \\
                $query_close = "UPDATE PERMANENZA
                                SET Data_Fine = '$data_evento' 
                                WHERE Nome_Specie_Fauna = '$specie_get' AND Nome_Esemplare = '$nome_get' AND Data_Fine IS NULL";
                mysqli_query($conn, $query_close);
                // OP 4 - Registrazione dello spostamento di un esemplare da un parco ad un'altro. \\
                
                if ($azione_uscita === 'Trasferimento') {
                    $nuovo_parco = mysqli_real_escape_string($conn, $_POST['nuovo_parco']);
                    $modalita = mysqli_real_escape_string($conn, $_POST['modalita_ingresso']);

                    if ($curr_perm['Nome_Parco'] === $nuovo_parco) {
                        throw new Exception("L'animale si trova già nel $nuovo_parco! Seleziona una destinazione diversa.");
                    }

                    $query_insert = "INSERT INTO PERMANENZA (Nome_Parco, Nome_Specie_Fauna, Nome_Esemplare, Data_Inizio, Data_Fine, Modalita_Ingresso) 
                                    VALUES ('$nuovo_parco', '$specie_get', '$nome_get', '$data_evento', NULL, '$modalita')";
                    mysqli_query($conn, $query_insert);

                } elseif ($azione_uscita === 'Decesso') {
                    $query_decesso = "UPDATE ESEMPLARE 
                                      SET Stato_Salute = 'Deceduto' 
                                      WHERE Nome_Specie_Fauna = '$specie_get' AND Nome_Esemplare = '$nome_get'";
                    mysqli_query($conn, $query_decesso);
                }
            } else {
                throw new Exception("L'animale non risulta avere una permanenza attiva in alcun parco da poter chiudere, probabilmente è già stato rilasciato oppure deceduto.");
            }
        }

        header("Location: tabella_fauna.php");
        exit();
    }

    $query_parchi = "SELECT Nome_Parco FROM PARCO ORDER BY Nome_Parco ASC";
    $res_parchi = mysqli_query($conn, $query_parchi);

} catch (Exception $e) {
    $errore_msg = $e->getMessage();

    if (!isset($esemplare))
        $esemplare = ['Nome_Specie_Fauna' => $specie_get, 'Nome_Esemplare' => $nome_get, 'Sesso' => 'M', 'Stato_Salute' => 'Ottimo', 'Data_Nascita' => $oggi];
}
?>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <title>Modifica Esemplare</title>
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
            max-width: 500px;
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

        input[disabled] {
            background-color: #555;
            color: #aaa;
            cursor: not-allowed;
        }

        hr {
            border: 0;
            border-top: 1px dashed #FFB100;
            margin: 30px 0;
        }

        .transfer-section {
            background-color: #2a2927;
            padding: 15px 20px;
            border-radius: 6px;
            border: 1px solid #555;
        }

        .transfer-section h3 {
            color: #FFB100;
            margin-top: 0;
            margin-bottom: 5px;
            text-align: center;
            font-size: 18px;
        }

        .help-text {
            font-size: 12px;
            color: #bbb;
            text-align: center;
            margin-bottom: 15px;
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

        .hidden-fields {
            display: none;
        }
    </style>
</head>

<body>

    <a href="tabella_fauna.php" class="back-link">&larr; Annulla e torna indietro</a>

    <div class="form-box">
        <h2>Modifica Dati Esemplare</h2>

        <?php if (isset($errore_msg))
            echo "<div class='error-msg'>$errore_msg</div>"; ?>

        <form method="POST">

            <label>Specie (Immutabile)</label>
            <input type="text" value="<?php echo htmlspecialchars($esemplare['Nome_Specie_Fauna']); ?>" disabled />

            <label>Nome Identificativo (Immutabile)</label>
            <input type="text" value="<?php echo htmlspecialchars($esemplare['Nome_Esemplare']); ?>" disabled />

            <label>Sesso</label>
            <select name="sesso">
                <option value="M" <?php if ($esemplare['Sesso'] == 'M')
                    echo 'selected'; ?>>Maschio</option>
                <option value="F" <?php if ($esemplare['Sesso'] == 'F')
                    echo 'selected'; ?>>Femmina</option>
            </select>

            <label>Data di Nascita (Immutabile)</label>
            <input type="date" name="data_nascita" max="<?php echo $oggi; ?>"
                value="<?php echo htmlspecialchars($esemplare['Data_Nascita']); ?>" disabled />

            <label>Stato di Salute</label>
            <select name="stato_salute">
                <option value="Ottimo" <?php if ($esemplare['Stato_Salute'] == 'Ottimo')
                    echo 'selected'; ?>>Ottimo
                </option>
                <option value="Buono" <?php if ($esemplare['Stato_Salute'] == 'Buono')
                    echo 'selected'; ?>>Buono</option>
                <option value="Sotto osservazione" <?php if ($esemplare['Stato_Salute'] == 'Sotto osservazione')
                    echo 'selected'; ?>>Sotto osservazione</option>
                <option value="In convalescenza" <?php if ($esemplare['Stato_Salute'] == 'In convalescenza')
                    echo 'selected'; ?>>In convalescenza</option>
                <option value="Critico" <?php if ($esemplare['Stato_Salute'] == 'Critico')
                    echo 'selected'; ?>>Critico
                </option>
                <?php if ($esemplare['Stato_Salute'] == 'Deceduto'): ?>
                    <option value="Deceduto" selected>Deceduto</option>
                <?php endif; ?>
            </select>

            <hr>

            <div class="transfer-section">

                <h3>Fine Permanenza (Opzionale)</h3>
                <div class="help-text">Compila questa sezione solo se l'animale sta lasciando il parco attuale, viene
                    liberato o è deceduto.</div>

                <label>Motivo di Uscita dal Parco</label>
                <select name="azione_uscita" id="azione_uscita" onchange="gestisciCampiUscita()">
                    <option value="">-- Nessuna Uscita --</option>
                    <option value="Trasferimento">Trasferimento in altro parco</option>
                    <option value="Rilascio">Rilascio definitivo in natura</option>
                    <option value="Decesso">Decesso dell'animale</option>
                </select>

                <div id="campi_data" class="hidden-fields">
                    <label>Data dell'Evento</label>
                    <input type="date" name="data_evento" id="data_evento" max="<?php echo $max_data_evento; ?>"
                        min="<?php echo $data_nascita; ?>" />
                </div>

                <div id="campi_trasferimento" class="hidden-fields">
                    <label>Nuovo Parco di Destinazione</label>
                    <select name="nuovo_parco" id="nuovo_parco">
                        <option value="">-- Seleziona parco --</option>
                        <?php
                        if (isset($res_parchi) && $res_parchi) {
                            while ($p = mysqli_fetch_assoc($res_parchi)) {
                                echo "<option value=\"" . htmlspecialchars($p['Nome_Parco']) . "\">" . htmlspecialchars($p['Nome_Parco']) . "</option>";
                            }
                        }
                        ?>
                    </select>

                    <label>Modalità di Ingresso nel nuovo parco</label>
                    <select name="modalita_ingresso" id="modalita_ingresso">
                        <option value="">-- Seleziona una modalità --</option>
                        <option value="Trasferimento da altro parco">Trasferimento da altro parco</option>
                        <option value="Scambio genetico inter-parco">Scambio genetico inter-parco</option>
                    </select>
                </div>
            </div>

            <button type="submit" class="btn-submit">Salva Modifiche</button>
        </form>

        <script>
            function gestisciCampiUscita() {
                const azione = document.getElementById('azione_uscita').value;
                const divData = document.getElementById('campi_data');
                const divTrasf = document.getElementById('campi_trasferimento');

                if (azione === '') {
                    divData.style.display = 'none';
                    divTrasf.style.display = 'none';
                } else if (azione === 'Rilascio' || azione === 'Decesso') {
                    divData.style.display = 'block';
                    divTrasf.style.display = 'none';
                } else if (azione === 'Trasferimento') {
                    divData.style.display = 'block';
                    divTrasf.style.display = 'block';
                }
            }

            document.querySelector('form').addEventListener('submit', function (e) {
                const azioneUscita = document.getElementById('azione_uscita').value;

                if (azioneUscita !== '') {
                    const dataEvento = document.getElementById('data_evento').value.trim();
                    if (!dataEvento) {
                        alert('La "Data dell\'Evento" è obbligatoria per registrare un\'uscita.');
                        e.preventDefault();
                        return;
                    }

                    if (azioneUscita === 'Trasferimento') {
                        const nuovoParco = document.getElementById('nuovo_parco').value.trim();
                        const modalitaIngresso = document.getElementById('modalita_ingresso').value.trim();
                        if (!nuovoParco) {
                            alert('Il campo "Nuovo Parco di Destinazione" è obbligatorio per un trasferimento.');
                            e.preventDefault();
                            return;
                        }
                        if (!modalitaIngresso) {
                            alert('Il campo "Modalità di Ingresso" è obbligatorio per un trasferimento.');
                            e.preventDefault();
                            return;
                        }
                    }
                }
            });
        </script>
    </div>

</body>

</html>
<?php mysqli_close($conn); ?>
