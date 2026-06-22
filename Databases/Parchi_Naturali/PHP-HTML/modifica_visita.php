<?php
session_start();

if (!isset($_SESSION['username']) || $_SESSION['ruolo'] === 'guardiaparco') {
    header("Location: index.htm");
    exit();
}

$servername = "localhost";
$username_db = "root";
$password_db = "";
$dbname = "parchi_naturali";

mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

$matricola_vet_get = $_GET['vet'];
$specie_get = $_GET['specie'];
$nome_get = $_GET['nome'];
$data_get = $_GET['data'];
$ora_get = $_GET['ora'];

try {
    $conn = mysqli_connect($servername, $username_db, $password_db, $dbname);

    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $esito_post = mysqli_real_escape_string($conn, $_POST['esito']);
        $terapia_post = mysqli_real_escape_string($conn, $_POST['terapia']);
        $nuovo_stato = mysqli_real_escape_string($conn, $_POST['nuovo_stato']);

        mysqli_begin_transaction($conn);

        try {
            $query_update_visita = "UPDATE VISITA_MEDICA 
                                    SET Esito = '$esito_post', Terapia_Prescritta = '$terapia_post' 
                                    WHERE Matricola_Vet = '$matricola_vet_get' 
                                    AND Nome_Specie_Fauna = '$specie_get' 
                                    AND Nome_Esemplare = '$nome_get' 
                                    AND Data = '$data_get' 
                                    AND Ora = '$ora_get'";
            mysqli_query($conn, $query_update_visita);

            $query_update_esemplare = "UPDATE ESEMPLARE 
                                       SET Stato_Salute = '$nuovo_stato' 
                                       WHERE Nome_Specie_Fauna = '$specie_get' AND Nome_Esemplare = '$nome_get'";
            mysqli_query($conn, $query_update_esemplare);

            mysqli_commit($conn);

            header("Location: registro_visite.php");
            exit();

        } catch (mysqli_sql_exception $e) {
            mysqli_rollback($conn);
            throw new Exception("Errore durante il salvataggio dei dati clinici: " . $e->getMessage());
        }
    }

    $query_select_visita = "SELECT V.*, E.Stato_Salute, VET.Nome, VET.Cognome 
                            FROM VISITA_MEDICA V
                            JOIN ESEMPLARE E ON V.Nome_Specie_Fauna = E.Nome_Specie_Fauna AND V.Nome_Esemplare = E.Nome_Esemplare
                            JOIN VETERINARIO VET ON V.Matricola_Vet = VET.Matricola
                            WHERE V.Matricola_Vet = '$matricola_vet_get' 
                            AND V.Nome_Specie_Fauna = '$specie_get' 
                            AND V.Nome_Esemplare = '$nome_get' 
                            AND V.Data = '$data_get' 
                            AND V.Ora = '$ora_get'";

    $res_select = mysqli_query($conn, $query_select_visita);
    $visita = mysqli_fetch_assoc($res_select);

    if (!$visita) {
        throw new Exception("Referto clinico non trovato nel database.");
    }

} catch (Exception $e) {
    $errore_msg = $e->getMessage();
}
?>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <title>Modifica Visita Medica</title>
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

        input[disabled] {
            background-color: #555;
            color: #aaa;
            cursor: not-allowed;
        }

        textarea {
            resize: vertical;
            min-height: 90px;
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
        <h2>Rettifica Cartella Clinica</h2>

        <?php if (isset($errore_msg))
            echo "<div class='error-msg'>$errore_msg</div>"; ?>

        <form method="POST">

            <label>Medico Veterinario (Immutabile)</label>
            <input type="text" value="<?php echo htmlspecialchars($visita['Nome'] . ' ' . $visita['Cognome'] . ' - ' . $matricola_vet_get . ''); ?>" disabled />

            <label>Paziente (Immutabile)</label>
            <input type="text" value="<?php echo htmlspecialchars($specie_get . ' - ' . $nome_get); ?>" disabled />

            <label>Data e Ora della Visita (Immutabile)</label>
            <input type="text" value="<?php echo htmlspecialchars($data_get . ' alle ore ' . $ora_get); ?>" disabled />

            <hr>

            <label>Stato di Salute Attuale dell'Esemplare</label>
            <select name="nuovo_stato" required>
                <option value="Ottimo" <?php if ($visita['Stato_Salute'] === 'Ottimo')
                    echo 'selected'; ?>>Ottimo
                </option>
                <option value="Buono" <?php if ($visita['Stato_Salute'] === 'Buono')
                    echo 'selected'; ?>>Buono</option>
                <option value="Sotto osservazione" <?php if ($visita['Stato_Salute'] === 'Sotto osservazione')
                    echo 'selected'; ?>>Sotto osservazione</option>
                <option value="In convalescenza" <?php if ($visita['Stato_Salute'] === 'In convalescenza')
                    echo 'selected'; ?>>In convalescenza</option>
                <option value="Critico" <?php if ($visita['Stato_Salute'] === 'Critico')
                    echo 'selected'; ?>>Critico
                </option>
            </select>

            <label>Esito Visita / Diagnosi aggiornata</label>
            <textarea name="esito" required><?php echo htmlspecialchars($visita['Esito']); ?></textarea>

            <label>Terapia Prescritta aggiornata</label>
            <textarea name="terapia"><?php echo htmlspecialchars($visita['Terapia_Prescritta']); ?></textarea>

            <button type="submit" class="btn-submit">Salva Modifiche Referto</button>
        </form>
    </div>

</body>

</html>
<?php
if (isset($conn) && $conn) {
    mysqli_close($conn);
}
?>
