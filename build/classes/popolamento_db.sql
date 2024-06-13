USE Kiitz;

INSERT INTO Utente(Username, Password, Nome, Cognome, CF, Email, Tipo)
VALUES ('user1', 'password1', 'John', 'Doe', 'CF1234', 'john.doe@example.com', 'R');

INSERT INTO Indirizzo(Utente_ID, Indirizzo_breve, CAP, Località, Provincia, Nazione)
VALUES (1, '123 Main St', '12345', 'City', 'Province', 'Country');

INSERT INTO Ordine(Ragione_sociale, Indirizzo_breve, Imposta, Sconto, Totale, Stato)
VALUES ('Company', '123 Main St', 10.00, 5.00, 100.00, 'NON EVASO');

INSERT INTO Prodotto(Nome, Prezzo, IVA, Giacenza, Descrizione, img1, img2, img3)
VALUES ('Product1', 10.00, 2.00, 100, 'This is a product', 'img1.jpg', 'img2.jpg', 'img3.jpg');

INSERT INTO Contenuto(Prezzo_Lordo, Qta, ID_Ordine, ID_Prodotto)
VALUES (12.00, 10, 1, 'Product1');

INSERT INTO Arma(ID_Arma, Materiale, Tipo, Utilizzo, ID_Prodotto)
VALUES ('Weapon1', 'Steel', 'Melee', 'War', 'Product1');

INSERT INTO Accessorio(ID_Accessorio, ID_Prodotto)
VALUES ('Accessory1', 'Product1');

INSERT INTO Abbigliamento(ID_Abbigliamento, ID_Prodotto, Tipo, Materiale)
VALUES (1, 'Product1', 'Maglietta', 'Cotton');