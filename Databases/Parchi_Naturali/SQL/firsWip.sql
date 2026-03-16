-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 30, 2024 at 06:38 PM
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
-- Database: `turilloparchinaturali`
--

-- --------------------------------------------------------

--
-- Table structure for table `fauna`
--

CREATE TABLE `fauna` (
  `fau_id` int(11) NOT NULL,
  `fau_dex` varchar(30) NOT NULL,
  `fau_sesso` enum('M','F') DEFAULT NULL,
  `fau_stato_salute` enum('S','M') DEFAULT NULL,
  `fau_data_nascita` date DEFAULT NULL,
  `fk_spf_id` int(11) DEFAULT NULL,
  `fk_prc_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fauna`
--

INSERT INTO `fauna` (`fau_id`, `fau_dex`, `fau_sesso`, `fau_stato_salute`, `fau_data_nascita`, `fk_spf_id`, `fk_prc_id`) VALUES
(1, 'orso', 'M', 'S', '2019-01-01', 4, 1),
(2, 'tigre', 'M', 'S', '2018-06-15', 2, 2),
(3, 'gorilla', 'F', 'M', '2020-03-10', 3, 3),
(4, 'orso', 'M', 'S', '2017-11-20', 4, 4),
(5, 'lupo', 'F', 'S', '2016-08-05', 5, 5),
(6, 'balena', 'M', 'M', '2020-07-12', 6, 6),
(7, 'squalo', 'F', 'S', '2015-09-30', 7, 7),
(8, 'pappagallo', 'M', 'S', '2018-04-25', 8, 8),
(9, 'gufo', 'M', 'M', '2019-10-18', 9, 9),
(10, 'picchio', 'F', 'S', '2020-12-05', 10, 10),
(11, 'serpente', 'F', 'S', '2017-05-20', 11, 1),
(12, 'tartaruga', 'M', 'M', '2016-08-10', 12, 2),
(13, 'rana', 'F', 'S', '2020-02-15', 13, 3),
(14, 'farfalla', 'M', 'S', '2018-11-30', 14, 4),
(15, 'ape', 'F', 'M', '2019-04-05', 15, 5),
(16, 'ragno', 'M', 'S', '2020-07-20', 16, 6),
(17, 'granchio', 'F', 'S', '2018-09-10', 17, 7),
(18, 'lumaca', 'M', 'M', '2017-12-25', 18, 8),
(19, 'medusa', 'M', 'S', '2019-11-05', 19, 9),
(20, 'stellamarina', 'F', 'S', '2020-03-30', 20, 10),
(21, 'serpente', 'M', 'S', '2012-09-01', 11, 10),
(23, 'cobra', 'F', 'M', '2016-03-01', 24, 2),
(24, 'orso', 'M', 'M', '2019-03-01', 4, 7),
(25, 'medusa', 'M', 'M', '2015-04-01', 19, 4),
(26, 'balena', 'M', 'M', '2011-11-01', 6, 8),
(27, 'salmone', 'M', 'M', '2010-12-01', 26, 8),
(29, 'tigre', 'M', 'S', '2018-03-12', 21, 1),
(30, 'pinguino', 'F', 'S', '2020-05-25', 22, 2),
(31, 'aquila', 'M', 'M', '2019-08-17', 23, 3),
(32, 'cobra', 'F', 'S', '2017-11-08', 24, 4),
(34, 'tigre', 'M', 'S', '2018-03-12', 21, 1),
(35, 'pinguino', 'F', 'S', '2020-05-25', 22, 2),
(36, 'aquila', 'M', 'M', '2019-08-17', 23, 3),
(37, 'cobra', 'F', 'S', '2017-11-08', 24, 4),
(39, 'tigre', 'M', 'S', '2018-03-12', 21, 1),
(40, 'pinguino', 'F', 'S', '2020-05-25', 22, 2),
(41, 'aquila', 'M', 'M', '2019-08-17', 23, 3),
(42, 'cobra', 'F', 'S', '2017-11-08', 24, 4),
(44, 'tigre', 'M', 'S', '2018-07-10', 21, 1),
(45, 'pinguino', 'M', 'S', '2019-02-28', 22, 2),
(46, 'aquila', 'F', 'M', '2017-10-15', 23, 3),
(47, 'cobra', 'M', 'S', '2018-04-10', 24, 4);

-- --------------------------------------------------------

--
-- Table structure for table `flora`
--

CREATE TABLE `flora` (
  `flo_id` int(11) NOT NULL,
  `flo_dex` varchar(25) DEFAULT NULL,
  `flo_stag_fiori` enum('estate','primavera','autunno','inverno') DEFAULT NULL,
  `fk_spo_id` int(11) DEFAULT NULL,
  `fk_prc_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `flora`
--

INSERT INTO `flora` (`flo_id`, `flo_dex`, `flo_stag_fiori`, `fk_spo_id`, `fk_prc_id`) VALUES
(1, 'Pino', 'estate', 1, 1),
(2, 'Quercia', 'primavera', 2, 2),
(3, 'Betulla', 'autunno', 3, 3),
(4, 'Biancospino', 'inverno', 19, 4),
(5, 'Girasole', 'estate', 33, 5),
(6, 'Primula', 'primavera', 42, 6),
(7, 'Orchidea', 'estate', 40, 7),
(8, 'Lavanda', 'estate', 36, 8),
(9, 'Anemone', 'primavera', 30, 9),
(10, 'Campanella', 'estate', 38, 10),
(11, 'Pino', 'estate', 1, 1),
(12, 'Quercia', 'primavera', 2, 2),
(13, 'Betulla', 'autunno', 3, 3),
(14, 'Faggio', 'inverno', 4, 4),
(15, 'Abete', 'estate', 5, 5),
(16, 'Cedro', 'primavera', 6, 6),
(17, 'Acero', 'autunno', 7, 7),
(18, 'Olmo', 'inverno', 8, 8),
(19, 'Salice', 'estate', 9, 9),
(20, 'Ginepro', 'primavera', 10, 10),
(21, 'Larice', 'autunno', 11, 1),
(22, 'Ontano', 'inverno', 12, 2),
(23, 'Castagno', 'estate', 13, 3),
(24, 'Ciliegio', 'primavera', 14, 4),
(25, 'Melo', 'autunno', 15, 5),
(26, 'Pesco', 'inverno', 16, 6),
(27, 'Prugno', 'estate', 17, 7),
(28, 'Susino', 'primavera', 18, 8),
(29, 'Biancospino', 'autunno', 19, 9),
(30, 'Ginepro comune', 'inverno', 20, 10),
(31, 'Pungitopo', 'estate', 21, 1),
(32, 'Corbezzolo', 'primavera', 22, 2),
(33, 'Erica', 'autunno', 23, 3),
(34, 'Alloro', 'inverno', 24, 4),
(35, 'Millefoglie', 'estate', 25, 5),
(36, 'Mirto', 'primavera', 26, 6),
(37, 'Rovo', 'autunno', 27, 7),
(38, 'Sambuco', 'inverno', 28, 8),
(39, 'Ligustro', 'estate', 29, 9),
(40, 'Anemone', 'primavera', 30, 10),
(41, 'Tulipano', 'autunno', 31, 1),
(42, 'Ortensia', 'inverno', 32, 2),
(43, 'Girasole', 'estate', 33, 3),
(44, 'Fiordaliso', 'primavera', 34, 4),
(45, 'Margherita', 'autunno', 35, 5),
(46, 'Lavanda', 'inverno', 36, 6),
(47, 'Geranio', 'estate', 37, 7),
(48, 'Campanella', 'primavera', 38, 8),
(49, 'Bocca di leone', 'autunno', 39, 9),
(50, 'Orchidea', 'inverno', 40, 10),
(51, 'Narciso', 'estate', 41, 1),
(52, 'Primula', 'primavera', 42, 2),
(53, 'Lillium', 'autunno', 43, 3),
(54, 'Iris', 'inverno', 44, 4),
(55, 'Ciclamino', 'estate', 45, 5),
(56, 'Peonia', 'primavera', 46, 6),
(57, 'Dalia', 'autunno', 47, 7),
(58, 'Amaranto', 'inverno', 48, 8),
(59, 'Anemone', 'estate', 49, 9);

-- --------------------------------------------------------

--
-- Table structure for table `floravive`
--

CREATE TABLE `floravive` (
  `fk_flo_id` int(11) DEFAULT NULL,
  `fk_prc_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `guardiani`
--

CREATE TABLE `guardiani` (
  `grd_id` int(11) NOT NULL,
  `grd_usr` varchar(50) NOT NULL,
  `grd_psw` varchar(50) NOT NULL,
  `fk_prc_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ordinifauna`
--

CREATE TABLE `ordinifauna` (
  `orf_id` int(11) NOT NULL,
  `orf_dex` varchar(23) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ordinifauna`
--

INSERT INTO `ordinifauna` (`orf_id`, `orf_dex`) VALUES
(1, 'mammiferi '),
(2, 'uccelli'),
(3, 'rettili'),
(4, 'anfibi'),
(5, 'pesci'),
(6, 'insetti'),
(7, 'aracnidi'),
(8, 'crostacei'),
(9, 'molluschi'),
(10, 'echinodermi');

-- --------------------------------------------------------

--
-- Table structure for table `parchi`
--

CREATE TABLE `parchi` (
  `prc_id` int(11) NOT NULL,
  `prc_dex` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `parchi`
--

INSERT INTO `parchi` (`prc_id`, `prc_dex`) VALUES
(1, 'Parco Nazionale del Gran Paradiso'),
(2, 'Parco Nazionale dei Monti Sibillini'),
(3, 'Parco Nazionale delle Cinque Terre'),
(4, 'Parco Nazionale del Gargano'),
(5, 'Parco Nazionale d Abruzzo, Lazio e Molise'),
(6, 'Parco Nazionale del Vesuvio'),
(7, 'Parco Nazionale d Aspromonte'),
(8, 'Parco Nazionale dell Arcipelago di La Maddalena'),
(9, 'Parco Nazionale delle Foreste Casentinesi'),
(10, 'Parco Nazionale dello Stelvio');

-- --------------------------------------------------------

--
-- Table structure for table `speciefauna`
--

CREATE TABLE `speciefauna` (
  `spf_id` int(11) NOT NULL,
  `spf_dex` varchar(23) DEFAULT NULL,
  `spf_mesi_adulto` int(11) DEFAULT NULL,
  `spf_estinzione` tinyint(1) DEFAULT NULL,
  `fk_orf_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `speciefauna`
--

INSERT INTO `speciefauna` (`spf_id`, `spf_dex`, `spf_mesi_adulto`, `spf_estinzione`, `fk_orf_id`) VALUES
(1, 'elefante', 240, 0, 1),
(2, 'tigre', 36, 1, 1),
(3, 'gorilla', 120, 0, 1),
(4, 'orso', 48, 0, 1),
(5, 'lupo', 12, 1, 1),
(6, 'balena', 480, 1, 5),
(7, 'squalo', 36, 0, 5),
(8, 'pappagallo', 12, 1, 2),
(9, 'gufo', 6, 0, 2),
(10, 'picchio', 3, 0, 2),
(11, 'serpente', 24, 1, 3),
(12, 'tartaruga', 180, 0, 3),
(13, 'rana', 12, 0, 4),
(14, 'farfalla', 1, 0, 6),
(15, 'ape', 3, 1, 6),
(16, 'ragno', 6, 0, 7),
(17, 'granchio', 6, 0, 8),
(18, 'lumaca', 12, 0, 9),
(19, 'medusa', 24, 1, 10),
(20, 'stellamarina', 12, 0, 10),
(21, 'tigre', 48, 1, 1),
(22, 'pinguino', 18, 0, 2),
(23, 'aquila', 60, 1, 2),
(24, 'cobra', 36, 1, 3),
(26, 'salmone', 18, 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `specieflora`
--

CREATE TABLE `specieflora` (
  `spo_id` int(11) NOT NULL,
  `spo_dex` varchar(25) DEFAULT NULL,
  `spo_tipo` enum('albero','arbusto','pianta erbacea') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `specieflora`
--

INSERT INTO `specieflora` (`spo_id`, `spo_dex`, `spo_tipo`) VALUES
(1, 'Pino', 'albero'),
(2, 'Quercia', 'albero'),
(3, 'Betulla', 'albero'),
(4, 'Faggio', 'albero'),
(5, 'Abete', 'albero'),
(6, 'Cedro', 'albero'),
(7, 'Acero', 'albero'),
(8, 'Olmo', 'albero'),
(9, 'Salice', 'albero'),
(10, 'Ginepro', 'albero'),
(11, 'Larice', 'albero'),
(12, 'Ontano', 'albero'),
(13, 'Castagno', 'albero'),
(14, 'Ciliegio', 'albero'),
(15, 'Melo', 'albero'),
(16, 'Pesco', 'albero'),
(17, 'Prugno', 'albero'),
(18, 'Susino', 'albero'),
(19, 'Biancospino', 'arbusto'),
(20, 'Ginepro comune', 'arbusto'),
(21, 'Pungitopo', 'arbusto'),
(22, 'Corbezzolo', 'arbusto'),
(23, 'Erica', 'arbusto'),
(24, 'Alloro', 'arbusto'),
(25, 'Millefoglie', 'arbusto'),
(26, 'Mirto', 'arbusto'),
(27, 'Rovo', 'arbusto'),
(28, 'Sambuco', 'arbusto'),
(29, 'Ligustro', 'arbusto'),
(30, 'Anemone', 'pianta erbacea'),
(31, 'Tulipano', 'pianta erbacea'),
(32, 'Ortensia', 'pianta erbacea'),
(33, 'Girasole', 'pianta erbacea'),
(34, 'Fiordaliso', 'pianta erbacea'),
(35, 'Margherita', 'pianta erbacea'),
(36, 'Lavanda', 'pianta erbacea'),
(37, 'Geranio', 'pianta erbacea'),
(38, 'Campanella', 'pianta erbacea'),
(39, 'Bocca di leone', 'pianta erbacea'),
(40, 'Orchidea', 'pianta erbacea'),
(41, 'Narciso', 'pianta erbacea'),
(42, 'Primula', 'pianta erbacea'),
(43, 'Lillium', 'pianta erbacea'),
(44, 'Iris', 'pianta erbacea'),
(45, 'Ciclamino', 'pianta erbacea'),
(46, 'Peonia', 'pianta erbacea'),
(47, 'Dalia', 'pianta erbacea'),
(48, 'Amaranto', 'pianta erbacea'),
(49, 'Anemone', 'pianta erbacea');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fauna`
--
ALTER TABLE `fauna`
  ADD PRIMARY KEY (`fau_id`),
  ADD KEY `fk_spf_id` (`fk_spf_id`),
  ADD KEY `fk_prc_id` (`fk_prc_id`);

--
-- Indexes for table `flora`
--
ALTER TABLE `flora`
  ADD PRIMARY KEY (`flo_id`),
  ADD KEY `fk_prc_id` (`fk_prc_id`),
  ADD KEY `fk_spo_id` (`fk_spo_id`);

--
-- Indexes for table `floravive`
--
ALTER TABLE `floravive`
  ADD KEY `fk_flo_id` (`fk_flo_id`),
  ADD KEY `fk_prc_id` (`fk_prc_id`);

--
-- Indexes for table `guardiani`
--
ALTER TABLE `guardiani`
  ADD PRIMARY KEY (`grd_id`),
  ADD UNIQUE KEY `grd_usr` (`grd_usr`),
  ADD KEY `fk_prc_id` (`fk_prc_id`);

--
-- Indexes for table `ordinifauna`
--
ALTER TABLE `ordinifauna`
  ADD PRIMARY KEY (`orf_id`);

--
-- Indexes for table `parchi`
--
ALTER TABLE `parchi`
  ADD PRIMARY KEY (`prc_id`);

--
-- Indexes for table `speciefauna`
--
ALTER TABLE `speciefauna`
  ADD PRIMARY KEY (`spf_id`),
  ADD KEY `fk_orf_id` (`fk_orf_id`);

--
-- Indexes for table `specieflora`
--
ALTER TABLE `specieflora`
  ADD PRIMARY KEY (`spo_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fauna`
--
ALTER TABLE `fauna`
  MODIFY `fau_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT for table `flora`
--
ALTER TABLE `flora`
  MODIFY `flo_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT for table `guardiani`
--
ALTER TABLE `guardiani`
  MODIFY `grd_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ordinifauna`
--
ALTER TABLE `ordinifauna`
  MODIFY `orf_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `parchi`
--
ALTER TABLE `parchi`
  MODIFY `prc_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `speciefauna`
--
ALTER TABLE `speciefauna`
  MODIFY `spf_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `specieflora`
--
ALTER TABLE `specieflora`
  MODIFY `spo_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `fauna`
--
ALTER TABLE `fauna`
  ADD CONSTRAINT `fauna_ibfk_1` FOREIGN KEY (`fk_spf_id`) REFERENCES `speciefauna` (`spf_id`),
  ADD CONSTRAINT `fauna_ibfk_2` FOREIGN KEY (`fk_prc_id`) REFERENCES `parchi` (`prc_id`);

--
-- Constraints for table `flora`
--
ALTER TABLE `flora`
  ADD CONSTRAINT `flora_ibfk_1` FOREIGN KEY (`fk_prc_id`) REFERENCES `parchi` (`prc_id`),
  ADD CONSTRAINT `flora_ibfk_2` FOREIGN KEY (`fk_spo_id`) REFERENCES `specieflora` (`spo_id`);

--
-- Constraints for table `floravive`
--
ALTER TABLE `floravive`
  ADD CONSTRAINT `floravive_ibfk_1` FOREIGN KEY (`fk_flo_id`) REFERENCES `flora` (`flo_id`),
  ADD CONSTRAINT `floravive_ibfk_2` FOREIGN KEY (`fk_prc_id`) REFERENCES `parchi` (`prc_id`);

--
-- Constraints for table `guardiani`
--
ALTER TABLE `guardiani`
  ADD CONSTRAINT `guardiani_ibfk_1` FOREIGN KEY (`fk_prc_id`) REFERENCES `parchi` (`prc_id`);

--
-- Constraints for table `speciefauna`
--
ALTER TABLE `speciefauna`
  ADD CONSTRAINT `speciefauna_ibfk_1` FOREIGN KEY (`fk_orf_id`) REFERENCES `ordinifauna` (`orf_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
