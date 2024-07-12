package model;
import java.io.*;

public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Utente [id=" + id + ", username=" + username + ", password=" + password + ", nome=" + nome
				+ ", cognome=" + cognome + ", CF=" + CF + ", email=" + email + ", tipo=" + tipo + "]";
	}

	private final int id;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String CF;
    private String email;
    private String tipo;
    private String dataNascita;
    private String telefono;
    private Indirizzo indirizzo;
    
    public Utente() {
    	throw new IllegalArgumentException();
    }
    
    public Utente(String username, String tipo) { //Usato per l'operazione di OP / DEOP
		this.id = -1;
		this.username = username;
		this.password = null;
		this.nome = null;
		this.cognome = null;
		this.CF = null;
		this.email = null;
		this.tipo = tipo;
    	
    	
    }
    
    public Utente(int id, String username, String password, String email) {
    	this.id = id;
    	this.username = username;
    	this.password = password;
    	this.email = email;
    	this.tipo = "Non Completo";
    }
    
    
    public Utente(int id, String username, String password, String nome, String cognome, String CF, String email) {
    	this.id = id;
    	this.username = username;
    	this.password = password;
    	this.email = email;
    	this.tipo = "R";
    	this.nome = nome;
    	this.cognome = cognome;
    	this.CF = CF;
    }
    
    //constructor without id
	public Utente(String username, String password, String nome, String cognome, String CF, String email) {
		this.id = -1;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.CF = CF;
		this.email = email;
		this.tipo = "R";
	}
	
	//constructor with everything  that was added
	public Utente(int id, String username, String password, String nome, String cognome, String CF, String email,
			String tipo, String dataNascita, String telefono, Indirizzo indirizzo) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.CF = CF;
		this.email = email;
		this.tipo = tipo;
		this.dataNascita = dataNascita;
		this.telefono = telefono;
		this.indirizzo = indirizzo;
	}
	
	//same constructor without id 
	public Utente(String username, String password, String nome, String cognome, String CF, String email, String tipo, String dataNascita, String telefono, Indirizzo indirizzo) {
		this.id = -1;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.CF = CF;
		this.email = email;
		this.tipo = tipo;
		this.dataNascita = dataNascita;
		this.telefono = telefono;
		this.indirizzo = indirizzo;
	}
	
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCF() {
        return CF;
    }

    public String getEmail() {
        return email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getDataNascita() {
		return dataNascita;
	}
	
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Indirizzo getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}
	

    
    

    
}