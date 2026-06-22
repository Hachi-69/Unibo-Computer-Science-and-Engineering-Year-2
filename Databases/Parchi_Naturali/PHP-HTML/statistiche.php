<?php
session_start();

// Sicurezza estrema: Solo l'Admin globale può accedere alle statistiche di sistema
if (!isset($_SESSION['username']) || $_SESSION['ruolo'] !== 'admin') {
    header("Location: index.htm");
    exit();
}

$servername = "localhost";
$username_db = "root";
$password_db = "";
$dbname = "parchi_naturali";

mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

try {
    $conn = mysqli_connect($servername, $username_db, $password_db, $dbname);

    // Fetch per i menu a tendina delle query dinamiche (OP5 e OP9)
    $res_parchi = mysqli_query($conn, "SELECT Nome_Parco FROM PARCO ORDER BY Nome_Parco ASC");
    $parchi_op5 = mysqli_fetch_all($res_parchi, MYSQLI_ASSOC);
    $parchi_op9 = $parchi_op5; // Ricicliamo l'array

    $res_stagioni = mysqli_query($conn, "SELECT DISTINCT Stagione_Fioritura FROM SPECIE_FLORA ORDER BY Stagione_Fioritura ASC");

    // ==============================================================================
    // ESECUZIONE QUERY STATICHE E DINAMICHE
    // ==============================================================================

    // OP 6 - Top 3 Biodiversità
    $q6 = "SELECT Nome_Parco, COUNT(DISTINCT Nome_Specie_Fauna) AS NumSpecie 
           FROM PERMANENZA 
           WHERE Data_Fine IS NULL 
           GROUP BY Nome_Parco 
           ORDER BY NumSpecie DESC LIMIT 3";
    $res_op6 = mysqli_query($conn, $q6);

    // OP 7 - Specie a maggior rischio
    $q7 = "SELECT Totali.Nome_Specie_Fauna, (IFNULL(Critiche.Num_Critiche, 0) * 100.0) / Totali.Num_Totale AS Percentuale_Critica
           FROM (SELECT Nome_Specie_Fauna, COUNT(*) AS Num_Totale FROM VISITA_MEDICA GROUP BY Nome_Specie_Fauna) AS Totali
           JOIN (SELECT Nome_Specie_Fauna, COUNT(*) AS Num_Critiche FROM VISITA_MEDICA WHERE Esito = 'Critico' GROUP BY Nome_Specie_Fauna) AS Critiche
           ON Totali.Nome_Specie_Fauna = Critiche.Nome_Specie_Fauna
           ORDER BY Percentuale_Critica DESC LIMIT 1";
    $res_op7 = mysqli_query($conn, $q7);

    // OP 8 - Parchi sotto-sorvegliati (Aggiunto NULLIF per evitare crash su divisione per zero)
    $q8 = "SELECT P.Nome_Parco, P.Superficie, COUNT(G.Matricola) AS Num_Guardiaparco, 
           (P.Superficie / NULLIF(COUNT(G.Matricola), 0)) AS Ettari_Per_Guardia
           FROM PARCO P
           LEFT JOIN GUARDIAPARCO G ON P.Nome_Parco = G.Parco_Assegnato
           GROUP BY P.Nome_Parco
           HAVING Ettari_Per_Guardia > 500 OR Num_Guardiaparco = 0";
    $res_op8 = mysqli_query($conn, $q8);

    // OP 10 - Veterinari molto attivi (>50 visite)
    $q10 = "SELECT V.Matricola, V.Nome, V.Cognome, V.Specializzazione, COUNT(VM.Data) AS Totale_Visite 
            FROM VETERINARIO V 
            JOIN VISITA_MEDICA VM ON V.Matricola = VM.Matricola_Vet
            WHERE VM.Data >= DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR) 
            GROUP BY V.Matricola, V.Nome, V.Cognome, V.Specializzazione 
            HAVING Totale_Visite > 50 
            ORDER BY Totale_Visite DESC";
    $res_op10 = mysqli_query($conn, $q10);

} catch (Exception $e) {
    $errore_msg = "Errore di connessione o esecuzione query: " . $e->getMessage();
}
?>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <title>Analisi e Statistiche - Admin</title>
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
            font-size: 28px;
            margin-bottom: 5px;
        }

        .subtitle {
            color: #ccc;
            font-size: 14px;
            margin-bottom: 30px;
            font-family: Arial, sans-serif;
        }

        .back-link {
            color: #FFB100;
            text-decoration: none;
            display: inline-block;
            margin-bottom: 20px;
        }

        .dashboard-grid {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
            max-width: 1200px;
            margin: 0 auto;
        }

        .stat-card {
            background-color: #343331;
            border: 2px dashed #FFB100;
            border-radius: 8px;
            width: 48%;
            padding: 20px;
            box-sizing: border-box;
            text-align: left;
            display: flex;
            flex-direction: column;
        }

        .stat-card h3 {
            color: #FFB100;
            margin-top: 0;
            font-size: 18px;
            border-bottom: 1px dashed white;
            padding-bottom: 10px;
        }

        .op-badge {
            background-color: #222;
            color: #FFB100;
            padding: 3px 6px;
            border-radius: 4px;
            font-size: 12px;
            margin-right: 10px;
            border: 1px solid #FFB100;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
            background-color: #222;
            font-family: Arial, sans-serif;
            font-size: 13px;
        }

        th,
        td {
            border: 1px dashed #555;
            padding: 8px;
            text-align: center;
        }

        th {
            color: #FFB100;
            background-color: #111;
        }

        .form-inline {
            background-color: #2a2927;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 15px;
            border: 1px solid #555;
        }

        select {
            padding: 6px;
            background-color: #111;
            color: white;
            border: 1px solid #FFB100;
            border-radius: 4px;
            width: 100%;
            margin-bottom: 10px;
        }

        button {
            padding: 8px;
            background-color: #FFB100;
            color: black;
            border: none;
            font-weight: bold;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            background-color: #bd8400;
            color: white;
        }

        .no-data {
            text-align: center;
            color: #ff4d4d;
            padding: 10px;
            font-style: italic;
        }

        .highlight-data {
            font-size: 24px;
            color: white;
            text-align: center;
            margin-top: 15px;
            font-weight: bold;
        }
    </style>
</head>

<body>

    <a href="dashboard.php" class="back-link">&larr; Torna alla Dashboard Centrale</a>

    <h2>Centro Analisi e Statistiche Regionali</h2>
    <div class="subtitle">Esecuzione interrogazioni di Business Intelligence (OP5 - OP10)</div>

    <?php if (isset($errore_msg))
        echo "<div class='no-data'>$errore_msg</div>"; ?>

    <div class="dashboard-grid">

        <div class="stat-card">
            <h3><span class="op-badge">OP 6</span> Top 3 Parchi per Biodiversità Faunistica</h3>
            <table>
                <tr>
                    <th>Posizione</th>
                    <th>Nome Parco</th>
                    <th>Specie Residenti Diverse</th>
                </tr>
                <?php
                $i = 1;
                while ($row = mysqli_fetch_assoc($res_op6)) {
                    echo "<tr><td>#$i</td><td>" . htmlspecialchars($row['Nome_Parco']) . "</td><td>" . htmlspecialchars($row['NumSpecie']) . "</td></tr>";
                    $i++;
                }
                ?>
            </table>
        </div>

        <div class="stat-card">
            <h3><span class="op-badge">OP 7</span> Specie Faunistica a Maggior Rischio</h3>
            <?php
            if ($row = mysqli_fetch_assoc($res_op7)) {
                echo "<div class='highlight-data'>" . htmlspecialchars($row['Nome_Specie_Fauna']) . "</div>";
                echo "<div style='text-align:center; color:#ccc; margin-top:5px;'>Tasso visite critiche: <span style='color:#ff4d4d; font-weight:bold;'>" . number_format($row['Percentuale_Critica'], 1) . "%</span></div>";
            } else {
                echo "<div class='no-data'>Nessuna visita con esito critico registrata al momento.</div>";
            }
            ?>
        </div>

        <div class="stat-card">
            <h3><span class="op-badge">OP 8</span> Allerta: Parchi Sotto-Sorvegliati</h3>
            <p style="font-size: 12px; color: #ccc;">Soglia di allarme: > 500 ettari/guardia oppure 0 guardie assegnate.
            </p>
            <table>
                <tr>
                    <th>Parco</th>
                    <th>Ettari totali</th>
                    <th>Guardie Attive</th>
                    <th>Ettari/Guardia</th>
                </tr>
                <?php
                if (mysqli_num_rows($res_op8) > 0) {
                    while ($row = mysqli_fetch_assoc($res_op8)) {
                        $ettari = $row['Ettari_Per_Guardia'] ? number_format($row['Ettari_Per_Guardia'], 1) : "N/D (Nessuna guardia)";
                        echo "<tr><td style='color:#ff4d4d;'>" . htmlspecialchars($row['Nome_Parco']) . "</td>";
                        echo "<td>" . htmlspecialchars($row['Superficie']) . "</td>";
                        echo "<td>" . htmlspecialchars($row['Num_Guardiaparco']) . "</td>";
                        echo "<td>" . htmlspecialchars($ettari) . "</td></tr>";
                    }
                } else {
                    echo "<tr><td colspan='4'>Nessun allarme. Tutti i parchi sono coperti adeguatamente.</td></tr>";
                }
                ?>
            </table>
        </div>

        <div class="stat-card">
            <h3><span class="op-badge">OP 10</span> Veterinari Top Performer (> 50 visite/anno)</h3>
            <table>
                <tr>
                    <th>Medico</th>
                    <th>Specializzazione</th>
                    <th>Interventi Annuali</th>
                </tr>
                <?php
                if (mysqli_num_rows($res_op10) > 0) {
                    while ($row = mysqli_fetch_assoc($res_op10)) {
                        echo "<tr><td>Dr. " . htmlspecialchars($row['Nome']) . " " . htmlspecialchars($row['Cognome']) . "</td>";
                        echo "<td>" . htmlspecialchars($row['Specializzazione']) . "</td>";
                        echo "<td style='color:#28a745; font-weight:bold;'>" . htmlspecialchars($row['Totale_Visite']) . "</td></tr>";
                    }
                } else {
                    // Dato che il DB è nuovo e potresti non avere ancora medici con 50 visite, mostriamo un avviso elegante
                    echo "<tr><td colspan='3'>Nessun medico ha superato la soglia delle 50 visite cliniche nell'ultimo anno.</td></tr>";
                }
                ?>
            </table>
        </div>

        <div class="stat-card">
            <h3><span class="op-badge">OP 5</span> Filtro Flora per Parco e Stagione</h3>
            <div class="form-inline">
                <form method="GET">
                    <select name="op5_parco" required>
                        <option value="">-- Seleziona Parco --</option>
                        <?php foreach ($parchi_op5 as $p) {
                            $sel = (isset($_GET['op5_parco']) && $_GET['op5_parco'] == $p['Nome_Parco']) ? 'selected' : '';
                            echo "<option value='" . htmlspecialchars($p['Nome_Parco']) . "' $sel>" . htmlspecialchars($p['Nome_Parco']) . "</option>";
                        } ?>
                    </select>
                    <select name="op5_stagione" required>
                        <option value="">-- Seleziona Stagione Fioritura --</option>
                        <?php while ($s = mysqli_fetch_assoc($res_stagioni)) {
                            $sel = (isset($_GET['op5_stagione']) && $_GET['op5_stagione'] == $s['Stagione_Fioritura']) ? 'selected' : '';
                            echo "<option value='" . htmlspecialchars($s['Stagione_Fioritura']) . "' $sel>" . htmlspecialchars($s['Stagione_Fioritura']) . "</option>";
                        } ?>
                    </select>
                    <button type="submit">Genera Report OP 5</button>
                </form>
            </div>

            <?php
            if (isset($_GET['op5_parco']) && isset($_GET['op5_stagione'])) {
                $p5 = mysqli_real_escape_string($conn, $_GET['op5_parco']);
                $s5 = mysqli_real_escape_string($conn, $_GET['op5_stagione']);

                $q5 = "SELECT SF.Nome_Specie_Flora, SF.Tipo FROM SPECIE_FLORA SF JOIN Cresce C ON SF.Nome_Specie_Flora = C.Nome_Specie_Flora WHERE C.Nome_Parco = '$p5' AND SF.Stagione_Fioritura = '$s5'";
                $r5 = mysqli_query($conn, $q5);

                echo "<table><tr><th>Specie Botanica</th><th>Tipologia</th></tr>";
                if (mysqli_num_rows($r5) > 0) {
                    while ($row = mysqli_fetch_assoc($r5)) {
                        echo "<tr><td>" . htmlspecialchars($row['Nome_Specie_Flora']) . "</td><td>" . htmlspecialchars($row['Tipo']) . "</td></tr>";
                    }
                } else {
                    echo "<tr><td colspan='2'>Nessuna corrispondenza trovata.</td></tr>";
                }
                echo "</table>";
            }
            ?>
        </div>

        <div class="stat-card">
            <h3><span class="op-badge">OP 9</span> Fabbisogno Alimentare del Parco</h3>
            <div class="form-inline">
                <form method="GET">
                    <select name="op9_parco" required>
                        <option value="">-- Seleziona Parco per calcolo logistico --</option>
                        <?php foreach ($parchi_op9 as $p) {
                            $sel = (isset($_GET['op9_parco']) && $_GET['op9_parco'] == $p['Nome_Parco']) ? 'selected' : '';
                            echo "<option value='" . htmlspecialchars($p['Nome_Parco']) . "' $sel>" . htmlspecialchars($p['Nome_Parco']) . "</option>";
                        } ?>
                    </select>
                    <button type="submit">Genera Report Logistico OP 9</button>
                </form>
            </div>

            <?php
            if (isset($_GET['op9_parco'])) {
                $p9 = mysqli_real_escape_string($conn, $_GET['op9_parco']);

                $q9 = "SELECT DISTINCT A.Nome_Alimento, A.Categoria 
                       FROM PERMANENZA P 
                       JOIN Dieta D ON P.Nome_Specie_Fauna = D.Nome_Specie_Fauna 
                       JOIN ALIMENTO A ON D.Nome_Alimento = A.Nome_Alimento 
                       WHERE P.Nome_Parco = '$p9' AND P.Data_Fine IS NULL";
                $r9 = mysqli_query($conn, $q9);

                echo "<table><tr><th>Risorsa Alimentare Necessaria</th><th>Categoria</th></tr>";
                if (mysqli_num_rows($r9) > 0) {
                    while ($row = mysqli_fetch_assoc($r9)) {
                        echo "<tr><td style='font-weight:bold;'>" . htmlspecialchars($row['Nome_Alimento']) . "</td><td>" . htmlspecialchars($row['Categoria']) . "</td></tr>";
                    }
                } else {
                    echo "<tr><td colspan='2'>Nessun fabbisogno registrato (Il parco è vuoto o mancano le diete).</td></tr>";
                }
                echo "</table>";
            }
            ?>
        </div>

    </div>

</body>

</html>
<?php mysqli_close($conn); ?>
