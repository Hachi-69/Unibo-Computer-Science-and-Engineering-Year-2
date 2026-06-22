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
        @font-face {
            font-family: 'font';
            src: url('font.otf') format('opentype');
            font-weight: normal;
            font-style: normal;
        }

        body {
            zoom: 1.15;
            background-color: #032217;
            color: #ffffff;
            text-align: center;
            font-family: 'font', Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }

        h2 {
            color: #FFB100;
        }

        .back-link {
            color: #FFB100;
            text-decoration: none;
            display: inline-block;
            margin-bottom: 20px;
        }

        .search-container {
            width: 80%;
            margin: 10px auto 20px auto;
            text-align: left;
        }

        #searchBar {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            background-color: #222;
            color: white;
            border: 1px dashed white;
            border-radius: 4px;
            box-sizing: border-box;
            font-family: 'font', Arial, sans-serif;
        }

        #searchBar:focus {
            outline: none;
            border-color: #FFB100;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #343331;
        }

        th,
        td {
            border: 1px dashed white;
            padding: 12px;
            text-align: center;
        }

        th {
            color: #FFB100;
            background-color: #222;
        }

        button {
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .btn-update {
            background-color: #FFB100;
            color: black;
        }

        .btn-update:hover {
            background-color: black;
            color: #FFB100;
        }

        .btn-delete {
            background-color: #ff4d4d;
            color: white;
        }

        .btn-delete:hover {
            background-color: white;
            color: #ff4d4d;
        }

        .btn-info {
            background-color: #4da6ff;
            color: white;
        }

        .btn-info:hover {
            background-color: white;
            color: #4da6ff;
        }

        tbody tr:hover {
            background-color: #bd8400;
            opacity: 0.8;
        }
    </style>
</head>

<body>

    <a href="dashboard.php" class="back-link">&larr; Torna alla Dashboard</a>

    <h2>
        Visualizzazione Registro Fauna
        <?php echo $ruolo === 'guardiaparco' ? "- " . htmlspecialchars($parco_filtro) : "(Visione Nazionale)"; ?>
    </h2>

    <div style="margin-bottom: 20px;">
        <button class="btn-update" style="padding: 10px 20px; font-size: 16px;"
            onclick="window.location.href='inserisci_fauna.php'">
            + Registra Nuovo Esemplare
        </button>
    </div>

    <div class="search-container">
        <input type="text" id="searchBar" onkeyup="filterTable()"
            placeholder="Cerca per specie, nome, sesso, stato di salute o parco attuale...">
    </div>

    <table>
        <thead>
            <tr>
                <th onclick="sortTable(0)" style="cursor: pointer;" title="Ordina per Specie">Specie &#9650;&#9660;</th>
                <th onclick="sortTable(1)" style="cursor: pointer;" title="Ordina per Nome Esemplare">Nome Esemplare
                    &#9650;&#9660;</th>
                <th onclick="sortTable(2)" style="cursor: pointer;" title="Ordina per Sesso">Sesso &#9650;&#9660;</th>
                <th onclick="sortTable(3)" style="cursor: pointer;" title="Ordina per Stato Salute">Stato Salute
                    &#9650;&#9660;</th>
                <?php if ($ruolo === 'admin'): ?>
                    <th onclick="sortTable(4)" style="cursor: pointer;" title="Ordina per Parco Attuale">Parco Attuale
                        &#9650;&#9660;</th>
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

        function filterTable() {
            var input, filter, table, tr, td, i, j, txtValue, found;
            input = document.getElementById("searchBar");
            filter = input.value.toLowerCase();
            table = document.querySelector("table");
            tr = table.getElementsByTagName("tr");

            for (i = 1; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td");

                if (td.length === 1 && td[0].colSpan > 1) continue;

                found = false;

                for (j = 0; j < td.length - 1; j++) {
                    if (td[j]) {
                        txtValue = td[j].textContent || td[j].innerText;
                        if (txtValue.toLowerCase().indexOf(filter) > -1) {
                            found = true;
                            break;
                        }
                    }
                }

                if (found) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }

        function sortTable(n) {
            var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
            table = document.querySelector("table");
            switching = true;
            dir = "asc";

            while (switching) {
                switching = false;
                rows = table.rows;

                for (i = 1; i < (rows.length - 1); i++) {
                    shouldSwitch = false;

                    x = rows[i].getElementsByTagName("TD")[n];
                    y = rows[i + 1].getElementsByTagName("TD")[n];

                    if (!x || !y || x.colSpan > 1) continue;

                    if (dir == "asc") {
                        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                            shouldSwitch = true;
                            break;
                        }
                    } else if (dir == "desc") {
                        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                            shouldSwitch = true;
                            break;
                        }
                    }
                }
                if (shouldSwitch) {
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    switching = true;
                    switchcount++;
                } else {
                    if (switchcount == 0 && dir == "asc") {
                        dir = "desc";
                        switching = true;
                    }
                }
            }
        }
    </script>
</body>

</html>
