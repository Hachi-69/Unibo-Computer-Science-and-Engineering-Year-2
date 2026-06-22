<?php
session_start();

if (!isset($_SESSION['username']) || $_SESSION['ruolo'] === 'guardiaparco') {
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
?>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <title>Registro Visite Mediche - Parchi Naturali</title>
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
            width: 90%;
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
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #343331;
        }

        th,
        td {
            border: 1px dashed white;
            padding: 10px;
            text-align: center;
            font-size: 14px;
        }

        th {
            color: #FFB100;
            background-color: #222;
        }

        tbody tr:hover {
            background-color: #bd8400;
            opacity: 0.8;
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
    </style>
</head>

<body>

    <a href="dashboard.php" class="back-link">&larr; Torna alla Dashboard</a>

    <h2>Registro Clinico Veterinario</h2>

    <div style="margin-bottom: 20px;">
        <button class="btn-update" style="padding: 10px 20px; font-size: 16px;"
            onclick="window.location.href='inserisci_visita.php'">
            + Registra Nuova Visita Medica
        </button>
    </div>

    <div class="search-container">
        <input type="text" id="searchBar" onkeyup="filterTable()"
            placeholder="Cerca per data, animale, esito, terapia, stato salute o veterinario...">
    </div>

    <table>
        <thead>
            <tr>
                <th onclick="sortTable(0)" style="cursor: pointer;">Data e Ora &#9650;&#9660;</th>
                <th onclick="sortTable(1)" style="cursor: pointer;">Paziente (Specie e Nome) &#9650;&#9660;</th>
                <th onclick="sortTable(2)" style="cursor: pointer;">Esito Visita &#9650;&#9660;</th>
                <th onclick="sortTable(3)" style="cursor: pointer;">Terapia Prescritta &#9650;&#9660;</th>
                <th onclick="sortTable(4)" style="cursor: pointer;">Stato Salute Attuale &#9650;&#9660;</th>
                <th onclick="sortTable(5)" style="cursor: pointer;">Veterinario &#9650;&#9660;</th>
                <th>Azioni</th>
            </tr>
        </thead>
        <tbody>
            <?php
            $query = "SELECT V.Data, V.Ora, V.Nome_Specie_Fauna, V.Nome_Esemplare, V.Esito, V.Terapia_Prescritta, V.Matricola_Vet, E.Stato_Salute, VET.Nome, VET.Cognome
                      FROM VISITA_MEDICA V
                      JOIN ESEMPLARE E ON V.Nome_Specie_Fauna = E.Nome_Specie_Fauna AND V.Nome_Esemplare = E.Nome_Esemplare
                      JOIN VETERINARIO VET ON V.Matricola_Vet = VET.Matricola
                      ORDER BY V.Data DESC, V.Ora DESC";

            $result = mysqli_query($conn, $query);

            if (mysqli_num_rows($result) > 0) {
                while ($row = mysqli_fetch_assoc($result)) {
                    $vet_js = addslashes($row['Matricola_Vet']);
                    $specie_js = addslashes($row['Nome_Specie_Fauna']);
                    $nome_js = addslashes($row['Nome_Esemplare']);
                    $data_js = addslashes($row['Data']);
                    $ora_js = addslashes($row['Ora']);

                    echo "<tr>";
                    echo "<td>" . htmlspecialchars($row['Data']) . " <br><span style='font-size: 12px; color: #ccc;'>" . htmlspecialchars($row['Ora']) . "</span></td>";
                    echo "<td>" . htmlspecialchars($row['Nome_Esemplare']) . "<br><span style='font-size: 12px; color: #ccc;'>" . htmlspecialchars($row['Nome_Specie_Fauna']) . "</span></td>";
                    echo "<td>" . htmlspecialchars($row['Esito']) . "</td>";
                    echo "<td>" . htmlspecialchars($row['Terapia_Prescritta']) . "</td>";
                    echo "<td>" . htmlspecialchars($row['Stato_Salute']) . "</td>";
                    echo "<td>" . htmlspecialchars($row['Cognome']) . " " . htmlspecialchars($row['Nome']) . "<br><span style='font-size: 12px; color: #ccc;'>" . htmlspecialchars($row['Matricola_Vet']) . "</span></td>";

                    echo "<td>";
                    echo "<button class='btn-update' onclick=\"modificaVisita('$vet_js', '$specie_js', '$nome_js', '$data_js', '$ora_js')\">Modifica</button> ";
                    echo "<button class='btn-delete' onclick=\"eliminaVisita('$vet_js', '$specie_js', '$nome_js', '$data_js', '$ora_js')\">Elimina</button>";
                    echo "</td>";
                    echo "</tr>";
                }
            } else {
                echo "<tr><td colspan='7'>Nessuna visita medica registrata nel database.</td></tr>";
            }
            mysqli_close($conn);
            ?>
        </tbody>
    </table>

    <script>
        function eliminaVisita(vet, specie, nome, data, ora) {
            if (confirm("Sei sicuro di voler eliminare la visita di " + nome + " del " + data + "?")) {
                window.location.href = 'elimina_visita.php?vet=' + encodeURIComponent(vet) + '&specie=' + encodeURIComponent(specie) + '&nome=' + encodeURIComponent(nome) + '&data=' + encodeURIComponent(data) + '&ora=' + encodeURIComponent(ora);
            }
        }

        function modificaVisita(vet, specie, nome, data, ora) {
            window.location.href = 'modifica_visita.php?vet=' + encodeURIComponent(vet) + '&specie=' + encodeURIComponent(specie) + '&nome=' + encodeURIComponent(nome) + '&data=' + encodeURIComponent(data) + '&ora=' + encodeURIComponent(ora);
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
