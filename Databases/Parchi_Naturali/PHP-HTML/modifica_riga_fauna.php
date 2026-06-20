<?php
session_start();
if (!isset($_SESSION['username'])) {
    header("Location: index.htm");
    exit();
}

$servername = "localhost";
$username_db = "root";
$password_db = "";
$dbname = "parchi_naturali";

$conn = mysqli_connect($servername, $username_db, $password_db, $dbname);
if (!$conn) { die("Connessione fallita: " . mysqli_connect_error()); }

$specie_get = mysqli_real_escape_string($conn, $_GET['specie']);
$nome_get = mysqli_real_escape_string($conn, $_GET['nome']);

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $sesso_post = mysqli_real_escape_string($conn, $_POST['sesso']);
    $salute_post = mysqli_real_escape_string($conn, $_POST['stato_salute']);

    $query_update = "UPDATE ESEMPLARE 
                     SET Sesso = '$sesso_post', Stato_Salute = '$salute_post' 
                     WHERE Nome_Specie_Fauna = '$specie_get' AND Nome_Esemplare = '$nome_get'";
    
    if (mysqli_query($conn, $query_update)) {
        header("Location: tabella_fauna.php");
        exit();
    } else {
        echo "<script>alert('Errore durante l\'aggiornamento del record.');</script>";
    }
}

// Caricamento dati attuali per popolare il form
$query_select = "SELECT * FROM ESEMPLARE WHERE Nome_Specie_Fauna = '$specie_get' AND Nome_Esemplare = '$nome_get'";
$res_select = mysqli_query($conn, $query_select);
$esemplare = mysqli_fetch_assoc($res_select);
?>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Modifica Esemplare</title>
    <style>
        body { background-color: #032217; color: white; font-family: Arial, sans-serif; padding: 20px; text-align: center; }
        .form-box { background-color: #343331; border: 2px dashed white; max-width: 500px; margin: 50px auto; padding: 30px; border-radius: 8px; text-align: left; }
        h2 { color: #FFB100; text-align: center; }
        .back-link { color: #FFB100; text-decoration: none; display: inline-block; margin-bottom: 20px; }
        label { display: block; margin-top: 15px; color: #FFB100; font-weight: bold; }
        input[type="text"], select { width: 100%; padding: 10px; margin-top: 5px; background-color: #222; color: white; border: 1px dashed white; box-sizing: border-box; }
        input[disabled] { background-color: #555; color: #aaa; cursor: not-allowed; }
        .btn-submit { background-color: #FFB100; color: black; border: none; padding: 12px; width: 100%; margin-top: 25px; font-weight: bold; cursor: pointer; border-radius: 4px; }
        .btn-submit:hover { background-color: #9e6f00; }
    </style>
</head>
<body>

    <a href="tabella_fauna.php" class="back-link">&larr; Annulla e torna indietro</a>

    <div class="form-box">
        <h2>Modifica Dati Esemplare</h2>
        <form method="POST">
            
            <label>Specie (Immutabile)</label>
            <input type="text" value="<?php echo htmlspecialchars($esemplare['Nome_Specie_Fauna']); ?>" disabled />

            <label>Nome Identificativo (Immutabile)</label>
            <input type="text" value="<?php echo htmlspecialchars($esemplare['Nome_Esemplare']); ?>" disabled />

            <label>Sesso</label>
            <select name="sesso">
                <option value="M" <?php if($esemplare['Sesso'] == 'M') echo 'selected'; ?>>Maschio</option>
                <option value="F" <?php if($esemplare['Sesso'] == 'F') echo 'selected'; ?>>Femmina</option>
            </select>

            <label>Stato di Salute</label>
            <select name="stato_salute">
                <option value="Ottimo" <?php if($esemplare['Stato_Salute'] == 'Ottimo') echo 'selected'; ?>>Ottimo</option>
                <option value="Buono" <?php if($esemplare['Stato_Salute'] == 'Buono') echo 'selected'; ?>>Buono</option>
                <option value="Sotto osservazione" <?php if($esemplare['Stato_Salute'] == 'Sotto osservazione') echo 'selected'; ?>>Sotto osservazione</option>
                <option value="In convalescenza" <?php if($esemplare['Stato_Salute'] == 'In convalescenza') echo 'selected'; ?>>In convalescenza</option>
                <option value="Critico" <?php if($esemplare['Stato_Salute'] == 'Critico') echo 'selected'; ?>>Critico</option>
            </select>

            <button type="submit" class="btn-submit">Salva Modifiche</button>
        </form>
    </div>

</body>
</html>
<?php mysqli_close($conn); ?>