package model;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;


public class Carrello implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final Map<Prodotto, Integer> prodotti;
	

	public Carrello() {
		prodotti = new HashMap<Prodotto, Integer>();	 
 }
	
	
	 public void addProduct(Prodotto prod) {
	        prodotti.putIfAbsent(prod, 1); 
	        if (prodotti.containsKey(prod)) {
	            prodotti.put(prod, prodotti.get(prod) + 1);
	        }
	    }
	
	 public void incrementProduct(Prodotto prod, Integer num) {
	        if (!prodotti.containsKey(prod)) {
	           addProduct(prod);
	           incrementProduct(prod,num-1);
	        }
	        prodotti.put(prod, prodotti.get(prod) + num);
	    }
	 
	 public void mergeCarts(Carrello altroCarrello) {
	        if (altroCarrello == null) {
	            return; 
	        }
	        altroCarrello.getProdotti().entrySet().stream().forEach(entry -> {
	            Prodotto product = entry.getKey();
	            Integer otherQuantity = entry.getValue();

	            if (!prodotti.containsKey(product)) {
	            	this.prodotti.put(product, otherQuantity);
	            }
	        });
	    }
	
	 public void clearCarr() {
		    prodotti.clear();
		  }
		
	public Map<Prodotto, Integer> getProdotti() {
		return prodotti;
	}
	
	public double getTotalPrice() {
	    double totalPrice = 0.0;
	    for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
	      Prodotto prodotto = entry.getKey();
	      int quantity = entry.getValue();
	      totalPrice += prodotto.getPrezzo() * quantity;
	    }
	    return totalPrice;
	  }
	public int getTotalItems() {
	    int totalItems = 0;
	    for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
	      totalItems += entry.getValue();
	    }
	    return totalItems;
	  }  
}
