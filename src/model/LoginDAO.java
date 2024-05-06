package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class LoginDAO {

	public static synchronized String UserValidation(LoginInfo userDetails) throws SQLException
	{	
		Connection con = null;
		PreparedStatement st = null;
		String validStatus = "";
		
		try
		{
			con = DMConnectionPool.getConnection();
			st = con.prepareStatement("SELECT username, password from Utente WHERE (username = ? AND password = ?)");
			st.setString(1,userDetails.getUsername());
			st.setString(2, userDetails.getPassword());
			ResultSet rs= st.executeQuery();
			while(rs.next())
			{
				validStatus = rs.getString("Tipo");
			}	
		}finally {
			try {
				if (st != null) st.close();
			} finally {
				DMConnectionPool.releaseConnection(con);
			}
		}
		return validStatus;
	}
 }

