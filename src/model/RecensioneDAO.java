package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class RecensioneDAO {
	private static final String INSERT_REC = "INSERT INTO Recensione (Email_Utente, Votazione, Commento, Data_Recensione, ID_Prodotto) VALUES (?, ?, ?, ?, ?)";
	private static final String DELETE_REC = "DELETE FROM Recensione WHERE Recensione.ID_Recensione = ?";
	
	public RecensioneDAO() {
		
	}
	
	public static synchronized int doSave(Recensione rec) throws SQLException {
		int id = 0;
		ResultSet rs = null;
		try(Connection con = DMConnectionPool.getConnection(); PreparedStatement st = con.prepareStatement(INSERT_REC, Statement.RETURN_GENERATED_KEYS)) {
			con.setAutoCommit(false);
			st.setString(1, rec.getEmail());
			st.setInt(2, rec.getVotazione());
			st.setString(3, rec.getCommento());
			st.setString(4, rec.getData_Recensione());
			st.setString(5, rec.getID_Prodotto());
			if(st.executeUpdate() == 1) {
				rs = st.getGeneratedKeys();
				if(rs.next()) {
					id = rs.getInt(1);
				}
				con.commit();
			} else {
				con.rollback();
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return id;
	}
	
	public static synchronized boolean doDelete(int id) throws SQLException {
		int result = 0;
		try(Connection con = DMConnectionPool.getConnection(); PreparedStatement st = con.prepareStatement(DELETE_REC)) {
			st.setInt(1, id);
			result = st.executeUpdate();
		}
		return (result != 0);
	}
	
	public static synchronized Collection<Recensione> doRetrieveByProduct(Prodotto prod) throws SQLException {
		Collection<Recensione> recensioni = new ArrayList<>();
		try (Connection con = DMConnectionPool.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM Recensione WHERE ID_Prodotto = ?")) {
			st.setString(1, prod.getNome());
			try (ResultSet rs = st.executeQuery()) {
				
				while (rs.next()) {
					Recensione rec = new Recensione(	
					rs.getString("Email_Utente"),
					rs.getInt("Votazione"),
					rs.getString("Commento"),
					rs.getString("Data_Recensione"),
					prod.getNome()
					);
					
					recensioni.add(rec);
				}
			}
		}
		return recensioni;
	}
}