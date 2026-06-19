<?php
session_start();

if (!isset($_SESSION['username'])) {
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

$parco_filtro = "";

if ($ruolo === 'guardiaparco') {
    $query_parco = "SELECT Parco_Assegnato FROM GUARDIAPARCO WHERE Matricola = '$matricola'";
    $res_parco = mysqli_query($conn, $query_parco);
    if ($row_parco = mysqli_fetch_assoc($res_parco)) {
        $parco_filtro = $row_parco['Parco_Assegnato'];
    }
}
?>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Gestione Fauna - Parchi Naturali</title>
    <style>
        body {
            background-color: #032217;
            color: #ffffff;
            text-align: center;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }
        h2 { color: #FFB100; }
        .back-link {
            color: #FFB100;
            text-decoration: none;
            display: inline-block;
            margin-bottom: 20px;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #343331;
        }
        th, td {
            border: 1px dashed white;
            padding: 12px;
            text-align: center;
        }
        th { color: #FFB100; background-color: #222; }
        button {
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }
        .btn-update { background-color: #FFB100; color: black; }
        .btn-delete { background-color: #ff4d4d; color: white; }
        .btn-info { background-color: #4da6ff; color: white; }
    </style>
</head>
<body>

    <a href="dashboard.php" class="back-link">&larr; Torna alla Dashboard</a>

    <h2>
        Visualizzazione Registro Fauna 
        <?php echo $ruolo === 'guardiaparco' ? "- " . htmlspecialchars($parco_filtro) : "(Visione Nazionale)"; ?>
    </h2>

    <table>
        <thead>
            <tr>
                <th>Specie</th>
                <th>Nome Esemplare</th>
                <th>Sesso</th>
                <th>Stato Salute</th>
                <?php if ($ruolo === 'admin'): ?>
                    <th>Parco Attuale</th>
                <?php endif; ?>
                <th>Azioni</th>
            </tr>
        </thead>
        <tbody>
            <?php
            if ($ruolo === 'guardiaparco') {
                $query = "SELECT E.Nome_Specie_Fauna, E.Nome_Esemplare, E.Sesso, E.Stato_Salute 
                          FROM ESEMPLARE E 
                          JOIN PERMANENZA P ON E.Nome_Specie_Fauna = P.Nome_Specie_Fauna AND E.Nome_Esemplare = P.Nome_Esemplare 
                          WHERE P.Nome_Parco = '" . mysqli_real_escape_string($conn, $parco_filtro) . "'
                          AND P.Data_Fine IS NULL";
            } else {
                $query = "SELECT E.Nome_Specie_Fauna, E.Nome_Esemplare, E.Sesso, E.Stato_Salute, P.Nome_Parco 
                          FROM ESEMPLARE E 
                          LEFT JOIN PERMANENZA P ON E.Nome_Specie_Fauna = P.Nome_Specie_Fauna AND E.Nome_Esemplare = P.Nome_Esemplare AND P.Data_Fine IS NULL";
            }

            $result = mysqli_query($conn, $query);

            if (mysqli_num_rows($result) > 0) {
                while ($row = mysqli_fetch_assoc($result)) {
                    $specie_js = addslashes($row['Nome_Specie_Fauna']);
                    $nome_js = addslashes($row['Nome_Esemplare']);

                    echo "<tr>";
                    echo "<td>" . htmlspecialchars($row['Nome_Specie_Fauna']) . "</td>";
                    echo "<td>" . htmlspecialchars($row['Nome_Esemplare']) . "</td>";
                    echo "<td>" . ($row['Sesso'] === 'M' ? 'Maschio' : 'Femmina') . "</td>";
                    echo "<td>" . htmlspecialchars($row['Stato_Salute']) . "</td>";
                    
                    if ($ruolo === 'admin') {
                        echo "<td>" . htmlspecialchars($row['Nome_Parco'] ?? 'Non assegnato') . "</td>";
                    }

                    echo "<td>";
                    echo "<button class='btn-info' onclick=\"infoFauna('$specie_js', '$nome_js')\">Info</button> ";
                    echo "<button class='btn-update' onclick=\"modificaFauna('$specie_js', '$nome_js')\">Modifica</button> ";
                    echo "<button class='btn-delete' onclick=\"eliminaFauna('$specie_js', '$nome_js')\">Elimina</button>";
                    echo "</td>";
                    echo "</tr>";
                }
            } else {
                $cols = $ruolo === 'admin' ? 6 : 5;
                echo "<tr><td colspan='$cols'>Nessun esemplare trovato.</td></tr>";
            }
            mysqli_close($conn);
            ?>
        </tbody>
    </table>

    <script>
        function eliminaFauna(specie, nome) {
            if (confirm("Sei sicuro di voler eliminare l'esemplare " + nome + " (" + specie + ")?")) {
                window.location.href = 'elimina_riga_fauna.php?specie=' + encodeURIComponent(specie) + '&nome=' + encodeURIComponent(nome);
            }
        }

        function modificaFauna(specie, nome) {
            window.location.href = 'modifica_riga_fauna.php?specie=' + encodeURIComponent(specie) + '&nome=' + encodeURIComponent(nome);
        }

        function infoFauna(specie, nome) {
            window.location.href = 'informazioni_riga_fauna.php?specie=' + encodeURIComponent(specie) + '&nome=' + encodeURIComponent(nome);
        }
    </script>
</body>
</html>
