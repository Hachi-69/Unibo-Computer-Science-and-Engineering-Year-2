CREATE DATABASE IF NOT EXISTS parchi_naturali;
USE parchi_naturali;

CREATE TABLE PARCO (
    Nome_Parco VARCHAR(100) PRIMARY KEY,
    Regione VARCHAR(50) NOT NULL,
    Superficie INT NOT NULL
);

CREATE TABLE SPECIE_FLORA (
    Nome_Specie_Flora VARCHAR(100) PRIMARY KEY,
    Tipo VARCHAR(50) NOT NULL,
    Stagione_Fioritura VARCHAR(50) NOT NULL
);

CREATE TABLE SPECIE_FAUNA (
    Nome_Specie_Fauna VARCHAR(100) PRIMARY KEY,
    Ordine VARCHAR(50) NOT NULL,
    Mesi_Adulto INT NOT NULL,
    Rischio_Estinzione ENUM('Rischio minimo', 'Vulnerabile', 'In pericolo', 'Critico', 'Estinto in natura') NOT NULL
);

CREATE TABLE ALIMENTO (
    Nome_Alimento VARCHAR(100) PRIMARY KEY,
    Categoria VARCHAR(50) NOT NULL
);

CREATE TABLE VETERINARIO (
    Matricola VARCHAR(20) PRIMARY KEY,
    Nome VARCHAR(50) NOT NULL,
    Cognome VARCHAR(50) NOT NULL,
    Telefono VARCHAR(20),
    Numero_Albo VARCHAR(50) UNIQUE NOT NULL,
    Specializzazione VARCHAR(100) NOT NULL
);

CREATE TABLE GUARDIAPARCO (
    Matricola VARCHAR(20) PRIMARY KEY,
    Nome VARCHAR(50) NOT NULL,
    Cognome VARCHAR(50) NOT NULL,
    Telefono VARCHAR(20),
    Parco_Assegnato VARCHAR(100) NOT NULL,
    FOREIGN KEY (Parco_Assegnato) REFERENCES PARCO(Nome_Parco) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE Cresce (
    Nome_Parco VARCHAR(100),
    Nome_Specie_Flora VARCHAR(100),
    PRIMARY KEY (Nome_Parco, Nome_Specie_Flora),
    FOREIGN KEY (Nome_Parco) REFERENCES PARCO(Nome_Parco) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Nome_Specie_Flora) REFERENCES SPECIE_FLORA(Nome_Specie_Flora) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Dieta (
    Nome_Alimento VARCHAR(100),
    Nome_Specie_Fauna VARCHAR(100),
    Fascia_Eta VARCHAR(50),
    PRIMARY KEY (Nome_Specie_Fauna, Nome_Alimento, Fascia_Eta),
    FOREIGN KEY (Nome_Specie_Fauna) REFERENCES SPECIE_FAUNA(Nome_Specie_Fauna) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Nome_Alimento) REFERENCES ALIMENTO(Nome_Alimento) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ESEMPLARE (
    Nome_Specie_Fauna VARCHAR(100),
    Nome_Esemplare VARCHAR(100),
    Sesso ENUM('M', 'F') NOT NULL,
    Data_Nascita DATE,
    Stato_Salute VARCHAR(50) NOT NULL,
    Totale_Visite_Subite INT DEFAULT 0,
    PRIMARY KEY (Nome_Specie_Fauna, Nome_Esemplare),
    FOREIGN KEY (Nome_Specie_Fauna) REFERENCES SPECIE_FAUNA(Nome_Specie_Fauna) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE PERMANENZA (
    Nome_Parco VARCHAR(100),
    Nome_Specie_Fauna VARCHAR(100),
    Nome_Esemplare VARCHAR(100),
    Data_Inizio DATE,
    Data_Fine DATE DEFAULT NULL,
    Modalita_Ingresso VARCHAR(50) NOT NULL,
    PRIMARY KEY (Nome_Parco, Nome_Specie_Fauna, Nome_Esemplare, Data_Inizio),
    FOREIGN KEY (Nome_Parco) REFERENCES PARCO(Nome_Parco) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (Nome_Specie_Fauna, Nome_Esemplare) REFERENCES ESEMPLARE(Nome_Specie_Fauna, Nome_Esemplare) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE VISITA_MEDICA (
    Matricola_Vet VARCHAR(20),
    Nome_Specie_Fauna VARCHAR(100),
    Nome_Esemplare VARCHAR(100),
    Data DATE,
    Ora TIME,
    Esito VARCHAR(50) NOT NULL,
    Terapia_Prescritta VARCHAR(255),
    PRIMARY KEY (Matricola_Vet, Nome_Specie_Fauna, Nome_Esemplare, Data, Ora),
    FOREIGN KEY (Matricola_Vet) REFERENCES VETERINARIO(Matricola) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (Nome_Specie_Fauna, Nome_Esemplare) REFERENCES ESEMPLARE(Nome_Specie_Fauna, Nome_Esemplare) ON UPDATE CASCADE ON DELETE RESTRICT
);
