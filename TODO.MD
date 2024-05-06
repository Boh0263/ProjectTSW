## TODO in formato MD

### Sicurezza

**Sistema di Error Handling con LOGGER (Opzionale):**

* Gestisce tutti gli errori lanciati dalle servlet.
* Reindirizza l'utente a una pagina di errore per ogni JSP che può generare errori.
* Nasconde all'utente i dettagli dell'errore.

**Prevenzione da SQLi:**

* Sanifica i campi per l'inserimento di nuovi prodotti in `add.jsp`.
* Sanifica i campi in `login.jsp`.
* Evita la manipolazione di URL da parte dell'admin.
* Ricostruisce la funzione `UserValidation` in `LoginDAO` per verificare le credenziali senza parametri in chiaro.

**CSRF Token per la verifica della sessione:**

* Anti-CSRF Token per la verifica delle sessioni (Opzionale).
* Sistema 2FA per admin con accesso da IP diverso (Opzionale) (modifica DB).

**Ricostruzione del caricamento foto per XSS:**

* Seguire l'esempio della lezione 10 su elearning.

### Refactoring

**Sostituire riferimenti di script e stili:**

* Usare path assoluti o relativi ai contesti delle pagine (usare `PageContext`).

**Sostituire riferimenti a pagine JSP:**

* Usare `ServletContext`.
* Rimuovere riferimenti a pagine non funzionanti.

**Nascondere nomi di Tabelle SQL:**

* Creare variabili statiche, finali e locali da concatenare alle stringhe.

**Implementare funzioni mancanti nel DAO:**

* Rimuovere `insert` dai DAO (implementato in `doSave`).

**Modificare pagine per la sessione utente:**

* Mostrare il numero di prodotti nel carrello.
* Mostrare username/nome utente nell'header (Opzionale).

### Da Implementare

**Pagina Impostazioni Account Utente:**

* Modifica dati utente.
    * Cambio password.
    * Cancellazione account.
    * Modifica indirizzo fatturazione/spedizione.
    * Gestione indirizzi di spedizione salvati.

**Sistema di Gestione Utenti lato Admin:**

**Pagina Singolo Prodotto:**

* Aggiungere parametro alla Tabella Prodotto per JSON/XML di attributi aggiuntivi.
* Aggiungere parametro alla Tabella Prodotto per file di presentazione XHTML/XML.
* Costruire la pagina `single.jsp` per accettare e mostrare file XHTML specifici.

**Sistema di Salvataggio Preferiti:**

* Cookie per preferenze utente generico.
* Cookie/sessione per oggetti di utente non registrato.
* Sessione per oggetti di utente registrato.

**Sistema Iscrizione Newsletter (Opzionale):**

**Sistema Visualizzazione Ordini lato Utente:**

**Sistema Gestione Ordini lato Admin:**
