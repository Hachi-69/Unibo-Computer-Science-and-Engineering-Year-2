-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 21, 2026 at 02:50 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parchi_naturali`
--

-- --------------------------------------------------------

--
-- Table structure for table `ALIMENTO`
--

CREATE TABLE `ALIMENTO` (
  `Nome_Alimento` varchar(100) NOT NULL,
  `Categoria` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ALIMENTO`
--

INSERT INTO `ALIMENTO` (`Nome_Alimento`, `Categoria`) VALUES
('Blocco di sali minerali', 'Integratori'),
('Calamari', 'Pesce'),
('Carcassa di ungulato', 'Carne'),
('Carne cruda mista', 'Carne'),
('Crostacei marini', 'Invertebrati'),
('Erba medica', 'Foraggio'),
('Fieno di prato polifita', 'Foraggio'),
('Foglie e germogli', 'Foraggio'),
('Frutti di bosco', 'Frutta e Semi'),
('Ghiande e castagne', 'Frutta e Semi'),
('Insetti misti', 'Invertebrati'),
('Integratore vitaminico', 'Integratori'),
('Lombrichi', 'Invertebrati'),
('Meduse', 'Invertebrati'),
('Noci e nocciole', 'Frutta e Semi'),
('Pesce azzurro', 'Pesce'),
('Pesce d\'acqua dolce', 'Pesce'),
('Piccoli roditori', 'Carne'),
('Semi misti per uccelli', 'Frutta e Semi'),
('Tuberi e radici', 'Foraggio');

-- --------------------------------------------------------

--
-- Table structure for table `Cresce`
--

CREATE TABLE `Cresce` (
  `Nome_Parco` varchar(100) NOT NULL,
  `Nome_Specie_Flora` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Cresce`
--

INSERT INTO `Cresce` (`Nome_Parco`, `Nome_Specie_Flora`) VALUES
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Acero montano'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Elleboro nero'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Faggio europeo'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Primula appenninica'),
('Parco Nazionale d\'Aspromonte', 'Castagno'),
('Parco Nazionale d\'Aspromonte', 'Faggio europeo'),
('Parco Nazionale d\'Aspromonte', 'Ginestra odorata'),
('Parco Nazionale d\'Aspromonte', 'Pino loricato'),
('Parco Nazionale dei Monti Sibillini', 'Castagno'),
('Parco Nazionale dei Monti Sibillini', 'Faggio europeo'),
('Parco Nazionale dei Monti Sibillini', 'Peonia selvatica'),
('Parco Nazionale dei Monti Sibillini', 'Primula appenninica'),
('Parco Nazionale del Gargano', 'Ciclamino napoletano'),
('Parco Nazionale del Gargano', 'Leccio'),
('Parco Nazionale del Gargano', 'Orchidea piramidale'),
('Parco Nazionale del Gargano', 'Pino marittimo'),
('Parco Nazionale del Gran Paradiso', 'Abete rosso'),
('Parco Nazionale del Gran Paradiso', 'Genziana maggiore'),
('Parco Nazionale del Gran Paradiso', 'Rododendro alpino'),
('Parco Nazionale del Gran Paradiso', 'Stella alpina'),
('Parco Nazionale del Vesuvio', 'Ginestra odorata'),
('Parco Nazionale del Vesuvio', 'Leccio'),
('Parco Nazionale del Vesuvio', 'Pino marittimo'),
('Parco Nazionale dell\'Arcipelago di La Maddalena', 'Corbezzolo'),
('Parco Nazionale dell\'Arcipelago di La Maddalena', 'Giglio di mare'),
('Parco Nazionale dell\'Arcipelago di La Maddalena', 'Mirto'),
('Parco Nazionale dell\'Arcipelago di La Maddalena', 'Quercia da sughero'),
('Parco Nazionale delle Cinque Terre', 'Erica arborea'),
('Parco Nazionale delle Cinque Terre', 'Leccio'),
('Parco Nazionale delle Cinque Terre', 'Pino marittimo'),
('Parco Nazionale delle Cinque Terre', 'Rosmarino selvatico'),
('Parco Nazionale delle Foreste Casentinesi', 'Acero montano'),
('Parco Nazionale delle Foreste Casentinesi', 'Castagno'),
('Parco Nazionale delle Foreste Casentinesi', 'Faggio europeo'),
('Parco Nazionale dello Stelvio', 'Abete rosso'),
('Parco Nazionale dello Stelvio', 'Genziana maggiore'),
('Parco Nazionale dello Stelvio', 'Papavero alpino'),
('Parco Nazionale dello Stelvio', 'Rododendro alpino'),
('Parco Nazionale dello Stelvio', 'Stella alpina'),
('Parco regionale della Vena del Gesso Romagnola', 'Castagno'),
('Parco regionale della Vena del Gesso Romagnola', 'Ginestra odorata');

-- --------------------------------------------------------

--
-- Table structure for table `Dieta`
--

CREATE TABLE `Dieta` (
  `Nome_Alimento` varchar(100) NOT NULL,
  `Nome_Specie_Fauna` varchar(100) NOT NULL,
  `Fascia_Eta` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Dieta`
--

INSERT INTO `Dieta` (`Nome_Alimento`, `Nome_Specie_Fauna`, `Fascia_Eta`) VALUES
('Carne cruda mista', 'Aquila reale', 'Cucciolo'),
('Piccoli roditori', 'Aquila reale', 'Tutte le eta'),
('Erba medica', 'Camoscio alpino', 'Tutte le eta'),
('Erba medica', 'Camoscio d\'Abruzzo', 'Tutte le eta'),
('Foglie e germogli', 'Capriolo', 'Tutte le eta'),
('Calamari', 'Caretta caretta', 'Adulto'),
('Meduse', 'Caretta caretta', 'Tutte le eta'),
('Blocco di sali minerali', 'Cervo nobile', 'Adulto'),
('Fieno di prato polifita', 'Cervo nobile', 'Inverno'),
('Foglie e germogli', 'Cervo nobile', 'Tutte le eta'),
('Ghiande e castagne', 'Cinghiale', 'Adulto'),
('Tuberi e radici', 'Cinghiale', 'Tutte le eta'),
('Piccoli roditori', 'Falco pellegrino', 'Adulto'),
('Pesce azzurro', 'Foca monaca', 'Tutte le eta'),
('Frutti di bosco', 'Gallo cedrone', 'Tutte le eta'),
('Semi misti per uccelli', 'Gallo cedrone', 'Adulto'),
('Piccoli roditori', 'Gatto selvatico', 'Tutte le eta'),
('Carcassa di ungulato', 'Gipeto', 'Tutte le eta'),
('Carcassa di ungulato', 'Grifone', 'Tutte le eta'),
('Piccoli roditori', 'Gufo reale', 'Tutte le eta'),
('Tuberi e radici', 'Istrice', 'Tutte le eta'),
('Carne cruda mista', 'Lince eurasiatica', 'Adulto'),
('Piccoli roditori', 'Lince eurasiatica', 'Tutte le eta'),
('Pesce d\'acqua dolce', 'Lontra europea', 'Tutte le eta'),
('Carcassa di ungulato', 'Lupo appenninico', 'Adulto'),
('Carne cruda mista', 'Lupo appenninico', 'Cucciolo'),
('Erba medica', 'Marmotta delle Alpi', 'Adulto'),
('Tuberi e radici', 'Marmotta delle Alpi', 'Tutte le eta'),
('Carne cruda mista', 'Orso bruno marsicano', 'Adulto'),
('Frutti di bosco', 'Orso bruno marsicano', 'Tutte le eta'),
('Tuberi e radici', 'Orso bruno marsicano', 'Adulto'),
('Lombrichi', 'Salamandra pezzata', 'Tutte le eta'),
('Ghiande e castagne', 'Scoiattolo rosso', 'Adulto'),
('Noci e nocciole', 'Scoiattolo rosso', 'Tutte le eta'),
('Foglie e germogli', 'Stambecco delle Alpi', 'Tutte le eta'),
('Lombrichi', 'Tasso', 'Tutte le eta'),
('Calamari', 'Tursiope', 'Adulto'),
('Pesce azzurro', 'Tursiope', 'Tutte le eta'),
('Piccoli roditori', 'Vipera comune', 'Adulto'),
('Frutti di bosco', 'Volpe rossa', 'Adulto'),
('Piccoli roditori', 'Volpe rossa', 'Tutte le eta');

-- --------------------------------------------------------

--
-- Table structure for table `ESEMPLARE`
--

CREATE TABLE `ESEMPLARE` (
  `Nome_Specie_Fauna` varchar(100) NOT NULL,
  `Nome_Esemplare` varchar(100) NOT NULL,
  `Sesso` enum('M','F') NOT NULL,
  `Data_Nascita` date DEFAULT NULL,
  `Stato_Salute` varchar(50) NOT NULL,
  `Totale_Visite_Subite` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ESEMPLARE`
--

INSERT INTO `ESEMPLARE` (`Nome_Specie_Fauna`, `Nome_Esemplare`, `Sesso`, `Data_Nascita`, `Stato_Salute`, `Totale_Visite_Subite`) VALUES
('Aquila reale', 'AQU-01', 'F', '2016-03-22', 'Ottimo', 2),
('Aquila reale', 'AQU-02', 'M', '2017-04-18', 'In convalescenza', 4),
('Aquila reale', 'AQU-03', 'F', '2019-06-10', 'Buono', 1),
('Camoscio alpino', 'CAL-01', 'M', '2018-09-09', 'Buono', 0),
('Camoscio alpino', 'CAL-02', 'F', '2019-10-10', 'Ottimo', 1),
('Camoscio alpino', 'CAL-03', 'M', '2021-07-22', 'In convalescenza', 3),
('Camoscio d\'Abruzzo', 'CAM-01', 'M', '2021-06-10', 'Ottimo', 0),
('Camoscio d\'Abruzzo', 'CAM-02', 'F', '2020-05-11', 'Buono', 1),
('Camoscio d\'Abruzzo', 'CAM-03', 'M', '2019-04-20', 'Sotto osservazione', 2),
('Camoscio d\'Abruzzo', 'CAM-04', 'F', '2022-01-15', 'Ottimo', 0),
('Capriolo', 'CAP-01', 'M', '2021-07-10', 'Buono', 0),
('Capriolo', 'CAP-02', 'F', '2022-05-20', 'Ottimo', 0),
('Capriolo', 'CAP-03', 'M', '2020-09-15', 'Sotto osservazione', 1),
('Caretta caretta', 'CAR-01', 'F', '2005-08-14', 'Critico', 6),
('Caretta caretta', 'CAR-02', 'M', '2010-07-20', 'Buono', 1),
('Caretta caretta', 'CAR-03', 'F', '2015-05-30', 'In convalescenza', 4),
('Caretta caretta', 'CAR-04', 'M', '2018-09-12', 'Ottimo', 0),
('Cervo nobile', 'CER-01', 'M', '2022-05-15', 'Ottimo', 0),
('Cervo nobile', 'CER-02', 'F', '2021-04-12', 'Buono', 1),
('Cervo nobile', 'CER-03', 'F', '2019-08-25', 'Ottimo', 0),
('Cervo nobile', 'CER-04', 'M', '2017-11-30', 'Critico', 4),
('Cervo nobile', 'CER-05', 'F', '2020-02-14', 'Ottimo', 0),
('Cinghiale', 'CIN-01', 'M', '2022-03-10', 'Ottimo', 0),
('Cinghiale', 'CIN-02', 'F', '2022-03-10', 'Ottimo', 0),
('Cinghiale', 'CIN-03', 'M', '2021-08-15', 'Buono', 1),
('Cinghiale', 'CIN-04', 'F', '2021-08-15', 'Buono', 0),
('Cinghiale', 'CIN-05', 'M', '2020-04-20', 'Sotto osservazione', 2),
('Cinghiale', 'CIN-06', 'F', '2019-12-05', 'Ottimo', 0),
('Cinghiale', 'Kevin', 'M', '2021-05-15', 'Ottimo', 1),
('Falco pellegrino', 'FAL-01', 'M', '2020-08-10', 'Ottimo', 0),
('Falco pellegrino', 'FAL-02', 'F', '2019-07-22', 'In convalescenza', 2),
('Foca monaca', 'FOC-01', 'F', '2014-12-10', 'Sotto osservazione', 3),
('Gallo cedrone', 'GAL-01', 'M', '2021-05-05', 'Buono', 0),
('Gatto selvatico', 'GAT-01', 'M', '2021-11-11', 'Buono', 0),
('Gatto selvatico', 'GAT-02', 'F', '2022-01-20', 'Ottimo', 0),
('Gatto selvatico', 'Luca', 'M', '2020-03-10', 'Sotto osservazione', 1),
('Gipeto', 'GIP-01', 'M', '2015-08-20', 'Ottimo', 2),
('Gipeto', 'GIP-02', 'F', '2018-05-15', 'Sotto osservazione', 3),
('Grifone', 'GRI-01', 'F', '2018-02-28', 'Buono', 2),
('Grifone', 'GRI-02', 'M', '2019-11-11', 'Ottimo', 0),
('Grifone', 'GRI-03', 'M', '2020-04-05', 'Ottimo', 0),
('Gufo reale', 'GUF-01', 'F', '2017-09-30', 'Ottimo', 1),
('Gufo reale', 'GUF-02', 'M', '2021-02-15', 'Buono', 0),
('Istrice', 'IST-01', 'M', '2020-11-20', 'Buono', 0),
('Istrice', 'IST-02', 'F', '2021-04-15', 'Ottimo', 0),
('Lince eurasiatica', 'LIN-01', 'F', '2019-08-30', 'Sotto osservazione', 1),
('Lince eurasiatica', 'LIN-02', 'M', '2020-05-12', 'Ottimo', 0),
('Lontra europea', 'LON-01', 'F', '2018-09-15', 'Ottimo', 1),
('Lontra europea', 'LON-02', 'M', '2019-03-22', 'Buono', 0),
('Lupo appenninico', 'Alpha', 'F', '2016-12-01', 'Critico', 5),
('Lupo appenninico', 'Beta', 'M', '2017-08-30', 'Buono', 3),
('Lupo appenninico', 'Navarro', 'M', '2018-05-20', 'Sotto osservazione', 2),
('Lupo appenninico', 'Remo', 'M', '2020-03-10', 'Ottimo', 0),
('Lupo appenninico', 'Romolo', 'M', '2020-03-10', 'Buono', 0),
('Lupo appenninico', 'Sicilia', 'F', '2019-04-15', 'Ottimo', 1),
('Marmotta delle Alpi', 'MAR-01', 'F', '2021-05-10', 'Ottimo', 0),
('Marmotta delle Alpi', 'MAR-02', 'M', '2021-05-10', 'Ottimo', 0),
('Marmotta delle Alpi', 'MAR-03', 'F', '2020-06-15', 'Buono', 1),
('Marmotta delle Alpi', 'MAR-04', 'M', '2019-07-20', 'Sotto osservazione', 2),
('Orso bruno marsicano', 'Amarena', 'F', '2015-04-12', 'Ottimo', 3),
('Orso bruno marsicano', 'Gemma', 'F', '2010-02-14', 'Ottimo', 2),
('Orso bruno marsicano', 'Juan Carrito', 'M', '2020-01-05', 'Buono', 5),
('Orso bruno marsicano', 'Peppe', 'M', '2012-08-20', 'Sotto osservazione', 8),
('Orso bruno marsicano', 'Sandrino', 'M', '2019-11-23', 'In convalescenza', 4),
('Orso bruno marsicano', 'Yoga', 'F', '2018-05-11', 'Buono', 1),
('Salamandra pezzata', 'SAL-01', 'F', '2022-03-12', 'Ottimo', 0),
('Salamandra pezzata', 'SAL-02', 'M', '2021-09-25', 'Buono', 0),
('Scoiattolo rosso', 'SCO-01', 'M', '2022-03-01', 'Ottimo', 0),
('Scoiattolo rosso', 'SCO-02', 'F', '2021-08-14', 'Buono', 0),
('Scoiattolo rosso', 'SCO-03', 'F', '2022-05-10', 'Ottimo', 0),
('Stambecco delle Alpi', 'STA-01', 'M', '2015-05-05', 'Buono', 2),
('Stambecco delle Alpi', 'STA-02', 'M', '2016-06-06', 'Ottimo', 0),
('Stambecco delle Alpi', 'STA-03', 'F', '2018-04-18', 'Ottimo', 1),
('Stambecco delle Alpi', 'STA-04', 'F', '2020-03-20', 'Sotto osservazione', 2),
('Tasso', 'TAS-01', 'M', '2017-10-10', 'Sotto osservazione', 2),
('Tasso', 'TAS-02', 'F', '2019-06-14', 'Ottimo', 0),
('Tursiope', 'TUR-01', 'M', '2012-11-05', 'Ottimo', 0),
('Tursiope', 'TUR-02', 'F', '2016-04-22', 'Buono', 1),
('Tursiope', 'TUR-03', 'M', '2019-08-15', 'Ottimo', 0),
('Vipera comune', 'VIP-01', 'F', '2020-06-15', 'Ottimo', 0),
('Vipera comune', 'VIP-02', 'M', '2021-04-20', 'Buono', 0),
('Vipera comune', 'VIP-03', 'F', '2019-08-10', 'Sotto osservazione', 1),
('Volpe rossa', 'VOL-01', 'F', '2021-03-10', 'Ottimo', 0),
('Volpe rossa', 'VOL-02', 'M', '2020-07-18', 'Buono', 1),
('Volpe rossa', 'VOL-03', 'F', '2022-02-28', 'Ottimo', 0);

-- --------------------------------------------------------

--
-- Table structure for table `GUARDIAPARCO`
--

CREATE TABLE `GUARDIAPARCO` (
  `Matricola` varchar(20) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Cognome` varchar(50) NOT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `Parco_Assegnato` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `GUARDIAPARCO`
--

INSERT INTO `GUARDIAPARCO` (`Matricola`, `Nome`, `Cognome`, `Telefono`, `Parco_Assegnato`) VALUES
('GRD001', 'Antonio', 'Russo', '3401112233', 'Parco Nazionale del Gran Paradiso'),
('GRD002', 'Elena', 'Romano', '3412223344', 'Parco Nazionale dei Monti Sibillini'),
('GRD003', 'Marco', 'Ferrari', '3423334455', 'Parco Nazionale d\'Abruzzo, Lazio e Molise'),
('GRD004', 'Chiara', 'Esposito', '3434445566', 'Parco Nazionale del Gargano'),
('GRD005', 'Davide', 'Ricci', '3445556677', 'Parco Nazionale del Vesuvio'),
('GRD006', 'Sara', 'Marino', '3456667788', 'Parco Nazionale dell\'Arcipelago di La Maddalena'),
('GRD007', 'Giovanni', 'Greco', '3467778899', 'Parco Nazionale dello Stelvio'),
('GRD008', 'Martina', 'Conti', '3478889900', 'Parco Nazionale delle Foreste Casentinesi'),
('GRD009', 'Lorenzo', 'Gallo', '3489990011', 'Parco Nazionale delle Cinque Terre'),
('GRD010', 'Simona', 'Costa', '3490001122', 'Parco Nazionale d\'Aspromonte'),
('GRD011', 'Ivano', 'Fabbri', '3392407028', 'Parco regionale della Vena del Gesso Romagnola');

-- --------------------------------------------------------

--
-- Table structure for table `PARCO`
--

CREATE TABLE `PARCO` (
  `Nome_Parco` varchar(100) NOT NULL,
  `Regione` varchar(50) NOT NULL,
  `Superficie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `PARCO`
--

INSERT INTO `PARCO` (`Nome_Parco`, `Regione`, `Superficie`) VALUES
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Abruzzo / Lazio / Molise', 50683),
('Parco Nazionale d\'Aspromonte', 'Calabria', 64153),
('Parco Nazionale dei Monti Sibillini', 'Marche / Umbria', 71437),
('Parco Nazionale del Gargano', 'Puglia', 118144),
('Parco Nazionale del Gran Paradiso', 'Valle d\'Aosta / Piemonte', 71043),
('Parco Nazionale del Vesuvio', 'Campania', 8482),
('Parco Nazionale dell\'Arcipelago di La Maddalena', 'Sardegna', 20146),
('Parco Nazionale delle Cinque Terre', 'Liguria', 3860),
('Parco Nazionale delle Foreste Casentinesi', 'Emilia-Romagna / Toscana', 36843),
('Parco Nazionale dello Stelvio', 'Lombardia / Trentino', 130700),
('Parco regionale della Vena del Gesso Romagnola', 'Emilia-Romagna', 2033);

-- --------------------------------------------------------

--
-- Table structure for table `PERMANENZA`
--

CREATE TABLE `PERMANENZA` (
  `Nome_Parco` varchar(100) NOT NULL,
  `Nome_Specie_Fauna` varchar(100) NOT NULL,
  `Nome_Esemplare` varchar(100) NOT NULL,
  `Data_Inizio` date NOT NULL,
  `Data_Fine` date DEFAULT NULL,
  `Modalita_Ingresso` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `PERMANENZA`
--

INSERT INTO `PERMANENZA` (`Nome_Parco`, `Nome_Specie_Fauna`, `Nome_Esemplare`, `Data_Inizio`, `Data_Fine`, `Modalita_Ingresso`) VALUES
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Camoscio d\'Abruzzo', 'CAM-01', '2021-07-20', NULL, 'Reintroduzione'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Camoscio d\'Abruzzo', 'CAM-03', '2019-10-01', NULL, 'Nascita in loco'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Capriolo', 'CAP-03', '2021-02-28', NULL, 'Cattura e ricollocamento'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Cervo nobile', 'CER-03', '2020-01-15', NULL, 'Scambio genetico inter-parco'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Cinghiale', 'CIN-05', '2020-09-10', NULL, 'Sconfinamento spontaneo'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Grifone', 'GRI-01', '2018-03-10', '2020-04-15', 'Reintroduzione'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Gufo reale', 'GUF-02', '2021-06-20', NULL, 'Trasferimento da altro parco'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Lupo appenninico', 'Remo', '2020-08-10', NULL, 'Nascita in loco'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Lupo appenninico', 'Romolo', '2020-08-10', NULL, 'Nascita in loco'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Orso bruno marsicano', 'Amarena', '2015-10-01', NULL, 'Nascita in loco'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Orso bruno marsicano', 'Gemma', '2010-06-15', NULL, 'Nascita in loco'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Orso bruno marsicano', 'Juan Carrito', '2020-05-10', NULL, 'Nascita in loco'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Orso bruno marsicano', 'Peppe', '2012-09-15', NULL, 'Nascita in loco'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Orso bruno marsicano', 'Sandrino', '2020-03-01', NULL, 'Nascita in loco'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Orso bruno marsicano', 'Yoga', '2021-04-11', NULL, 'Cattura e ricollocamento'),
('Parco Nazionale d\'Abruzzo, Lazio e Molise', 'Scoiattolo rosso', 'SCO-03', '2022-09-10', NULL, 'Nascita in loco'),
('Parco Nazionale d\'Aspromonte', 'Cervo nobile', 'CER-05', '2020-07-20', NULL, 'Reintroduzione'),
('Parco Nazionale d\'Aspromonte', 'Cinghiale', 'CIN-04', '2022-01-15', NULL, 'Nascita in loco'),
('Parco Nazionale d\'Aspromonte', 'Gatto selvatico', 'GAT-01', '2021-12-05', NULL, 'Nascita in loco'),
('Parco Nazionale d\'Aspromonte', 'Grifone', 'GRI-03', '2020-08-05', NULL, 'Reintroduzione'),
('Parco Nazionale d\'Aspromonte', 'Istrice', 'IST-02', '2021-08-25', NULL, 'Nascita in loco'),
('Parco Nazionale d\'Aspromonte', 'Salamandra pezzata', 'SAL-01', '2022-07-12', NULL, 'Nascita in loco'),
('Parco Nazionale dei Monti Sibillini', 'Aquila reale', 'AQU-03', '2019-10-10', NULL, 'Nascita in loco'),
('Parco Nazionale dei Monti Sibillini', 'Camoscio d\'Abruzzo', 'CAM-01', '2021-06-10', '2021-07-19', 'Nascita in loco'),
('Parco Nazionale dei Monti Sibillini', 'Camoscio d\'Abruzzo', 'CAM-02', '2020-06-15', NULL, 'Reintroduzione'),
('Parco Nazionale dei Monti Sibillini', 'Camoscio d\'Abruzzo', 'CAM-04', '2022-06-01', NULL, 'Nascita in loco'),
('Parco Nazionale dei Monti Sibillini', 'Capriolo', 'CAP-02', '2022-08-10', NULL, 'Nascita in loco'),
('Parco Nazionale dei Monti Sibillini', 'Cervo nobile', 'CER-03', '2019-08-25', '2020-01-14', 'Nascita in loco'),
('Parco Nazionale dei Monti Sibillini', 'Cervo nobile', 'CER-04', '2018-04-10', NULL, 'Sconfinamento spontaneo'),
('Parco Nazionale dei Monti Sibillini', 'Grifone', 'GRI-01', '2020-04-16', NULL, 'Trasferimento da altro parco'),
('Parco Nazionale dei Monti Sibillini', 'Lupo appenninico', 'Sicilia', '2019-05-20', NULL, 'Nascita in loco'),
('Parco Nazionale dei Monti Sibillini', 'Orso bruno marsicano', 'Yoga', '2018-05-11', '2021-04-10', 'Sconfinamento spontaneo'),
('Parco Nazionale dei Monti Sibillini', 'Tasso', 'TAS-01', '2018-03-22', NULL, 'Nascita in loco'),
('Parco Nazionale dei Monti Sibillini', 'Vipera comune', 'VIP-02', '2021-08-20', NULL, 'Nascita in loco'),
('Parco Nazionale del Gargano', 'Capriolo', 'CAP-03', '2020-09-15', '2021-02-27', 'Nascita in loco'),
('Parco Nazionale del Gargano', 'Caretta caretta', 'CAR-04', '2019-01-12', NULL, 'Nascita in loco'),
('Parco Nazionale del Gargano', 'Cinghiale', 'CIN-02', '2022-05-15', NULL, 'Nascita in loco'),
('Parco Nazionale del Gargano', 'Falco pellegrino', 'FAL-02', '2019-11-22', NULL, 'Sconfinamento spontaneo'),
('Parco Nazionale del Gargano', 'Grifone', 'GRI-02', '2020-04-11', NULL, 'Nascita in loco'),
('Parco Nazionale del Gargano', 'Istrice', 'IST-01', '2021-04-10', NULL, 'Nascita in loco'),
('Parco Nazionale del Gargano', 'Lontra europea', 'LON-01', '2019-02-10', NULL, 'Avvistamento e tutela'),
('Parco Nazionale del Gargano', 'Lontra europea', 'LON-02', '2019-08-20', NULL, 'Nascita in loco'),
('Parco Nazionale del Gargano', 'Lupo appenninico', 'Alpha', '2017-02-10', NULL, 'Sconfinamento spontaneo'),
('Parco Nazionale del Gargano', 'Tursiope', 'TUR-02', '2016-10-22', NULL, 'Avvistamento e tutela'),
('Parco Nazionale del Gran Paradiso', 'Aquila reale', 'AQU-02', '2017-05-22', NULL, 'Nascita in loco'),
('Parco Nazionale del Gran Paradiso', 'Camoscio alpino', 'CAL-02', '2019-11-20', NULL, 'Nascita in loco'),
('Parco Nazionale del Gran Paradiso', 'Camoscio alpino', 'CAL-03', '2021-10-15', NULL, 'Nascita in loco'),
('Parco Nazionale del Gran Paradiso', 'Lince eurasiatica', 'LIN-02', '2020-10-05', NULL, 'Avvistamento spontaneo'),
('Parco Nazionale del Gran Paradiso', 'Lupo appenninico', 'Beta', '2018-01-15', NULL, 'Sconfinamento spontaneo'),
('Parco Nazionale del Gran Paradiso', 'Marmotta delle Alpi', 'MAR-03', '2020-10-15', NULL, 'Nascita in loco'),
('Parco Nazionale del Gran Paradiso', 'Marmotta delle Alpi', 'MAR-04', '2019-11-20', NULL, 'Nascita in loco'),
('Parco Nazionale del Gran Paradiso', 'Stambecco delle Alpi', 'STA-03', '2018-05-01', '2021-05-01', 'Nascita in loco'),
('Parco Nazionale del Gran Paradiso', 'Stambecco delle Alpi', 'STA-04', '2020-08-05', NULL, 'Nascita in loco'),
('Parco Nazionale del Vesuvio', 'Cinghiale', 'CIN-01', '2022-04-10', NULL, 'Nascita in loco'),
('Parco Nazionale del Vesuvio', 'Cinghiale', 'CIN-06', '2020-03-12', NULL, 'Nascita in loco'),
('Parco Nazionale del Vesuvio', 'Volpe rossa', 'VOL-01', '2021-07-15', NULL, 'Nascita in loco'),
('Parco Nazionale dell\'Arcipelago di La Maddalena', 'Caretta caretta', 'CAR-01', '2005-09-01', NULL, 'Nascita in loco'),
('Parco Nazionale dell\'Arcipelago di La Maddalena', 'Caretta caretta', 'CAR-03', '2018-09-11', NULL, 'Trasferimento da altro parco'),
('Parco Nazionale dell\'Arcipelago di La Maddalena', 'Foca monaca', 'FOC-01', '2015-04-10', NULL, 'Avvistamento e tutela'),
('Parco Nazionale dell\'Arcipelago di La Maddalena', 'Tursiope', 'TUR-01', '2013-05-20', NULL, 'Monitoraggio costiero'),
('Parco Nazionale dell\'Arcipelago di La Maddalena', 'Vipera comune', 'VIP-01', '2020-10-15', NULL, 'Nascita in loco'),
('Parco Nazionale dell\'Arcipelago di La Maddalena', 'Volpe rossa', 'VOL-03', '2022-06-20', NULL, 'Sconfinamento spontaneo'),
('Parco Nazionale delle Cinque Terre', 'Caretta caretta', 'CAR-02', '2010-08-10', NULL, 'Nascita in loco'),
('Parco Nazionale delle Cinque Terre', 'Caretta caretta', 'CAR-03', '2015-06-10', '2018-09-10', 'Salvataggio in mare'),
('Parco Nazionale delle Cinque Terre', 'Falco pellegrino', 'FAL-01', '2020-12-10', NULL, 'Nascita in loco'),
('Parco Nazionale delle Cinque Terre', 'Tursiope', 'TUR-03', '2020-01-15', NULL, 'Avvistamento e tutela'),
('Parco Nazionale delle Cinque Terre', 'Vipera comune', 'VIP-03', '2019-11-10', NULL, 'Nascita in loco'),
('Parco Nazionale delle Cinque Terre', 'Volpe rossa', 'VOL-02', '2020-11-10', NULL, 'Nascita in loco'),
('Parco Nazionale delle Foreste Casentinesi', 'Capriolo', 'CAP-01', '2021-10-15', NULL, 'Nascita in loco'),
('Parco Nazionale delle Foreste Casentinesi', 'Cervo nobile', 'CER-01', '2022-06-20', NULL, 'Nascita in loco'),
('Parco Nazionale delle Foreste Casentinesi', 'Cervo nobile', 'CER-02', '2021-09-01', NULL, 'Nascita in loco'),
('Parco Nazionale delle Foreste Casentinesi', 'Cinghiale', 'CIN-03', '2021-11-20', NULL, 'Nascita in loco'),
('Parco Nazionale delle Foreste Casentinesi', 'Gatto selvatico', 'GAT-02', '2022-05-15', NULL, 'Nascita in loco'),
('Parco Nazionale delle Foreste Casentinesi', 'Gufo reale', 'GUF-01', '2018-02-15', NULL, 'Nascita in loco'),
('Parco Nazionale delle Foreste Casentinesi', 'Gufo reale', 'GUF-02', '2021-02-15', '2021-06-19', 'Nascita in loco'),
('Parco Nazionale delle Foreste Casentinesi', 'Lupo appenninico', 'Navarro', '2018-06-15', NULL, 'Nascita in loco'),
('Parco Nazionale delle Foreste Casentinesi', 'Salamandra pezzata', 'SAL-02', '2022-02-25', NULL, 'Nascita in loco'),
('Parco Nazionale delle Foreste Casentinesi', 'Scoiattolo rosso', 'SCO-01', '2022-04-20', NULL, 'Nascita in loco'),
('Parco Nazionale delle Foreste Casentinesi', 'Scoiattolo rosso', 'SCO-02', '2021-12-14', NULL, 'Nascita in loco'),
('Parco Nazionale delle Foreste Casentinesi', 'Tasso', 'TAS-02', '2019-10-18', NULL, 'Nascita in loco'),
('Parco Nazionale dello Stelvio', 'Aquila reale', 'AQU-01', '2016-04-10', NULL, 'Nascita in loco'),
('Parco Nazionale dello Stelvio', 'Camoscio alpino', 'CAL-01', '2018-10-15', NULL, 'Nascita in loco'),
('Parco Nazionale dello Stelvio', 'Gallo cedrone', 'GAL-01', '2021-09-05', NULL, 'Nascita in loco'),
('Parco Nazionale dello Stelvio', 'Gipeto', 'GIP-01', '2015-09-10', NULL, 'Reintroduzione'),
('Parco Nazionale dello Stelvio', 'Gipeto', 'GIP-02', '2018-09-15', NULL, 'Nascita in loco'),
('Parco Nazionale dello Stelvio', 'Lince eurasiatica', 'LIN-01', '2019-09-15', NULL, 'Avvistamento spontaneo'),
('Parco Nazionale dello Stelvio', 'Marmotta delle Alpi', 'MAR-01', '2021-09-10', NULL, 'Nascita in loco'),
('Parco Nazionale dello Stelvio', 'Marmotta delle Alpi', 'MAR-02', '2021-09-10', NULL, 'Nascita in loco'),
('Parco Nazionale dello Stelvio', 'Stambecco delle Alpi', 'STA-01', '2015-06-01', NULL, 'Nascita in loco'),
('Parco Nazionale dello Stelvio', 'Stambecco delle Alpi', 'STA-02', '2016-10-10', NULL, 'Nascita in loco'),
('Parco Nazionale dello Stelvio', 'Stambecco delle Alpi', 'STA-03', '2021-05-02', NULL, 'Scambio genetico inter-parco'),
('Parco regionale della Vena del Gesso Romagnola', 'Cinghiale', 'Kevin', '2021-06-01', NULL, 'Sconfinamento spontaneo'),
('Parco regionale della Vena del Gesso Romagnola', 'Gatto selvatico', 'Luca', '2020-05-20', NULL, 'Avvistamento e tutela');

-- --------------------------------------------------------

--
-- Table structure for table `SPECIE_FAUNA`
--

CREATE TABLE `SPECIE_FAUNA` (
  `Nome_Specie_Fauna` varchar(100) NOT NULL,
  `Ordine` varchar(50) NOT NULL,
  `Mesi_Adulto` int(11) NOT NULL,
  `Rischio_Estinzione` enum('Rischio minimo','Vulnerabile','In pericolo','Critico','Estinto in natura') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `SPECIE_FAUNA`
--

INSERT INTO `SPECIE_FAUNA` (`Nome_Specie_Fauna`, `Ordine`, `Mesi_Adulto`, `Rischio_Estinzione`) VALUES
('Aquila reale', 'Accipitriformi', 48, 'Rischio minimo'),
('Camoscio alpino', 'Artiodattili', 36, 'Rischio minimo'),
('Camoscio d\'Abruzzo', 'Artiodattili', 36, 'Vulnerabile'),
('Capriolo', 'Artiodattili', 14, 'Rischio minimo'),
('Caretta caretta', 'Testudinati', 150, 'Vulnerabile'),
('Cervo nobile', 'Artiodattili', 24, 'Rischio minimo'),
('Cinghiale', 'Artiodattili', 12, 'Rischio minimo'),
('Falco pellegrino', 'Falconiformi', 24, 'Rischio minimo'),
('Foca monaca', 'Carnivori', 48, 'In pericolo'),
('Gallo cedrone', 'Galliformi', 12, 'Vulnerabile'),
('Gatto selvatico', 'Carnivori', 10, 'Rischio minimo'),
('Gipeto', 'Accipitriformi', 72, 'Critico'),
('Grifone', 'Accipitriformi', 60, 'Critico'),
('Gufo reale', 'Strigiformi', 12, 'Rischio minimo'),
('Istrice', 'Roditori', 12, 'Rischio minimo'),
('Lince eurasiatica', 'Carnivori', 24, 'In pericolo'),
('Lontra europea', 'Carnivori', 24, 'In pericolo'),
('Lupo appenninico', 'Carnivori', 24, 'Vulnerabile'),
('Marmotta delle Alpi', 'Roditori', 24, 'Rischio minimo'),
('Orso bruno marsicano', 'Carnivori', 48, 'Critico'),
('Salamandra pezzata', 'Urodeli', 24, 'Rischio minimo'),
('Scoiattolo rosso', 'Roditori', 10, 'Rischio minimo'),
('Stambecco delle Alpi', 'Artiodattili', 48, 'Rischio minimo'),
('Tasso', 'Carnivori', 12, 'Rischio minimo'),
('Tursiope', 'Cetacei', 120, 'Rischio minimo'),
('Vipera comune', 'Squamati', 36, 'Rischio minimo'),
('Volpe rossa', 'Carnivori', 10, 'Rischio minimo');

-- --------------------------------------------------------

--
-- Table structure for table `SPECIE_FLORA`
--

CREATE TABLE `SPECIE_FLORA` (
  `Nome_Specie_Flora` varchar(100) NOT NULL,
  `Tipo` varchar(50) NOT NULL,
  `Stagione_Fioritura` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `SPECIE_FLORA`
--

INSERT INTO `SPECIE_FLORA` (`Nome_Specie_Flora`, `Tipo`, `Stagione_Fioritura`) VALUES
('Abete rosso', 'Albero', 'Primavera'),
('Acero montano', 'Albero', 'Primavera'),
('Castagno', 'Albero', 'Estate'),
('Ciclamino napoletano', 'Pianta erbacea', 'Autunno'),
('Corbezzolo', 'Arbusto', 'Autunno'),
('Elleboro nero', 'Pianta erbacea', 'Inverno'),
('Erica arborea', 'Arbusto', 'Primavera'),
('Faggio europeo', 'Albero', 'Primavera'),
('Genziana maggiore', 'Pianta erbacea', 'Estate'),
('Giglio di mare', 'Pianta erbacea', 'Estate'),
('Ginepro coccolone', 'Arbusto', 'Primavera'),
('Ginestra odorata', 'Arbusto', 'Primavera'),
('Leccio', 'Albero', 'Primavera'),
('Mirto', 'Arbusto', 'Estate'),
('Oleandro selvatico', 'Arbusto', 'Estate'),
('Orchidea piramidale', 'Pianta erbacea', 'Primavera'),
('Papavero alpino', 'Pianta erbacea', 'Estate'),
('Peonia selvatica', 'Pianta erbacea', 'Primavera'),
('Pino loricato', 'Albero', 'Primavera'),
('Pino marittimo', 'Albero', 'Primavera'),
('Primula appenninica', 'Pianta erbacea', 'Primavera'),
('Quercia da sughero', 'Albero', 'Primavera'),
('Rododendro alpino', 'Arbusto', 'Estate'),
('Rosmarino selvatico', 'Arbusto', 'Primavera'),
('Stella alpina', 'Pianta erbacea', 'Estate');

-- --------------------------------------------------------

--
-- Table structure for table `VETERINARIO`
--

CREATE TABLE `VETERINARIO` (
  `Matricola` varchar(20) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Cognome` varchar(50) NOT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `Numero_Albo` varchar(50) NOT NULL,
  `Specializzazione` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `VETERINARIO`
--

INSERT INTO `VETERINARIO` (`Matricola`, `Nome`, `Cognome`, `Telefono`, `Numero_Albo`, `Specializzazione`) VALUES
('VET001', 'Mario', 'Rossi', '3331234567', 'ALB-RM-1023', 'Fauna selvatica alpina e ungulati'),
('VET002', 'Giulia', 'Bianchi', '3349876543', 'ALB-MI-4451', 'Medicina dei grandi carnivori'),
('VET003', 'Luca', 'Verdi', '3381122334', 'ALB-NA-7890', 'Patologia aviare e rapaci'),
('VET004', 'Francesca', 'Neri', '3395566778', 'ALB-BO-3321', 'Erpetologia e biologia marina'),
('VET005', 'Alessandro', 'Gialli', '3319988776', 'ALB-FI-5647', 'Medicina preventiva e parassitologia'),
('VET006', 'Umberto', 'Sansovini', '3517743052', 'ALB-RA-3621', 'Esperto HACCP selvatico e controllo qualità piadina romagnola');

-- --------------------------------------------------------

--
-- Table structure for table `VISITA_MEDICA`
--

CREATE TABLE `VISITA_MEDICA` (
  `Matricola_Vet` varchar(20) NOT NULL,
  `Nome_Specie_Fauna` varchar(100) NOT NULL,
  `Nome_Esemplare` varchar(100) NOT NULL,
  `Data` date NOT NULL,
  `Ora` time NOT NULL,
  `Esito` varchar(50) NOT NULL,
  `Terapia_Prescritta` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `VISITA_MEDICA`
--

INSERT INTO `VISITA_MEDICA` (`Matricola_Vet`, `Nome_Specie_Fauna`, `Nome_Esemplare`, `Data`, `Ora`, `Esito`, `Terapia_Prescritta`) VALUES
('VET001', 'Camoscio d\'Abruzzo', 'CAM-03', '2020-05-12', '13:00:00', 'Controllo biometrico neonatale', 'Nessuna'),
('VET001', 'Camoscio d\'Abruzzo', 'CAM-03', '2022-09-18', '08:45:00', 'Zoppia arto anteriore destro', 'Riposo in area recintata e antinfiammatorio'),
('VET001', 'Capriolo', 'CAP-03', '2022-02-10', '13:15:00', 'Sotto osservazione - Ferita lacero-contusa', 'Disinfezione e sutura'),
('VET001', 'Cervo nobile', 'CER-04', '2018-11-15', '11:00:00', 'Sospetta parassitosi intestinale', 'Trattamento antielmintico'),
('VET001', 'Cervo nobile', 'CER-04', '2019-02-20', '09:30:00', 'Esito negativo feci - Guarito', 'Nessuna'),
('VET001', 'Cervo nobile', 'CER-04', '2021-01-10', '15:15:00', 'Critico - Denutrizione invernale', 'Foraggiamento supplementare mirato'),
('VET001', 'Cervo nobile', 'CER-04', '2021-04-05', '10:45:00', 'Recupero peso completato', 'Nessuna'),
('VET002', 'Lupo appenninico', 'Alpha', '2018-12-01', '08:30:00', 'Critico - Sospetto avvelenamento', 'Lavanda gastrica e fluidoterapia'),
('VET002', 'Lupo appenninico', 'Alpha', '2019-01-15', '09:15:00', 'Debole ma stabile', 'Epatoprotettori'),
('VET002', 'Lupo appenninico', 'Alpha', '2020-03-20', '10:00:00', 'Buono - Rilascio in natura', 'Nessuna'),
('VET002', 'Lupo appenninico', 'Alpha', '2021-11-10', '16:45:00', 'Ferita da combattimento intra-specifico', 'Sutura e antibiotico a lento rilascio'),
('VET002', 'Lupo appenninico', 'Alpha', '2022-02-28', '14:20:00', 'Guarito - Controllo radiocollare', 'Nessuna'),
('VET002', 'Orso bruno marsicano', 'Amarena', '2019-05-10', '09:30:00', 'Buono - Applicazione radiocollare', 'Nessuna'),
('VET002', 'Orso bruno marsicano', 'Amarena', '2021-04-15', '10:00:00', 'Ottimo - Cambio batterie collare', 'Nessuna'),
('VET002', 'Orso bruno marsicano', 'Amarena', '2023-05-20', '11:15:00', 'Ottimo - Prelievo ematico', 'Nessuna, somministrate vitamine'),
('VET002', 'Orso bruno marsicano', 'Gemma', '2015-08-20', '09:00:00', 'Cattura per monitoraggio', 'Applicazione radiocollare'),
('VET002', 'Orso bruno marsicano', 'Gemma', '2020-05-14', '11:30:00', 'Ottimo - Rimozione collare malfunzionante', 'Nessuna'),
('VET002', 'Orso bruno marsicano', 'Juan Carrito', '2020-10-10', '09:15:00', 'Controllo cucciolo pre-letargo', 'Nessuna'),
('VET002', 'Orso bruno marsicano', 'Juan Carrito', '2021-05-20', '11:00:00', 'Incursione centro abitato - Sedazione', 'Nessuna'),
('VET002', 'Orso bruno marsicano', 'Juan Carrito', '2022-03-15', '14:30:00', 'Applicazione marca auricolare', 'Antibiotico locale'),
('VET002', 'Orso bruno marsicano', 'Juan Carrito', '2023-01-24', '16:00:00', 'Incidente stradale lieve', 'Osservazione 48h e antidolorifico'),
('VET002', 'Orso bruno marsicano', 'Juan Carrito', '2024-06-10', '10:45:00', 'Buono - Controllo generale', 'Nessuna'),
('VET002', 'Orso bruno marsicano', 'Peppe', '2013-05-10', '09:00:00', 'Controllo accrescimento', 'Nessuna'),
('VET002', 'Orso bruno marsicano', 'Peppe', '2015-04-12', '10:30:00', 'Applicazione radiocollare', 'Sedazione leggera'),
('VET002', 'Orso bruno marsicano', 'Peppe', '2017-06-20', '11:15:00', 'Sostituzione batteria collare', 'Nessuna'),
('VET002', 'Orso bruno marsicano', 'Peppe', '2019-09-05', '08:45:00', 'Sospetta dermatite', 'Trattamento antiparassitario locale'),
('VET002', 'Orso bruno marsicano', 'Peppe', '2021-03-22', '14:00:00', 'Controllo post-letargo', 'Nessuna'),
('VET002', 'Orso bruno marsicano', 'Peppe', '2022-11-10', '15:30:00', 'Zoppia lieve arto posteriore', 'Antinfiammatorio'),
('VET002', 'Orso bruno marsicano', 'Peppe', '2024-05-18', '09:30:00', 'Sostituzione collare GPS', 'Nessuna'),
('VET002', 'Orso bruno marsicano', 'Peppe', '2025-08-30', '10:00:00', 'Sotto osservazione - Calo ponderale', 'Integrazione alimentare'),
('VET002', 'Orso bruno marsicano', 'Sandrino', '2020-05-10', '14:00:00', 'Critico - Investimento stradale', 'Chirurgia ortopedica e antibiotici'),
('VET002', 'Orso bruno marsicano', 'Sandrino', '2020-06-15', '09:00:00', 'Miglioramento - Controllo post-operatorio', 'Antidolorifici'),
('VET002', 'Orso bruno marsicano', 'Sandrino', '2020-10-20', '10:30:00', 'In convalescenza - Riabilitazione', 'Integrazione alimentare'),
('VET002', 'Orso bruno marsicano', 'Sandrino', '2021-04-05', '11:00:00', 'Stabile - Rimozione ferri', 'Nessuna'),
('VET002', 'Orso bruno marsicano', 'Yoga', '2020-10-15', '10:30:00', 'Buono - Controllo di routine pre-letargo', 'Nessuna'),
('VET003', 'Aquila reale', 'AQU-02', '2018-05-20', '14:00:00', 'Trauma da impatto con cavo elettrico', 'Immobilizzazione ala sinistra'),
('VET003', 'Aquila reale', 'AQU-02', '2018-07-15', '10:30:00', 'Rimozione bendaggio - Inizio riabilitazione volo', 'Nessuna'),
('VET003', 'Aquila reale', 'AQU-02', '2018-09-10', '11:00:00', 'Volo incerto - Prolungamento voliera tunnel', 'Integrazione calcio'),
('VET003', 'Aquila reale', 'AQU-02', '2022-04-18', '09:15:00', 'In convalescenza prolungata', 'Mantenimento in cattivita per inidoneita al rilascio'),
('VET003', 'Grifone', 'GRI-01', '2019-02-28', '16:30:00', 'Marcatura pre-rilascio', 'Nessuna - Applicazione anello e decolorazione penne'),
('VET003', 'Grifone', 'GRI-01', '2021-05-15', '12:00:00', 'Sospetta intossicazione da piombo (saturnismo)', 'Terapia chelante (CaEDTA)'),
('VET004', 'Caretta caretta', 'CAR-01', '2010-06-15', '10:00:00', 'Ingestione amo da pesca', 'Estrazione chirurgica endoscopica'),
('VET004', 'Caretta caretta', 'CAR-01', '2010-08-20', '09:30:00', 'Buono - Rilascio in mare', 'Nessuna'),
('VET004', 'Caretta caretta', 'CAR-01', '2015-05-10', '16:00:00', 'Intrappolamento in rete fantasma', 'Trattamento necrosi pinna'),
('VET004', 'Caretta caretta', 'CAR-01', '2015-07-15', '11:15:00', 'Stabile ma amputazione necessaria', 'Antibiotici ad ampio spettro'),
('VET004', 'Caretta caretta', 'CAR-01', '2018-09-22', '14:30:00', 'Sindrome da decompressione', 'Ossigenoterapia in camera iperbarica'),
('VET004', 'Caretta caretta', 'CAR-01', '2022-01-10', '10:45:00', 'Critico - Ingestione plastica', 'Lassativi e fluidoterapia endovenosa'),
('VET004', 'Foca monaca', 'FOC-01', '2016-04-12', '09:00:00', 'Debilitazione da tempesta', 'Idratazione forzata e pasta di pesce'),
('VET004', 'Foca monaca', 'FOC-01', '2016-05-20', '11:30:00', 'Guarigione completa - Tag satellitare applicato', 'Nessuna'),
('VET004', 'Foca monaca', 'FOC-01', '2021-08-15', '15:00:00', 'Sotto osservazione - Micosi cutanea', 'Bagni antimicotici locali'),
('VET005', 'Cinghiale', 'CIN-05', '2021-10-15', '09:00:00', 'Cattura per monitoraggio peste suina africana', 'Prelievo ematico e tampone - Esito Negativo'),
('VET005', 'Cinghiale', 'CIN-05', '2022-11-20', '14:30:00', 'Zoppia da laccio di bracconaggio', 'Rimozione laccio e disinfezione profonda'),
('VET005', 'Marmotta delle Alpi', 'MAR-04', '2020-04-10', '11:15:00', 'Risveglio letargo difficoltoso - Sottopeso', 'Integrazione vitaminica e foraggiamento'),
('VET005', 'Marmotta delle Alpi', 'MAR-04', '2021-08-22', '10:00:00', 'Dermatite parassitaria (Acariasi)', 'Trattamento acaricida spot-on'),
('VET006', 'Cinghiale', 'Kevin', '2023-02-10', '13:00:00', 'Ispezione HACCP superata - Massa grassa ideale', 'Premio: Mezza piadina crudo e squacquerone'),
('VET006', 'Gatto selvatico', 'Luca', '2023-04-05', '12:30:00', 'Cali di zuccheri e spossatezza', 'Integrazione urgente: Crescione erbe e salsiccia');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ALIMENTO`
--
ALTER TABLE `ALIMENTO`
  ADD PRIMARY KEY (`Nome_Alimento`);

--
-- Indexes for table `Cresce`
--
ALTER TABLE `Cresce`
  ADD PRIMARY KEY (`Nome_Parco`,`Nome_Specie_Flora`),
  ADD KEY `Nome_Specie_Flora` (`Nome_Specie_Flora`);

--
-- Indexes for table `Dieta`
--
ALTER TABLE `Dieta`
  ADD PRIMARY KEY (`Nome_Specie_Fauna`,`Nome_Alimento`,`Fascia_Eta`),
  ADD KEY `Nome_Alimento` (`Nome_Alimento`);

--
-- Indexes for table `ESEMPLARE`
--
ALTER TABLE `ESEMPLARE`
  ADD PRIMARY KEY (`Nome_Specie_Fauna`,`Nome_Esemplare`);

--
-- Indexes for table `GUARDIAPARCO`
--
ALTER TABLE `GUARDIAPARCO`
  ADD PRIMARY KEY (`Matricola`),
  ADD KEY `Parco_Assegnato` (`Parco_Assegnato`);

--
-- Indexes for table `PARCO`
--
ALTER TABLE `PARCO`
  ADD PRIMARY KEY (`Nome_Parco`);

--
-- Indexes for table `PERMANENZA`
--
ALTER TABLE `PERMANENZA`
  ADD PRIMARY KEY (`Nome_Parco`,`Nome_Specie_Fauna`,`Nome_Esemplare`,`Data_Inizio`),
  ADD KEY `Nome_Specie_Fauna` (`Nome_Specie_Fauna`,`Nome_Esemplare`);

--
-- Indexes for table `SPECIE_FAUNA`
--
ALTER TABLE `SPECIE_FAUNA`
  ADD PRIMARY KEY (`Nome_Specie_Fauna`);

--
-- Indexes for table `SPECIE_FLORA`
--
ALTER TABLE `SPECIE_FLORA`
  ADD PRIMARY KEY (`Nome_Specie_Flora`);

--
-- Indexes for table `VETERINARIO`
--
ALTER TABLE `VETERINARIO`
  ADD PRIMARY KEY (`Matricola`),
  ADD UNIQUE KEY `Numero_Albo` (`Numero_Albo`);

--
-- Indexes for table `VISITA_MEDICA`
--
ALTER TABLE `VISITA_MEDICA`
  ADD PRIMARY KEY (`Matricola_Vet`,`Nome_Specie_Fauna`,`Nome_Esemplare`,`Data`,`Ora`),
  ADD KEY `Nome_Specie_Fauna` (`Nome_Specie_Fauna`,`Nome_Esemplare`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Cresce`
--
ALTER TABLE `Cresce`
  ADD CONSTRAINT `Cresce_ibfk_1` FOREIGN KEY (`Nome_Parco`) REFERENCES `PARCO` (`Nome_Parco`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Cresce_ibfk_2` FOREIGN KEY (`Nome_Specie_Flora`) REFERENCES `SPECIE_FLORA` (`Nome_Specie_Flora`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Dieta`
--
ALTER TABLE `Dieta`
  ADD CONSTRAINT `Dieta_ibfk_1` FOREIGN KEY (`Nome_Specie_Fauna`) REFERENCES `SPECIE_FAUNA` (`Nome_Specie_Fauna`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Dieta_ibfk_2` FOREIGN KEY (`Nome_Alimento`) REFERENCES `ALIMENTO` (`Nome_Alimento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ESEMPLARE`
--
ALTER TABLE `ESEMPLARE`
  ADD CONSTRAINT `ESEMPLARE_ibfk_1` FOREIGN KEY (`Nome_Specie_Fauna`) REFERENCES `SPECIE_FAUNA` (`Nome_Specie_Fauna`) ON UPDATE CASCADE;

--
-- Constraints for table `GUARDIAPARCO`
--
ALTER TABLE `GUARDIAPARCO`
  ADD CONSTRAINT `GUARDIAPARCO_ibfk_1` FOREIGN KEY (`Parco_Assegnato`) REFERENCES `PARCO` (`Nome_Parco`) ON UPDATE CASCADE;

--
-- Constraints for table `PERMANENZA`
--
ALTER TABLE `PERMANENZA`
  ADD CONSTRAINT `PERMANENZA_ibfk_1` FOREIGN KEY (`Nome_Parco`) REFERENCES `PARCO` (`Nome_Parco`) ON UPDATE CASCADE,
  ADD CONSTRAINT `PERMANENZA_ibfk_2` FOREIGN KEY (`Nome_Specie_Fauna`,`Nome_Esemplare`) REFERENCES `ESEMPLARE` (`Nome_Specie_Fauna`, `Nome_Esemplare`) ON UPDATE CASCADE;

--
-- Constraints for table `VISITA_MEDICA`
--
ALTER TABLE `VISITA_MEDICA`
  ADD CONSTRAINT `VISITA_MEDICA_ibfk_1` FOREIGN KEY (`Matricola_Vet`) REFERENCES `VETERINARIO` (`Matricola`) ON UPDATE CASCADE,
  ADD CONSTRAINT `VISITA_MEDICA_ibfk_2` FOREIGN KEY (`Nome_Specie_Fauna`,`Nome_Esemplare`) REFERENCES `ESEMPLARE` (`Nome_Specie_Fauna`, `Nome_Esemplare`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
