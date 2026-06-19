-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 20, 2026 at 01:47 AM
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
-- Database: `parchi_autenticazione`
--

-- --------------------------------------------------------

--
-- Table structure for table `UTENTI`
--

CREATE TABLE `UTENTI` (
  `Id_Utente` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Ruolo` enum('guardiaparco','veterinario','admin') NOT NULL,
  `Matricola_Dipendente` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `UTENTI`
--

INSERT INTO `UTENTI` (`Id_Utente`, `Username`, `Password`, `Ruolo`, `Matricola_Dipendente`) VALUES
(1, 'marco_ranger', '$2y$10$t80QCSIR0iHFlM29wQFsY.9SzeoQbSG3/3lBSAU5c3fEixCpOGEpK', 'guardiaparco', 'GRD003'),
(2, 'giulia_vet', '$2y$10$SC66AHbn6Q8J8GC10hKkTeTlBVa23JzXOYYnbGCkcrJNzof.nrrtS', 'veterinario', 'VET002'),
(3, 'admin_nazionale', '$2y$10$PAh0KGzhxsTtnyibu0XF3OTPVUUTZSbc.nghNjN15lGGO23IaZd4q', 'admin', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `UTENTI`
--
ALTER TABLE `UTENTI`
  ADD PRIMARY KEY (`Id_Utente`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `UTENTI`
--
ALTER TABLE `UTENTI`
  MODIFY `Id_Utente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
