package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.*;




@WebServlet("/checkout")
public class CheckoutControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdottoDAO pdao;
	private UserDAO udao;
	private OrdineDAO odao;
      
    public CheckoutControl() {
        super();
     
        pdao = new ProdottoDAOImp();
        udao = new UserDAOImp();
        odao = new OrdineDAOImp();
   
    }

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//in questo caso, aspetto un json in formato string con i dettagli dell'ordine presi da paypal.
		
		String jsonString = request.getParameter("dettagli");
	
		
		try {
		JsonElement jsonElement = JsonParser.parseString(jsonString);	
			
		if (jsonElement.isJsonObject()) {
			JsonObject json = jsonElement.getAsJsonObject();
			
			JsonArray purchaseUnits = json.getAsJsonArray("purchase_units");
			 JsonObject purchaseUnit = purchaseUnits.get(0).getAsJsonObject();
		     JsonArray itemsNode = purchaseUnit.getAsJsonArray("items");
		     
		     Map<Prodotto, Integer> prodotti = new HashMap<>();
		     
				for (JsonElement itemElement : itemsNode) {
					JsonObject item = itemElement.getAsJsonObject();

					String name = item.get("name").getAsString();
					int quantity = item.get("quantity").getAsInt();
					Prodotto prodotto = null;	
					try {
						
						
						prodotto = pdao.doRetrieveByKey(name);
						prodotto.setPrezzo(item.get("unit_amount").getAsJsonObject().get("value").getAsDouble());
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					if(prodotto != null) {
					prodotti.put(prodotto, quantity);
					}
				}
				//get user by session:
				
			String username = (String) request.getSession().getAttribute("username");
			Utente user = null;
			if(username != null) {
				try {
					
				    user = udao.doRetrieveByName(username);
					
				} catch (SQLException e) {
					e.printStackTrace();
					response.sendRedirect(request.getContextPath( ) + "/");
				}
			}
			
				
				Ordine ordine = new Ordine(
						prodotti,
						user.getNome() + " " + user.getCognome(),
						purchaseUnit.has("discount") ? purchaseUnit.get("discount").getAsJsonObject().get("value").getAsDouble() : 0.0,
					    user.getIndirizzo(),
					    purchaseUnit.has("tax_total") ? purchaseUnit.get("tax_total").getAsJsonObject().get("value").getAsDouble() : 0.0
						);
				
			    try {
							
		                  boolean test = odao.doSave(ordine);
		                  System.out.println(test);
		                    
				     } catch (SQLException e) {
							e.printStackTrace();
						}

			request.getSession().removeAttribute("carrello");
			
			request.setAttribute("ordine", ordine);
			
			request.setAttribute("custom_styles", new String[] { "./resources/styles/main_styles.css", "./resources/styles/responsive.css", "./resources/styles/checkout_style.css" });
			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Riepilogo-Checkout.jsp");
			rd.forward(request, response);
			
		}} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() +"/");
		}
		
		
		
	}

}
