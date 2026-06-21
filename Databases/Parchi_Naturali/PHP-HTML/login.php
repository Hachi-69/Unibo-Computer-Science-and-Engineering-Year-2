<?php
session_start();

$servername = "localhost";
$username_db = "root";
$password_db = "";
$dbname = "parchi_autenticazione";

$conn = mysqli_connect($servername, $username_db, $password_db, $dbname);

if (!$conn) {
    die("Connessione fallita: " . mysqli_connect_error());
}

$user_input = mysqli_real_escape_string($conn, $_POST['username']);
$pass_input = $_POST['password'];

$query = "SELECT * FROM UTENTI WHERE Username = '$user_input'";
$result = mysqli_query($conn, $query);

if (mysqli_num_rows($result) == 1) {
    $row = mysqli_fetch_assoc($result);

    if (password_verify($pass_input, $row['Password'])) {

        $_SESSION['username'] = $row['Username'];
        $_SESSION['ruolo'] = $row['Ruolo'];
        $_SESSION['matricola'] = $row['Matricola_Dipendente'];

        header("Location: dashboard.php");
        exit();

    } else {
        header("Location: credenzialiNonValide.htm");
        exit();
    }
} else {
    header("Location: credenzialiNonValide.htm");
    exit();
}

mysqli_close($conn);
?>
