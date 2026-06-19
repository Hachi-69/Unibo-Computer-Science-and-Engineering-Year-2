<?php
session_start();

if (!isset($_SESSION['username'])) {
    header("Location: index.htm");
    exit();
}

$username = $_SESSION['username'];
$ruolo = $_SESSION['ruolo'];
$matricola = $_SESSION['matricola'];
?>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Parchi Naturali - Dashboard</title>
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
            margin: 0;
            padding: 0;
            font-family: 'font', Arial, sans-serif;
        }

        .header-panel {
            background-color: #343331;
            padding: 15px;
            border-bottom: 2px dashed white;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header-panel h3 {
            margin: 0;
            color: #FFB100;
        }

        .logout-btn {
            background-color: #FFB100;
            color: #000000;
            padding: 8px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-family: 'font', Arial, sans-serif;
            text-decoration: none;
            font-size: 14px;
        }

        .logout-btn:hover {
            background-color: #9e6f00;
        }

        .container {
            max-width: 900px;
            margin: 50px auto;
            padding: 20px;
        }

        h2 {
            color: #FFB100;
            font-size: 32px;
            margin-bottom: 30px;
        }

        .grid-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 25px;
        }

        .menu-card {
            background-color: #343331;
            border: 2px dashed white;
            border-radius: 10px;
            width: 240px;
            padding: 20px;
            box-sizing: border-box;
            transition: transform 0.3s ease;
            text-decoration: none;
            color: #ffffff;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 180px;
        }

        .menu-card:hover {
            transform: scale(1.05);
            border-color: #FFB100;
        }

        .menu-card h4 {
            color: #FFB100;
            margin: 10px 0 0 0;
            font-size: 18px;
        }

        .menu-card p {
            font-size: 12px;
            color: #ddd;
            margin-top: 5px;
        }
    </style>
</head>
<body>

    <div class="header-panel">
        <div>
            Utente: <strong><?php echo htmlspecialchars($username); ?></strong> 
            (Ruolo: <strong><?php echo htmlspecialchars($ruolo); ?></strong>)
        </div>
        <h3>Sistema Gestionale Rete Parchi Naturali</h3>
        <a href="logout.php" class="logout-btn">Disconnetti</a>
    </div>

    <div class="container">
        <h2>Pannello Operativo Personale</h2>
        
        <div class="grid-container">

            <?php if ($ruolo === 'guardiaparco' || $ruolo === 'admin'): ?>
                <a href="tabella_fauna.php" class="menu-card">
                    <h4>Gestione Fauna</h4>
                    <p>Visualizza, inserisci o trasferisci gli esemplari del parco.</p>
                </a>
                <a href="tabella_flora.php" class="menu-card">
                    <h4>Consultazione Flora</h4>
                    <p>Catalogo delle specie floristiche presenti nel territorio.</p>
                </a>
            <?php endif; ?>

            <?php if ($ruolo === 'veterinario' || $ruolo === 'admin'): ?>
                <a href="registro_visite.php" class="menu-card">
                    <h4>Registro Visite</h4>
                    <p>Gestione delle visite cliniche e aggiornamento stato salute.</p>
                </a>
                <a href="consultazione_diete.php" class="menu-card">
                    <h4>Piani Dietetici</h4>
                    <p>Consulta i requisiti nutrizionali delle specie faunistiche.</p>
                </a>
            <?php endif; ?>

            <?php if ($ruolo === 'admin'): ?>
                <a href="statistiche.php" class="menu-card" style="border-color: #FFB100;">
                    <h4 style="color: #ffffff;">Analisi & Statistiche</h4>
                    <p style="color: #FFB100;">Esegui le 10 interrogazioni analitiche di sistema.</p>
                </a>
            <?php endif; ?>

        </div>
    </div>

</body>
</html>
