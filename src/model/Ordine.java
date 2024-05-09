package model;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Ordine implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final Map<Prodotto, Integer> prodotti;
	private final double ScontoCoupon;
	private final String Ragione_Sociale;
	private final Indirizzo Address;
	private final String Data_Ordine;

	public Ordine() {
		this.ScontoCoupon = 0.0d;
		this.prodotti = new HashMap<Prodotto, Integer>();
		this.Ragione_Sociale = null;
		this.Address = null;
		this.Data_Ordine = ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy.mm.ss"));
	}
	
	public Ordine(Map<Prodotto, Integer> prodotti, String Ragione_Sociale, double ScontoCoupon, Indirizzo Address) {
	    this.prodotti = prodotti;
	    this.Ragione_Sociale = Ragione_Sociale;
	    this.ScontoCoupon = ScontoCoupon;
	    this.Address = Address;
	    this.Data_Ordine = ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy.mm.ss"));
	  }
	
	public Map<Prodotto, Integer> getProdotti() {
		return prodotti;
	}

	public double getScontoCoupon() {
		return ScontoCoupon;
	}

	public String getRagione_Sociale() {
		return Ragione_Sociale;
	}

	public Indirizzo getAddress() {
		return Address;
	}

	public String getData_Ordine() {
		return Data_Ordine;
	}

}

