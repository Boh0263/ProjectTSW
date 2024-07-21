USE Kiitz;
GRANT FILE ON *.* TO 'root'@'localhost';
-- Inserting example users into Utente table
INSERT INTO Utente (Username, Password, Nome, Cognome, CF, Email, Tipo) VALUES
('user1', 'password1', 'John', 'Doe', 'ABC12345', 'john.doe@example.com', 'R'),
('user2', 'password2', 'Jane', 'Smith', 'XYZ67890', 'jane.smith@example.com', 'NR'),
('admin', 'adminpassword', 'Admin', 'User', 'ADMIN123', 'admin@example.com', 'A');

-- Inserting example addresses into Indirizzo table
INSERT INTO Indirizzo (Utente_ID, Indirizzo_breve, CAP, Località, Provincia, Nazione) VALUES
(1, 'Via Roma 123', '00100', 'Roma', 'RM', 'Italia'),
(2, 'Broadway St 456', '10001', 'New York', 'NY', 'USA'),
(3, 'Admin Lane 789', '12345', 'Admin City', 'AC', 'Adminland');

-- Inserting example orders into Ordine table
INSERT INTO Ordine (Ragione_sociale, Indirizzo_breve, Imposta, Sconto, Totale, Data_Ordine, Stato) VALUES
('Company A', 'Via Verdi 456', 12.50, 2.00, 100.00, '2024-07-01', 'NON EVASO'),
('Company B', 'Boulevard de Paris 789', 15.00, 3.00, 120.00, '2024-07-02', 'NON EVASO');

-- Inserting example images into Immagine table
INSERT INTO Immagine (Placeholder, MimeType, Content) VALUES 
('product_2.png', 'image/png', LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/product_2.png')),
('product_2.png', 'image/png', LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/product_2.png')),
('product_2.png', 'image/png', LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/product_2.png'));

-- Inserting example products into Prodotto table
INSERT INTO Prodotto (Nome, Prezzo, IVA, Giacenza, Descrizione, img1, img2, img3) VALUES
('Product A', 50.00, 22.00, 100, 'Description of Product A', 1, 2, 3),
('Product B', 75.00, 22.00, 50, 'Description of Product B', 1, 2, 3);

-- Inserting example order contents into Contenuto table
INSERT INTO Contenuto (Prezzo_Lordo, Qta, ID_Ordine, ID_Prodotto) VALUES
(50.00, 2, 1, 'Product A'),
(75.00, 1, 2, 'Product B');

-- Inserting example weapons into Arma table
INSERT INTO Arma (Materiale, Tipo, Utilizzo, ID_Prodotto) VALUES
('Steel', 'Melee', 'War', 'Product A'),
('Wood', 'Ranged', 'Hunting', 'Product B');

-- Inserting example accessories into Accessorio table
INSERT INTO Accessorio (ID_Prodotto) VALUES
('Product A'),
('Product B');

-- Inserting example clothing into Abbigliamento table
INSERT INTO Abbigliamento (ID_Prodotto, Tipo, Genere) VALUES
('Product A', 'Maglietta', 'U'),
('Product B', 'Pantalone', 'F');

-- Inserting example armor into Armatura table
INSERT INTO Armatura (Materiale, Pezzo, ID_Prodotto) VALUES
('Ferro', 'Helmet', 'Product A'),
('Maglia', 'Chestplate', 'Product B');

-- Inserting example reviews into Recensione table
INSERT INTO Recensione (ID_Prodotto, Email_Utente, Votazione, Commento, Data_Recensione) VALUES
('Product A', 'john.doe@example.com', 4, 'Good product', '2024-07-03'),
('Product B', 'jane.smith@example.com', 5, 'Excellent!', '2024-07-04');