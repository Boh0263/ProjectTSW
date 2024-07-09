package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDAO {
	private static final String INSERT_IMAGE_SQL = "INSERT INTO Immagine (Placeholder, Content) VALUES (?, ?)";
    private static final String SELECT_IMAGE_BY_ID = "SELECT Placeholder, Content, MimeType  FROM Immagine WHERE ID = ?";
    private static final String GET_IMAGES = "SELECT ID FROM Immagine WHERE ID = ?";
    
	public ImageDAO() {
	}
	
	public static synchronized int doSave(Immagine image) throws SQLException {
	   
		PreparedStatement st = null;
		Connection con = null;
		ResultSet rs = null;
		int id = 0;
		
		try {
			con = DMConnectionPool.getConnection();
			st = con.prepareStatement(INSERT_IMAGE_SQL);
			st.setString(1, image.getPlaceholder());
			st.setBytes(2, image.getContent());
			
			if (st.executeUpdate() == 1) {
			   rs = st.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt("ID");
				}			    
			}
		} finally {
			try {
				if (st != null)
					st.close();
			} finally {
				DMConnectionPool.releaseConnection(con);
			}
		}
		return id;
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
			immagine = new Immagine(rs.getString("Placeholder"), rs.getBytes("Content"), rs.getString("MimeType"));
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
	
	public static synchronized int doUpdate(Immagine image, int id) throws SQLException{
		boolean flag = true;
		try(Connection con = DMConnectionPool.getConnection(); PreparedStatement st = con.prepareStatement(GET_IMAGES)) {
			st.setInt(1, id);
			try(ResultSet rs = st.executeQuery()){
				while(rs.next() && flag) {
					if(rs.getInt("ID") == id) {
						flag = false;
					}
				}
			}
		}
		if(flag = false) {	//trovato stesso id
			return id;
		}
		else {
			return doSave(image);
		}
	}

}
