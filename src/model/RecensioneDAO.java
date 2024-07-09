package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecensioneDAO {
	private static final String INSERT_REC = "INSERT INTO Recensione (Email_Utente, Votazione, Commento, Data_Recensione) VALUES (?, ?, ?, ?)";
	private static final String DELETE_REC = "DELETE FROM Recensione WHERE Recensione.ID_Recensione = ?";
	
	public RecensioneDAO() {
		
	}
	
	public static synchronized int doSave(Recensione rec) throws SQLException {
		int id = 0;
		ResultSet rs = null;
		try(Connection con = DMConnectionPool.getConnection(); PreparedStatement st = con.prepareStatement(INSERT_REC)) {
			st.setString(1, rec.getEmail());
			st.setInt(2, rec.getVotazione());
			st.setString(3, rec.getCommento());
			st.setString(4, rec.getData_Recensione());
			if(st.executeUpdate() == 1) {
				rs = st.getGeneratedKeys();
				if(rs.next()) {
					id = rs.getInt("ID_Recensione");
				}
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
}