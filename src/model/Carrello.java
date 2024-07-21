package model;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Carrello implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final Map<Prodotto, Integer> prodotti;
	

	public Carrello() {
		prodotti = new HashMap<Prodotto, Integer>();	 
 }
	
	
	 public void addProduct(Prodotto prod) {
	       prodotti.merge(prod, 1, Integer::sum);
	    }
	 
		public void setProduct(Prodotto prod, Integer num) {
			prodotti.put(prod, num);
		}
	 

		public void removeProduct(Prodotto prod) {
			prodotti.computeIfPresent(prod, (key, value) -> value > 1 ? value - 1 : null);
		}
	
	 public void incrementProduct(Prodotto prod, Integer num) {
	           prodotti.merge(prod, num, Integer::sum);
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
	    BigDecimal total = new BigDecimal(totalPrice);
	    total = total.setScale(3, RoundingMode.HALF_UP);
	    return total.doubleValue();
	  }
	
	public int getTotalItems() {
	    int totalItems = 0;
	    for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
	      totalItems += entry.getValue();
	    }
	    return totalItems;
	  }
	
}


