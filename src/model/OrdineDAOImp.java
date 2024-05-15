package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

public class OrdineDAOImp implements OrdineDAO {

	@Override
	public Ordine getbyID(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Ordine> doretrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doSave(Ordine t) throws SQLException {
		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;

		//Verificare che l'ordine sia coerente al grado minimo.
		//Per ogni prodotto nella lista dell'ordine, fare una insert nella tabella contenuto
		//Aggiornare il valore della giacenza dei singoli prodotti
		//Aggiungere un nuovo indirizzo se l'indirizzo immesso dall'utente non esiste: 1. check se indirizzo non esiste (trova un id) 2. aggiungi se non c'era.
		
		String insertOrdine = "INSERT INTO Ordine (Ragione_sociale, Indirizzo_breve, Imposta, Sconto, Totale, Stato) VALUES (?,?,?,?,?,'NON EVASO')"; 
		String insertContent = "INSERT INTO Contenuto (Prezzo_Lordo, Qta, ID_Ordine, ID_Prodotto) VALUES (?,?,?,?)";
		try {
			con = DMConnectionPool.getConnection();
			ps1 = con.prepareStatement(insertOrdine, Statement.RETURN_GENERATED_KEYS);
			
			if(ps1.executeUpdate() > 0) {
				ResultSet gK = ps1.getGeneratedKeys();
				if(gK.next()) {
				 int ID = gK.getInt(1);
				 ps2 = con.prepareStatement(insertContent);
				 ps2.setDouble(1,t.getTotalPrice());
				 //TBC
				 
				 
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
	public List<Ordine> doRetrieveByUser(Utente t) {
		// TODO Auto-generated method stub
		return null;
	}

}
