package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrdineDAOImp implements OrdineDAO {

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
		            doRetrieveContent(rs.getInt("ID")),
		            rs.getString("Ragione_sociale"),
		            rs.getDouble("Sconto"),
		            new Indirizzo(rs.getString("Indirizzo_breve")),
		            rs.getString("Data_Ordine"),
		            rs.getDouble("Imposta")));	  
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
			contenuto.put(prodottoDAO.doRetrieveByKey(rs.getString("ID_Prodotto")), (Integer) rs.getInt("Qta"));	
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


	@Override
	public boolean doSave(Ordine t) throws SQLException {
		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;

		//Verificare che l'ordine sia coerente al grado minimo.
		//Per ogni prodotto nella lista dell'ordine, fare una insert nella tabella contenuto
		//Aggiornare il valore della giacenza dei singoli prodotti
		//Aggiungere un nuovo indirizzo se l'indirizzo immesso dall'utente non esiste: 1. check se indirizzo non esiste (trova un id) 2. aggiungi se non c'era.
		
		String insertOrdine = "INSERT INTO Ordine (Ragione_sociale, Indirizzo_breve, Imposta, Sconto, Totale, Stato, Data_Ordine) VALUES (?,?,?,?,?,'NON EVASO',?)"; 
		String insertContent = "INSERT INTO Contenuto (Prezzo_Lordo, Qta, ID_Ordine, ID_Prodotto) VALUES (?,?,?,?)";
		String updateProduct = "UPDATE Prodotto SET Giacenza = Giacenza - ?";
		try {
			con = DMConnectionPool.getConnection();
			ps1 = con.prepareStatement(insertOrdine, Statement.RETURN_GENERATED_KEYS);
			
			ps1.setString(1, t.getRagione_Sociale());
			ps1.setString(2, t.getAddress().toString());
			ps1.setDouble(3, t.getImposta());
			ps1.setDouble(4, t.getScontoCoupon());
			ps1.setDouble(5, t.getTotalPrice());
			ps1.setString(6, t.getData_Ordine());
			
			
			if(ps1.executeUpdate() > 0) {
				ResultSet gK = ps1.getGeneratedKeys();
				if(gK.next()) {
				 int ID = gK.getInt(1);
				 
				 for (Map.Entry<Prodotto, Integer> entry : t.getProdotti().entrySet()) {
					 ps2 = con.prepareStatement(insertContent);
					 
					 Prodotto prodotto = entry.getKey();
					 ps2.setDouble(1,prodotto.getPrezzo());
					 ps2.setInt(2, entry.getValue());
					 ps2.setInt(3, ID);
					 ps2.setString(4, prodotto.getNome());
					 
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
		}
			con.commit();
			
		} finally {
			try {
				if (ps1 != null) ps1.close();
				if (ps2 != null) ps2.close();
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
		String select = "SELECT * FROM Ordine WHERE Ragione_Sociale IN (SELECT CONCAT(?, ' ',?) FROM Utente  WHERE Username = ?)";
		try {
			con = DMConnectionPool.getConnection();
			ps = con.prepareStatement(select);
			ps.setString(1, t.getNome());
			ps.setString(2, t.getCognome());
			ps.setString(3, t.getUsername());
			rs = ps.executeQuery();
			ordini = new LinkedList<Ordine>();
			while (rs.next()) {
				ordini.add(new Ordine(doRetrieveContent(rs.getInt("ID")),
						rs.getString("Ragione_sociale"),
						rs.getDouble("Sconto"),
						new Indirizzo(rs.getString("Indirizzo_breve")),
						rs.getString("Data_Ordine"),
						rs.getDouble("Imposta")));
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

}
