## TODO

### (Sicurezza)


* Gestisce tutti gli errori lanciati dalle servlet.
* Reindirizza l'utente a una pagina di errore per ogni JSP che può generare errori.

**Prevenzione da SQLi:**

* -Sanifica i campi in `login.jsp`.
* Evita la manipolazione di URL da parte dell'admin.
* Ricostruisce la funzione `UserValidation` in `LoginDAO` per verificare le credenziali senza parametri in chiaro.

**CSRF Token per la verifica della sessione:**


** (fabio) Ricostruzione del caricamento foto per XSS:**

* Seguire l'esempio della lezione 10 su elearning.

### (Refactoring)

** - Sostituire riferimenti di script e stili:**
* CSP policy (usa hash e nonce).
* Decidere la policy CSP.

**Sostituire riferimenti a pagine JSP:**

* +Rimuovere riferimenti a pagine non funzionanti.

**Implementare funzioni mancanti nel DAO:**

* Carrello, Catalogo , Ordini.

**


### (Cose Da Implementare)

**Pagina Impostazioni Account Utente:**

* Modifica dati utente.
    * Cambio password.
    * Cancellazione account.
    * Modifica indirizzo fatturazione/spedizione.
    * Gestione indirizzi di spedizione salvati.

**Sistema di Gestione Utenti lato Admin:**
  * OP Admin.
  * Rimozione Utente.

**Pagina Singolo Prodotto:**

* Aggiungere parametro alla Tabelle Categoria per JSON/XML di attributi aggiuntivi.


**Sistema di Salvataggio Preferiti:**
* Pagina Wishlist.jsp (copiata da ordini.jsp)
* Sessione per oggetti di utente registrato.

**Sistema Iscrizione Newsletter (Opzionale):**

**Sistema Visualizzazione Ordini lato Utente:**
 * Da completare.
 * Generare fattura per ogni ordine.
 * Possibilità di annullare l'ordine a fattura non emessa.

**Sistema Gestione Ordini lato Admin:**	
	* Visualizzare ordini con filtro sullo stato dell'ordine.
	* Generare la fattura.
	* Cancellare ordini dal database. 
	+ Aggiungere un controllo sulla data, con warning.
	
**Sistema di ricerca Prodotti in Catalogo.jsp:**
 	* Usare Isotope.
 		
**Sistema di checkout:**
  
  *Richiedere l'indirizzo di spedizione.
  *Usare l'API di Paypal Sandbox.	
  
  
  