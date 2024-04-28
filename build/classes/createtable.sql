CREATE DATABASE IF NOT EXISTS Kiitz;
Use Kiitz;

CREATE TABLE IF NOT EXISTS Utente(
  ID INT auto_increment PRIMARY KEY,
  Username VARCHAR(255) NOT NULL UNIQUE,
  Password VARCHAR(255) NOT NULL,
  Nome VARCHAR(255),
  Cognome VARCHAR(255),
  Data_nascita DATE,
  Email VARCHAR(255) UNIQUE,
  Tipo ENUM('R','NR')
);

CREATE TABLE IF NOT EXISTS Indirizzo (
  ID INT auto_increment PRIMARY KEY,
  Utente_ID INT,
  Indirizzo_breve VARCHAR(255),
  CAP VARCHAR(255),
  Località VARCHAR(255),
  Provincia VARCHAR(255),
  Nazione VARCHAR(255),
  FOREIGN KEY (Utente_ID) REFERENCES Utente(ID)
);

CREATE TABLE IF NOT EXISTS Ordine (
ID INT auto_increment PRIMARY KEY,
Ragione_sociale VARCHAR(35) NOT NULL,
Indirizzo_breve VARCHAR(50) NOT NULL,
Imposta DECIMAL(10,2),
Sconto DECIMAL(10,2),
Totale DECIMAL(10,4) NOT NULL,
Stato VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS Prodotto (
 Nome VARCHAR(255) PRIMARY KEY,
 Prezzo DECIMAL(10,3) NOT NULL,
 IVA DECIMAL(10,2) NOT NULL,
 Giacenza INT DEFAULT 0,
 Descrizione VARCHAR(255) NOT NULL,
 img1 VARCHAR(255) NOT NULL,
 img2 VARCHAR(255) NOT NULL,
 img3 VARCHAR(255) NOT NULL
 );
 
CREATE TABLE IF NOT EXISTS Contenuto (
Riassunto VARCHAR(255) NOT NULL,
Prezzo_Lordo DECIMAL(10,4) NOT NULL,
Qta INT NOT NULL,
ID_Ordine INT NOT NULL,
ID_Prodotto VARCHAR(255) NOT NULL,
FOREIGN KEY (ID_Ordine) REFERENCES Ordine(ID),
FOREIGN KEY (ID_Prodotto) REFERENCES Prodotto(Nome),
PRIMARY KEY (ID_Ordine, ID_Prodotto)
);

CREATE TABLE IF NOT EXISTS Arma (
  ID_Arma VARCHAR(255) PRIMARY KEY,
  Materiale VARCHAR(255),
  Tipo ENUM('Melee', 'Ranged'),
  Utilizzo ENUM('War', 'Hunting', 'Decorative'),
  ID_Prodotto VARCHAR(255) NOT NULL,
  FOREIGN KEY (ID_Prodotto) REFERENCES Prodotto(Nome)
);

CREATE TABLE IF NOT EXISTS Accessorio (
  ID_Accessorio VARCHAR(255) PRIMARY KEY,
  ID_Prodotto VARCHAR(255) NOT NULL,
  FOREIGN KEY (ID_Prodotto) REFERENCES Prodotto(Nome)
);
Use kiitz;
CREATE TABLE IF NOT EXISTS Abbigliamento (
  ID_Abbigliamento INT PRIMARY KEY AUTO_INCREMENT,
  ID_Prodotto VARCHAR(255) NOT NULL,
  Tipo ENUM('Maglietta', 'Pantalone', 'Calzatura'),
  Materiale VARCHAR(255),
  FOREIGN KEY (ID_Prodotto) REFERENCES Prodotto(Nome)
);


