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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import model.Prodotto;
import model.ProdottoDAO;
import model.ProdottoDAOImp;


@WebServlet("/Preferiti")
public class PreferitiControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProdottoDAO dao;
       
   
    public PreferitiControl() {
        super();
        dao = new ProdottoDAOImp();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/Preferiti.jsp");
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
            // Initialize Gson
            Gson gson = new Gson();
            TypeToken<Map<String, Integer>> type = new TypeToken<Map<String, Integer>>() {};
            Map<String, Integer> prefData = gson.fromJson(keysParam, type.getType());

            // Retrieve products
            Map<String, Prodotto> prodotti = new HashMap<>();
            Prodotto prodotto = null;

            for (String key : prefData.keySet()) {
                try {
                    prodotto = dao.doRetrieveByKey(key);
                    if (prodotto != null) {
                        prodotti.put(key, prodotto); 
                    }
                } catch (SQLException e) {
                    continue;
                }
            }

         
            gson = new GsonBuilder().create();
            JsonObject jsonMap = new JsonObject();

            for (Map.Entry<String, Prodotto> entry : prodotti.entrySet()) {
                JsonElement prodottoJson = gson.toJsonTree(entry.getValue());
                JsonObject prodottoObject = prodottoJson.getAsJsonObject();

                prodottoObject.addProperty("nome", entry.getValue().getNome());
                prodottoObject.addProperty("categoria", entry.getValue().getClass().getSimpleName());
                prodottoObject.addProperty("prezzo", entry.getValue().getPrezzo());
                prodottoObject.addProperty("descrizione", entry.getValue().getDescrizione());
                prodottoObject.addProperty("img1", entry.getValue().getImg1());

                jsonMap.add(entry.getKey(), prodottoObject);
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
