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

$conn = mysqli_connect($servername, $username_db, $password_db, $dbname);
if (!$conn) {
    die("Connessione fallita: " . mysqli_connect_error());
}
?>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <title>Piani Dietetici - Parchi Naturali</title>
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

        tbody tr:hover {
            background-color: #bd8400;
            opacity: 0.8;
        }

        .badge-eta {
            padding: 4px 8px;
            border-radius: 4px;
            font-size: 12px;
            background-color: #555;
            color: #FFB100;
        }
    </style>
</head>

<body>

    <a href="dashboard.php" class="back-link">&larr; Torna alla Dashboard</a>

    <h2>Consultazione Piani Dietetici Faunistici</h2>

    <div class="search-container">
        <input type="text" id="searchBar" onkeyup="filterTable()"
            placeholder="Cerca per specie, fascia d'età, alimento o categoria...">
    </div>

    <table>
        <thead>
            <tr>
                <th onclick="sortTable(0)" style="cursor: pointer;" title="Ordina per Specie">Specie Faunistica
                    &#9650;&#9660;</th>
                <th onclick="sortTable(1)" style="cursor: pointer;" title="Ordina per Fascia d'Età">Fascia d'Età
                    &#9650;&#9660;</th>
                <th onclick="sortTable(2)" style="cursor: pointer;" title="Ordina per Alimento">Alimento
                    &#9650;&#9660;</th>
                <th onclick="sortTable(3)" style="cursor: pointer;" title="Ordina per Categoria">Categoria Nutrizionale
                    &#9650;&#9660;</th>
            </tr>
        </thead>
        <tbody>
            <?php
            $query = "SELECT D.Nome_Specie_Fauna, D.Fascia_Eta, A.Nome_Alimento, A.Categoria 
                      FROM Dieta D
                      JOIN ALIMENTO A ON D.Nome_Alimento = A.Nome_Alimento
                      ORDER BY D.Nome_Specie_Fauna ASC, D.Fascia_Eta ASC, A.Categoria ASC";

            $result = mysqli_query($conn, $query);

            if (mysqli_num_rows($result) > 0) {
                while ($row = mysqli_fetch_assoc($result)) {
                    echo "<tr>";
                    echo "<td>" . htmlspecialchars($row['Nome_Specie_Fauna']) . "</td>";
                    echo "<td>" . htmlspecialchars($row['Fascia_Eta']) . "</td>";
                    echo "<td>" . htmlspecialchars($row['Nome_Alimento']) . "</td>";
                    echo "<td>" . htmlspecialchars($row['Categoria']) . "</td>";
                    echo "</tr>";
                }
            } else {
                echo "<tr><td colspan='4'>Nessun piano dietetico registrato nel database.</td></tr>";
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
