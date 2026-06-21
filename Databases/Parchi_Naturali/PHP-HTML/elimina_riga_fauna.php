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

    mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

    try {
        $conn = mysqli_connect($servername, $username_db, $password_db, $dbname);

        $specie = mysqli_real_escape_string($conn, $_GET['specie']);
        $nome = mysqli_real_escape_string($conn, $_GET['nome']);

        $query_permanenza = "DELETE FROM PERMANENZA WHERE Nome_Specie_Fauna = '$specie' AND Nome_Esemplare = '$nome'";
        mysqli_query($conn, $query_permanenza);

        $query_visite = "DELETE FROM VISITA_MEDICA WHERE Nome_Specie_Fauna = '$specie' AND Nome_Esemplare = '$nome'";
        mysqli_query($conn, $query_visite);

        $query_esemplare = "DELETE FROM ESEMPLARE WHERE Nome_Specie_Fauna = '$specie' AND Nome_Esemplare = '$nome'";
        mysqli_query($conn, $query_esemplare);

        mysqli_close($conn);

        header("Location: tabella_fauna.php");
        exit();

    } catch (mysqli_sql_exception $e) {
        echo "<!DOCTYPE html><html lang='it'><head><meta charset='UTF-8'><title>Errore Database</title></head>";
        echo "<body style='background-color: #032217; color: white; text-align: center; font-family: Arial, sans-serif; padding: 50px;'>";
        echo "<h2 style='color: #FFB100;'>Errore di Sistema</h2>";
        echo "<p>Impossibile eliminare l'esemplare dal database.</p>";
        echo "<p style='color: #ff4d4d; background-color: #222; padding: 15px; border-radius: 5px; display: inline-block;'>Dettaglio tecnico: " . htmlspecialchars($e->getMessage()) . "</p>";
        echo "<br><br><a href='tabella_fauna.php' style='color: #FFB100; text-decoration: none;'>&larr; Torna al Registro Fauna</a>";
        echo "</body></html>";
        exit();
    }
} else {
    header("Location: tabella_fauna.php");
    exit();
}
?>
