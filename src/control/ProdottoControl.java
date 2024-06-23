package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import model.Prodotto;
import model.ProdottoDAOImp;


public class ProdottoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ProdottoDAOImp dao = new ProdottoDAOImp();
       
    public ProdottoControl() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = null;
        String pathInfo = request.getPathInfo();
        
        if (pathInfo != null) {
            productName = pathInfo.substring(1); 
        }

        // Se il nome del prodotto non è presente nell'URL lo si cerca nei parametri della richiesta.
        
        if (productName == null) {
            productName = request.getParameter("Nome");
            if (productName != null) {
                // Se il nome del prodotto è presente nei parametri della richiesta si reindirizza alla pagina del prodotto.
               response.sendRedirect("./Prodotto/" + productName);
                return;
            }
        } else {
            try {
            	Object obj = (Prodotto) dao.doRetrieveByKey(productName);
            	System.out.println(obj.toString());
                request.setAttribute("prodotto", obj);
            } catch (Exception e) {
                if (e instanceof SQLException) {
                    throw new ServletException("Prodotto non trovato. :"+ e.getMessage());
                } else {
                    throw new ServletException("Errore: " + e.getMessage());
                }
            }
        }
        
        String[] styles = {"./resources/styles/single_responsive.css", "./resources/styles/single_styles.css"};
        request.setAttribute("custom_styles", styles);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/singlea.jsp");
        dispatcher.forward(request, response);
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keysParam = request.getParameter("keys");
        if (keysParam == null || keysParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing product keys.");
            return;
        }

        try {
            Gson gson = new Gson();
            TypeToken<HashMap<String, Integer>> type = new TypeToken<HashMap<String, Integer>>(){};
            Map<String, Integer> cartData = gson.fromJson(keysParam, type.getType()); 
            Map<Prodotto,Integer> prodotti = new HashMap<>();
            Prodotto prodotto = null;
            for (String key : cartData.keySet()) {
            	try {
                
            		prodotto = dao.doRetrieveByKey(key);
            	
                if (prodotto != null) {
                    prodotti.put(prodotto, cartData.get(key));
                	}
                } catch (SQLException e) {
            		continue;
            	}
            }
            
            
            gson = new GsonBuilder().create();
            JsonObject jsonMap = new JsonObject();
            
            for(Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
            	
            	JsonElement prodottoJson = gson.toJsonTree(entry.getKey());
            	JsonObject prodottoObject = prodottoJson.getAsJsonObject();
            	
            	prodottoObject.addProperty("quantita", entry.getValue());
            	prodottoObject.addProperty("categoria", entry.getKey().getClass().getSimpleName());
            	
            	jsonMap.add(entry.getKey().getNome(), prodottoObject);
            }
            
            String jsonResponse = gson.toJson(jsonMap);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
        }
    }
}
