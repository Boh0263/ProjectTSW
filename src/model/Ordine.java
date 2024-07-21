package model;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Ordine implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final int ID;
	private final Map<Prodotto, Integer> prodotti;
	private final double ScontoCoupon;
	private final String Ragione_Sociale;
	private final Indirizzo Address;
	private final String Data_Ordine;
	private final double Imposta;
	private String stato;

	public Ordine() {
		this.ID = 0;
		this.ScontoCoupon = 0.0d;
		this.prodotti = new HashMap<Prodotto, Integer>();
		this.Ragione_Sociale = null;
		this.Address = null;
		this.Data_Ordine = ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
		this.Imposta = 0.0d;
		this.stato = "NON EVASO";
	}
	
	public Ordine(Map<Prodotto, Integer> prodotti, String Ragione_Sociale, double ScontoCoupon, Indirizzo Address, double Imposta) {
	    this.ID = 0;
		this.prodotti = prodotti;
	    this.Ragione_Sociale = Ragione_Sociale;
	    this.ScontoCoupon = ScontoCoupon;
	    this.Address = Address;
	    this.Data_Ordine = ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
	    this.Imposta = Imposta;
	    this.stato = "NON EVASO";
	  }
	
	public Ordine(int ID, Map<Prodotto, Integer> prodotti, String Ragione_Sociale, double ScontoCoupon, Indirizzo Address, double Imposta, String stato) {
		this.ID = ID;
		this.prodotti = prodotti;
		this.Ragione_Sociale = Ragione_Sociale;
		this.ScontoCoupon = ScontoCoupon;
		this.Address = Address;
		this.Data_Ordine = ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
		this.Imposta = Imposta;
		this.stato = stato;
	}

	public Ordine(Map<Prodotto, Integer> prodotti, String Ragione_Sociale, double ScontoCoupon, Indirizzo Address,String Data_Ordine, double Imposta) {
		this.ID = 0;
		this.prodotti = prodotti;
		this.Ragione_Sociale = Ragione_Sociale;
		this.ScontoCoupon = ScontoCoupon;
		this.Address = Address;
		this.Data_Ordine = Data_Ordine;
		this.Imposta = Imposta;
		this.stato = "NON EVASO";
	}
	
	public Ordine(int ID, Map<Prodotto, Integer> prodotti, String Ragione_Sociale, double ScontoCoupon, Indirizzo Address, String Data_Ordine, double Imposta, String stato) {
		this.ID = ID;
		this.prodotti = prodotti;
		this.Ragione_Sociale = Ragione_Sociale;
		this.ScontoCoupon = ScontoCoupon;
		this.Address = Address;
		this.Data_Ordine = Data_Ordine;
		this.Imposta = Imposta;
		this.stato = stato;
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

	public int getID() {
		return ID;
	}

	public Indirizzo getAddress() {
		return Address;
	}

	public String getData_Ordine() {
		return Data_Ordine;
	}
	public double getSubTotalPrice() {
	    double totalPrice = 0.0;
	    for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
	      Prodotto prodotto = entry.getKey();
	      int quantity = entry.getValue();
	      totalPrice += prodotto.getPrezzo() * quantity;
	    }
	    return totalPrice;
	  }
	
	public double getTotalPrice() {
		return getSubTotalPrice() - getScontoCoupon();
	}

	public double getImposta() {
		return Imposta;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

}

