<?php
session_start();

if (!isset($_SESSION['username']) || $_SESSION['ruolo'] === 'guardiaparco') {
    header("Location: index.htm");
    exit();
}

if (
    isset($_GET['vet']) &&
    isset($_GET['specie']) &&
    isset($_GET['nome']) &&
    isset($_GET['data']) &&
    isset($_GET['ora'])
) {
    $servername = "localhost";
    $username_db = "root";
    $password_db = "";
    $dbname = "parchi_naturali";

    mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

    try {
        $conn = mysqli_connect($servername, $username_db, $password_db, $dbname);

        $matricola_vet = mysqli_real_escape_string($conn, $_GET['vet']);
        $specie = mysqli_real_escape_string($conn, $_GET['specie']);
        $nome = mysqli_real_escape_string($conn, $_GET['nome']);
        $data = mysqli_real_escape_string($conn, $_GET['data']);
        $ora = mysqli_real_escape_string($conn, $_GET['ora']);

        mysqli_begin_transaction($conn);

        try {
            $query_delete = "DELETE FROM VISITA_MEDICA 
                             WHERE Matricola_Vet = '$matricola_vet' 
                             AND Nome_Specie_Fauna = '$specie' 
                             AND Nome_Esemplare = '$nome' 
                             AND Data = '$data' 
                             AND Ora = '$ora'";
            mysqli_query($conn, $query_delete);

            $query_decrement = "UPDATE ESEMPLARE 
                                SET Totale_Visite_Subite = GREATEST(0, Totale_Visite_Subite - 1) 
                                WHERE Nome_Specie_Fauna = '$specie' AND Nome_Esemplare = '$nome'";
            mysqli_query($conn, $query_decrement);

            mysqli_commit($conn);

            mysqli_close($conn);

            header("Location: registro_visite.php");
            exit();

        } catch (mysqli_sql_exception $e) {
            mysqli_rollback($conn);
            throw $e;
        }

    } catch (Exception $e) {
        echo "<!DOCTYPE html><html lang='it'><head><meta charset='UTF-8'><title>Errore Database</title></head>";
        echo "<body style='zoom: 1.15; background-color: #032217; color: white; text-align: center; font-family: Arial, sans-serif; padding: 50px;'>";
        echo "<h2 style='color: #FFB100;'>Errore di Sistema</h2>";
        echo "<p>Impossibile eliminare la visita medica selezionata.</p>";
        echo "<p style='color: #ff4d4d; background-color: #222; padding: 15px; border-radius: 5px; display: inline-block;'>Dettaglio tecnico: " . htmlspecialchars($e->getMessage()) . "</p>";
        echo "<br><br><a href='registro_visite.php' style='color: #FFB100; text-decoration: none;'>&larr; Torna al Registro Visite</a>";
        echo "</body></html>";
        exit();
    }
} else {
    header("Location: registro_visite.php");
    exit();
}
?>
