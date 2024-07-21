package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;

import java.io.StringReader;
import java.io.StringWriter;

public class OrdineDAOImp implements OrdineDAO {
	private static final Set<String> EXPECTED_ELEMENTS = new HashSet<>();
    static {
        // Struttura XML attesa per la fattura.
        EXPECTED_ELEMENTS.add("Ordine");
        EXPECTED_ELEMENTS.add("ID");
        EXPECTED_ELEMENTS.add("Data_Ordine");
        EXPECTED_ELEMENTS.add("Ragione_Sociale");
        EXPECTED_ELEMENTS.add("Indirizzo");
        EXPECTED_ELEMENTS.add("Sconto");
        EXPECTED_ELEMENTS.add("Stato");
        EXPECTED_ELEMENTS.add("Prezzo Totale");
        EXPECTED_ELEMENTS.add("Prodotti");
        EXPECTED_ELEMENTS.add("Prodotto");
        EXPECTED_ELEMENTS.add("Nome");
        EXPECTED_ELEMENTS.add("Quantita");
        EXPECTED_ELEMENTS.add("Imponibile");
        EXPECTED_ELEMENTS.add("IVA");
    }

	@Override
	public Ordine doRetrieveByKey(int id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Ordine ordine = null;
		
		String select = "SELECT * FROM Ordine WHERE ID = ?";
		try {
			con = DMConnectionPool.getConnection();
			ps = con.prepareStatement(select);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				ordine = new Ordine(
						doRetrieveContent(rs.getInt("ID")),
						rs.getString("Ragione_sociale"),
						rs.getDouble("Sconto"),
						new Indirizzo(rs.getString("Indirizzo_breve")),
						rs.getString("Data_Ordine"),
						rs.getDouble("Imposta")
						);
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} finally {
				DMConnectionPool.releaseConnection(con);
			}
		}
		return ordine;
	}

	@Override
	public Collection<Ordine> doretrieveAll(String stato) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<Ordine> ordini = null;
		String select = null;
		if (stato == null || stato.isEmpty()) {
			select = "SELECT * FROM Ordine";
		} else {
		   select = "SELECT * FROM Ordine WHERE Stato = ?";
		}
	   try {
		   con = DMConnectionPool.getConnection();
		   ps = con.prepareStatement(select);
		   if (stato != null && !stato.isEmpty()) {
               ps.setString(1, stato);
           }
		   rs = ps.executeQuery();
		   ordini = new LinkedList<Ordine>();
		   while(rs.next()) {
		            ordini.add( new Ordine(
		            rs.getInt("ID"),		
		            doRetrieveContent(rs.getInt("ID")),
		            rs.getString("Ragione_sociale"),
		            rs.getDouble("Sconto"),
		            new Indirizzo(rs.getString("Indirizzo_breve")),
		            rs.getString("Data_Ordine"),
		            rs.getDouble("Imposta"),
		            rs.getString("Stato")
		            ));	  
		            	  }
		   } finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} finally {
				DMConnectionPool.releaseConnection(con);
			}
		}
		return ordini;
	}
	   
	
	public Map<Prodotto, Integer> doRetrieveContent(int ID_Ordine) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProdottoDAO prodottoDAO = null;
		HashMap<Prodotto, Integer> contenuto = new HashMap<Prodotto, Integer>();
		
		String select = "SELECT * FROM Contenuto WHERE ID_Ordine = ?";
		try {
			prodottoDAO = new ProdottoDAOImp();
			con = DMConnectionPool.getConnection();
			ps = con.prepareStatement(select);
			ps.setInt(1, ID_Ordine);
			rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto temp = prodottoDAO.doRetrieveByKey(rs.getString("ID_Prodotto"));
				temp.setPrezzo(rs.getDouble("Prezzo_Lordo") / rs.getInt("Qta"));
			contenuto.put(temp, (Integer) rs.getInt("Qta"));	
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} finally {
				DMConnectionPool.releaseConnection(con);
			}
		}
		return contenuto;
	}
	
	public String doRetrieveFattura(Ordine t) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String fattura = null;
		String selectSQL = "SELECT Contenuto FROM Fattura WHERE ID_Ordine = ?";
		try {
			con = DMConnectionPool.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, t.getID());
			rs = ps.executeQuery();
			if (rs.next()) {
				fattura = rs.getString("Contenuto");
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} finally {
				DMConnectionPool.releaseConnection(con);
			}
		}
		return fattura;
	}
	


	@Override
	public boolean doSave(Ordine t) throws SQLException {
		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;

		//Verificare che l'ordine sia coerente al grado minimo.
		//Per ogni prodotto nella lista dell'ordine, fare una insert nella tabella contenuto
		//Aggiornare il valore della giacenza dei singoli prodotti
		//Aggiungere un nuovo indirizzo se l'indirizzo immesso dall'utente non esiste: 1. check se indirizzo non esiste (trova un id) 2. aggiungi se non c'era.
		
		String insertOrdine = "INSERT INTO Ordine (Ragione_sociale, Indirizzo_breve, Imposta, Sconto, Totale, Stato, Data_Ordine) VALUES (?,?,?,?,?,'NON EVASO',?)"; 
		String insertContent = "INSERT INTO Contenuto (Prezzo_Lordo, Qta, ID_Ordine, ID_Prodotto, Nome_Prodotto) VALUES (?,?,?,?,?)";
		String updateProduct = "UPDATE Prodotto SET Giacenza = Giacenza - ?";
		String uploadFattura = "INSERT INTO Fattura (ID_Ordine, Data_Fattura, Contenuto) VALUES (?,?,?)";
		
		try {
			con = DMConnectionPool.getConnection();
			con.setAutoCommit(false);
			ps1 = con.prepareStatement(insertOrdine, Statement.RETURN_GENERATED_KEYS);
			
			ps1.setString(1, t.getRagione_Sociale());
			ps1.setString(2, t.getAddress().toString());
			ps1.setDouble(3, t.getImposta());
			ps1.setDouble(4, t.getScontoCoupon());
			ps1.setDouble(5, t.getTotalPrice());
			ps1.setString(6, t.getData_Ordine());
			
			
			if(ps1.executeUpdate() > 0) {
				int ID;
				ResultSet gK = ps1.getGeneratedKeys();
				if(gK.next()) {
				 ID = gK.getInt(1);
				 
				 for (Map.Entry<Prodotto, Integer> entry : t.getProdotti().entrySet()) {
					 ps2 = con.prepareStatement(insertContent);
					 
					 Prodotto prodotto = entry.getKey();
					 ps2.setDouble(1,prodotto.getPrezzo());
					 ps2.setInt(2, entry.getValue());
					 ps2.setInt(3, ID);
					 ps2.setString(4, prodotto.getNome());
					 ps2.setString(5, prodotto.getNome());
					 
					 if (ps2.executeUpdate() <= 0) {
						con.rollback();
						return false;
					 };
					 
					 ps3 = con.prepareStatement(updateProduct);
					 ps3.setInt(1, entry.getValue());
					 if (ps3.executeUpdate() <= 0) {
							con.rollback();
							return false;
					    };
				}
			} else {
				con.rollback();
				return false;
			}
				
				//Fase 4: crea file xml, converti in stringa e inserisci in tabella Fattura
				String xml = null;
				
				try {
					
					xml = createXML(t);
					ps4 = con.prepareStatement(uploadFattura);
					ps4.setInt(1, ID);
					ps4.setString(2, t.getData_Ordine());
					ps4.setString(3, xml);
					
					if (ps4.executeUpdate() <= 0) {
						con.rollback();
						return false;
					}
		
					
				} catch (Exception e) {
					System.out.println("Errore nella creazione del file XML:"+ e.getMessage());
				    con.rollback();
				    return false;
				}
				
				
				
		}
			con.commit();
			
		} finally {
			try {
				if (ps1 != null) ps1.close();
				if (ps2 != null) ps2.close();
				if (ps3 != null) ps3.close();
				if (ps4 != null) ps4.close();
			} finally {
			DMConnectionPool.releaseConnection(con);
		}
	}
	return true;
	}

	@Override
	public int insert(Ordine t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Ordine t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean doDelete(Ordine t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

    @Override
	public Collection<Ordine> doRetrieveByUser(Utente t) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<Ordine> ordini = null;
		String select = "SELECT * FROM Ordine WHERE Ragione_Sociale IN (SELECT CONCAT(Nome,' ',Cognome) FROM Utente  WHERE Username = ? AND Nome = ? AND Cognome = ?)";
		try {
			con = DMConnectionPool.getConnection();
			ps = con.prepareStatement(select);
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getNome());
			ps.setString(3, t.getCognome());
			
			rs = ps.executeQuery();
			ordini = new LinkedList<Ordine>();
			while (rs.next()) {
				ordini.add(new Ordine(
						rs.getInt("ID"),
						doRetrieveContent(rs.getInt("ID")),
						rs.getString("Ragione_sociale"),
						rs.getDouble("Sconto"),
						new Indirizzo(rs.getString("Indirizzo_breve")),
						rs.getString("Data_Ordine"),
						rs.getDouble("Imposta"),
						rs.getString("Stato")
						));
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} finally {
				DMConnectionPool.releaseConnection(con);
			}
		}
		return ordini;
	}
    
    
	public String createXML(Ordine t) throws Exception { 
		
		 Element rootElement = new Element("Ordine");

	        // Dettagli dell'ordine
	        rootElement.addContent(new Element("ID").setText(String.valueOf(t.getID())));
	        rootElement.addContent(new Element("Data_Ordine").setText(t.getData_Ordine()));
	        rootElement.addContent(new Element("Ragione_Sociale").setText(t.getRagione_Sociale()));
	        rootElement.addContent(new Element("Indirizzo").setText(t.getAddress().toString()));
	        rootElement.addContent(new Element("Sconto").setText(String.valueOf(t.getScontoCoupon())));
	        rootElement.addContent(new Element("Stato").setText(t.getStato()));
	        rootElement.addContent(new Element("Prezzo_Totale").setText(String.valueOf(t.getTotalPrice())));

	        // Aggiungi i prodotti
	        Element itemsElement = new Element("Prodotti");
	        rootElement.addContent(itemsElement);

	        for(Map.Entry<Prodotto, Integer> entry : t.getProdotti().entrySet()) {
	            Element itemElement = new Element("Prodotto");

	            itemElement.addContent(new Element("Nome").setText(entry.getKey().getNome()));
	            itemElement.addContent(new Element("Quantita").setText(String.valueOf(entry.getValue())));
	            itemElement.addContent(new Element("Imponibile").setText(String.valueOf(entry.getKey().getPrezzo())));
	            itemElement.addContent(new Element("IVA").setText(String.valueOf(entry.getKey().getIVA())));

	            itemsElement.addContent(itemElement);
	        }

	        
	        Document doc = new Document(rootElement);
	        XMLOutputter xmlOutputter = new XMLOutputter();
	        xmlOutputter.setFormat(Format.getPrettyFormat());
	        StringWriter writer = new StringWriter();
	        xmlOutputter.output(doc, writer);

	        return writer.toString();
	    }
	
	

    public  boolean isWellFormed(String xml) {
        try {
            
            SAXBuilder saxBuilder = new SAXBuilder();
            Document doc = saxBuilder.build(new StringReader(xml));
            
       
            return hasExpectedStructure(doc.getRootElement());
        } catch (Exception e) {
            return false;
        }
    }

    private  boolean hasExpectedStructure(Element root) {
        // Controllo ad <Ordine>.
        if (!root.getName().equals("Ordine")) {
            return false;
        }

        //Controllo ricorsivo della struttura dell'XML per trovare tutti i tag.
        Set<String> tags = new HashSet<>();
        collectTags(root, tags);

        // Validazione su tutti i tag attesi.
        return tags.containsAll(EXPECTED_ELEMENTS);
    }

    private void collectTags(Element element, Set<String> tags) {
        tags.add(element.getName());
        for (Element child : element.getChildren()) {
            collectTags(child, tags);
        }
    }
	
    
}
