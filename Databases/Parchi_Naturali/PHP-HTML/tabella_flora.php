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
    <title>Consultazione Flora - Parchi Naturali</title>
    <style>
        @font-face {
            font-family: 'font';
            src: url('font.otf') format('opentype');
            font-weight: normal;
            font-style: normal;
        }

        body {
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

        tbody tr:hover {
            background-color: #bd8400;
            opacity: 0.8;
        }
    </style>
</head>

<body>

    <a href="dashboard.php" class="back-link">&larr; Torna alla Dashboard</a>

    <h2>
        Catalogo Flora
        <?php echo $ruolo === 'guardiaparco' ? "- " . htmlspecialchars($parco_filtro) : "(Visione Nazionale)"; ?>
    </h2>

    <div class="search-container">
        <input type="text" id="searchBar" onkeyup="filterTable()" placeholder="Cerca pianta, tipo, stagione o parco...">
    </div>

    <table>
        <thead>
            <tr>
                <th onclick="sortTable(0)" style="cursor: pointer;" title="Ordina alfabeticamente">Nome Specie
                    &#9650;&#9660;</th>
                <th onclick="sortTable(1)" style="cursor: pointer;" title="Ordina per Tipo">Tipo &#9650;&#9660;</th>
                <th onclick="sortTable(2)" style="cursor: pointer;" title="Ordina per Stagione">Stagione Fioritura
                    &#9650;&#9660;</th>
                <?php if ($ruolo === 'admin'): ?>
                    <th onclick="sortTable(3)" style="cursor: pointer;" title="Ordina per Parco">Parco di Appartenenza
                        &#9650;&#9660;</th>
                <?php endif; ?>
            </tr>
        </thead>
        <tbody>
            <?php
            if ($ruolo === 'guardiaparco') {
                $query = "SELECT SF.Nome_Specie_Flora, SF.Tipo, SF.Stagione_Fioritura 
                          FROM SPECIE_FLORA SF
                          JOIN Cresce C ON SF.Nome_Specie_Flora = C.Nome_Specie_Flora
                          WHERE C.Nome_Parco = '" . mysqli_real_escape_string($conn, $parco_filtro) . "'
                          ORDER BY SF.Nome_Specie_Flora ASC";
            } else {
                $query = "SELECT SF.Nome_Specie_Flora, SF.Tipo, SF.Stagione_Fioritura, C.Nome_Parco 
                          FROM SPECIE_FLORA SF
                          JOIN Cresce C ON SF.Nome_Specie_Flora = C.Nome_Specie_Flora
                          ORDER BY C.Nome_Parco ASC, SF.Nome_Specie_Flora ASC";
            }

            $result = mysqli_query($conn, $query);

            if (mysqli_num_rows($result) > 0) {
                while ($row = mysqli_fetch_assoc($result)) {
                    echo "<tr>";
                    echo "<td style='font-weight: bold;'>" . htmlspecialchars($row['Nome_Specie_Flora']) . "</td>";
                    echo "<td>" . htmlspecialchars($row['Tipo']) . "</td>";
                    echo "<td>" . htmlspecialchars($row['Stagione_Fioritura']) . "</td>";

                    if ($ruolo === 'admin') {
                        echo "<td>" . htmlspecialchars($row['Nome_Parco']) . "</td>";
                    }
                    echo "</tr>";
                }
            } else {
                $cols = $ruolo === 'admin' ? 4 : 3;
                echo "<tr><td colspan='$cols'>Nessuna specie botanica registrata.</td></tr>";
            }
            mysqli_close($conn);
            ?>
        </tbody>
    </table>

    <script>
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

                for (j = 0; j < td.length; j++) {
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
