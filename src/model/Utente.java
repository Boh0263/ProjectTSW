package model;
import java.io.*;

public class Utente implements Serializable {
    
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
    
    public Utente() {
    	throw new IllegalArgumentException();
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
    	this.tipo = "Completo";
    	this.nome = nome;
    	this.cognome = cognome;
    	this.CF = CF;
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

    
    

    
}