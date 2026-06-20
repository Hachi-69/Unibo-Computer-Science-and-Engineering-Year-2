<?php
session_start();

if (!isset($_SESSION['username']) || $_SESSION['ruolo'] === 'veterinario') {
    header("Location: index.htm");
    exit();
}

if (isset($_GET['specie']) && isset($_GET['nome'])) {
    $servername = "localhost";
    $username_db = "root";
    $password_db = "";
    $dbname = "parchi_naturali";

    $conn = mysqli_connect($servername, $username_db, $password_db, $dbname);
    if (!$conn) {
        die("Connessione fallita: " . mysqli_connect_error());
    }

    $specie = mysqli_real_escape_string($conn, $_GET['specie']);
    $nome = mysqli_real_escape_string($conn, $_GET['nome']);

    $query = "DELETE FROM ESEMPLARE WHERE Nome_Specie_Fauna = '$specie' AND Nome_Esemplare = '$nome'";
    
    mysqli_query($conn, $query);
    mysqli_close($conn);
}

header("Location: tabella_fauna.php");
exit();
?>
