package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDAO {
	private static final String INSERT_IMAGE_SQL = "INSERT INTO Immagine (Placeholder, Content) VALUES (?, ?)";
    private static final String SELECT_IMAGE_BY_ID = "SELECT Placeholder, Content FROM Immagine WHERE ID = ?";
    
	public ImageDAO() {
	}
	
	public static synchronized boolean doSave(Immagine image) throws SQLException {
	   
		PreparedStatement st = null;
		Connection con = null;
		boolean status = false;
		try {
			con = DMConnectionPool.getConnection();
			st = con.prepareStatement(INSERT_IMAGE_SQL);
			st.setString(1, image.getPlaceholder());
			st.setBytes(2, image.getContent());
			
			if (st.executeUpdate() == 1) {
			    status = true;
			}
		} finally {
			try {
				if (st != null)
					st.close();
			} finally {
				DMConnectionPool.releaseConnection(con);
			}
		}
		return status;
	}
	
	
	public static synchronized Immagine doRetrieveByKey(int id) throws SQLException {
		Immagine immagine = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DMConnectionPool.getConnection();
			st = con.prepareStatement(SELECT_IMAGE_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
			immagine = new Immagine(rs.getString("Placeholder"), rs.getBytes("Content"));
			}
			
		} finally {
			try {
				if (st != null)
					st.close();
			} finally {
				DMConnectionPool.releaseConnection(con);
			}
		}
		return immagine;
	}

}
