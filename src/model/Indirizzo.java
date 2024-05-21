package model;

import java.io.Serializable;

public class Indirizzo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String via;
	private String citta;
	private String provincia;
	private String cap;
	
	public Indirizzo(String via, String citta, String provincia, String cap) {
        this.via = via;
        this.citta = citta;
        this.provincia = provincia;
        this.cap = cap;
    }
	public Indirizzo(String unica) {
		String[] parti = unica.split(",");
        this.via = parti[0];
        this.citta = parti[1];
        this.provincia = parti[2];
        this.cap = parti[3];
	}
	
	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String getCap() {
		return cap;
	}
	
	public void setCap(String cap) {
		this.cap = cap;
	}
	
	@Override
	public String toString() {
		return via + ", " + citta + ", " + provincia + ", " + cap;
	}

}
