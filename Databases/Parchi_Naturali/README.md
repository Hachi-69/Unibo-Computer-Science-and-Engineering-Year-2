# Sistema Gestionale Rete Parchi Naturali 🌲🐾

Progetto realizzato per il corso di **Basi di Dati** (A.A. 2025/2026) presso l'Università di Bologna, Campus di Cesena - Corso di Laurea in Ingegneria e Scienze Informatiche.

Questo applicativo web client-server permette la gestione centralizzata di una rete di parchi naturali regionali. Il sistema astrae la complessità di un database relazionale MySQL attraverso un'interfaccia utente dinamica, garantendo un accesso sicuro, controllato e transazionale alle informazioni per le diverse figure professionali dell'Ente.

## 🌟 Funzionalità Principali

* **🔐 Role-Based Access Control (RBAC):** Accesso differenziato basato sul principio del privilegio minimo. Il sistema instrada automaticamente l'utente verso i moduli di competenza:
  * **Amministratore:** Accesso globale e reportistica di Business Intelligence.
  * **Guardiaparco:** Gestione anagrafica e trasferimenti della fauna, consultazione flora.
  * **Veterinario:** Gestione delle cartelle cliniche e consultazione dei piani dietetici.
* **🐻 Gestione Fauna e Storicizzazione:** Tracciamento degli esemplari, trasferimenti tra parchi, decessi e rilasci in natura, con chiusura automatica delle permanenze per mantenere lo storico degli spostamenti.
* **🩺 Registro Clinico Transazionale:** Inserimento e modifica di visite mediche. Le operazioni critiche (come l'inserimento di un referto che aggiorna contemporaneamente lo stato di salute dell'animale) sono protette da **Transazioni ACID** (Commit/Rollback) lato server.
* **📊 Business Intelligence e AJAX:** L'amministratore dispone di una dashboard per interrogazioni complesse (calcolo indici di biodiversità, allerte sorveglianza parchi, specie a maggior rischio clinico). I report sono generati in background tramite **Fetch API (AJAX)** per aggiornare il DOM senza ricaricare la pagina.
* **🛡️ Sicurezza e Integrità:** Controlli a doppio livello (HTML/JS lato client e PHP lato server) per prevenire anomalie logiche e temporali (es. impossibilità di registrare visite per animali non ancora nati o già deceduti).

## 🛠️ Stack Tecnologico

* **Database:** MySQL (Relazionale, Tabelle InnoDB, Vincoli di integrità referenziale `CASCADE`/`RESTRICT`).
* **Backend:** PHP 8+ (Gestione sessioni, interrogazioni `mysqli`, elaborazione transazionale).
* **Frontend:** HTML5, CSS3 (Flexbox), JavaScript (Fetch API per manipolazione asincrona del DOM).

## 📁 Struttura della Repository

```text
📦 Parchi_Naturali
 ┣ 📂 SQL
 ┃ ┣ 📜 createTables.sql          # DDL per la creazione dello schema relazionale
 ┃ ┣ 📜 parchi_naturali.sql       # Dump del database (Struttura + Dati di test)
 ┃ ┗ 📜 parchi_autenticazione.sql # DDL e DML per le credenziali di accesso
 ┣ 📂 PHP-HTML
 ┃ ┣ 📜 index.htm                 # Entry point e form di Login
 ┃ ┣ 📜 dashboard.php             # Pannello di snodo RBAC
 ┃ ┣ 📜 statistiche.php           # Modulo Admin (Query complesse e chiamate AJAX)
 ┃ ┣ 📜 inserisci_visita.php      # Modulo Veterinario (Transazioni ACID)
 ┃ ┣ 📜 ...                       # (Altri script CRUD per Fauna, Flora e Visite)
 ┃ ┗ 📜 font.otf                  # Tipografia personalizzata per l'interfaccia
 ┣ 📜 Relazione Parchi Naturali.pdf # Documentazione completa del progetto
 ┣ 📜 ER.png                      # Schema Entità-Relazione
 ┗ 📜 Logic.png                   # Schema Logico Relazionale
```
 
## 🔑 Credenziali di Test (Demo)
* **Admin**: admin_nazionale / admin_nazionale

* **Veterinario**: giulia_vet / giulia_vet

* **Guardiaparco**: marco_ranger / marco_ranger

## 👨‍💻 Autore

### Luca Turillo
Studente di Ingegneria e Scienze Informatiche - Alma Mater Studiorum Università di Bologna
