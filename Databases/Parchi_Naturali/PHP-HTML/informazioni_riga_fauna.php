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

$specie = mysqli_real_escape_string($conn, $_GET['specie']);
$nome = mysqli_real_escape_string($conn, $_GET['nome']);

$query_esemplare = "SELECT * FROM ESEMPLARE WHERE Nome_Specie_Fauna = '$specie' AND Nome_Esemplare = '$nome'";
$res_esemplare = mysqli_query($conn, $query_esemplare);
$esemplare = mysqli_fetch_assoc($res_esemplare);

if (!$esemplare) {
    die("Esemplare non trovato.");
}
?>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Scheda Esemplare - <?php echo htmlspecialchars($nome); ?></title>
    <style>
        body { background-color: #032217; color: white; font-family: Arial, sans-serif; padding: 20px; text-align: center; }
        .scheda { background-color: #343331; border: 2px dashed white; max-width: 700px; margin: 30px auto; padding: 20px; border-radius: 8px; text-align: left; }
        h2, h3 { color: #FFB100; text-align: center; }
        .back-link { color: #FFB100; text-decoration: none; display: inline-block; margin-bottom: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; background-color: #222; }
        th, td { border: 1px dashed #555; padding: 8px; text-align: left; font-size: 14px; }
        th { color: #FFB100; }
        .info-group { margin-bottom: 10px; font-size: 16px; }
        .info-label { color: #FFB100; font-weight: bold; }
    </style>
</head>
<body>

    <a href="tabella_fauna.php" class="back-link">&larr; Torna al Registro Fauna</a>

    <div class="scheda">
        <h2>Fascicolo Esemplare: <?php echo htmlspecialchars($nome); ?></h2>
        
        <div class="info-group"><span class="info-label">Specie:</span> <?php echo htmlspecialchars($esemplare['Nome_Specie_Fauna']); ?></div>
        <div class="info-group"><span class="info-label">Sesso:</span> <?php echo $esemplare['Sesso'] == 'M' ? 'Maschio' : 'Femmina'; ?></div>
        <div class="info-group"><span class="info-label">Data di Nascita:</span> <?php echo htmlspecialchars($esemplare['Data_Nascita']); ?></div>
        <div class="info-group"><span class="info-label">Stato di Salute Corrente:</span> <?php echo htmlspecialchars($esemplare['Stato_Salute']); ?></div>
        <div class="info-group"><span class="info-label">Visite Cliniche Totali Subite:</span> <?php echo htmlspecialchars($esemplare['Totale_Visite_Subite']); ?></div>

        <h3>Storico Residenze e Spostamenti</h3>
        <table>
            <thead>
                <tr><th>Parco</th><th>Dal</th><th>Al</th><th>Modalità Ingresso</th></tr>
            </thead>
            <tbody>
                <?php
                $q_perm = "SELECT * FROM PERMANENZA WHERE Nome_Specie_Fauna = '$specie' AND Nome_Esemplare = '$nome' ORDER BY Data_Inizio DESC";
                $r_perm = mysqli_query($conn, $q_perm);
                while($p = mysqli_fetch_assoc($r_perm)) {
                    $fine = $p['Data_Fine'] ? $p['Data_Fine'] : "<b>In corso (Attuale)</b>";
                    echo "<tr><td>{$p['Nome_Parco']}</td><td>{$p['Data_Inizio']}</td><td>{$fine}</td><td>{$p['Modalita_Ingresso']}</td></tr>";
                }
                ?>
            </tbody>
        </table>

        <h3>Cartella Clinica Veterinaria</h3>
        <table>
            <thead>
                <tr><th>Data</th><th>Veterinario</th><th>Esito Visita</th><th>Terapia Prescritta</th></tr>
            </thead>
            <tbody>
                <?php
                $q_vis = "SELECT * FROM VISITA_MEDICA WHERE Nome_Specie_Fauna = '$specie' AND Nome_Esemplare = '$nome' ORDER BY Data DESC";
                $r_vis = mysqli_query($conn, $q_vis);
                if(mysqli_num_rows($r_vis) > 0) {
                    while($v = mysqli_fetch_assoc($r_vis)) {
                        echo "<tr><td>{$v['Data']}</td><td>{$v['Matricola_Vet']}</td><td>{$v['Esito']}</td><td>{$v['Terapia_Prescritta']}</td></tr>";
                    }
                } else {
                    echo "<tr><td colspan='4'>Nessuna visita medica registrata per questo esemplare.</td></tr>";
                }
                ?>
            </tbody>
        </table>
    </div>

</body>
</html>
<?php mysqli_close($conn); ?>
