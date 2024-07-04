package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.sql.PreparedStatement;

public  class LoginDAO {
	static final String emailPattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
	
	
	
	private static boolean isEmail(String username) {
        	Pattern pattern = Pattern.compile(emailPattern);
        	return pattern.matcher(username).matches();
    }
	
	public static synchronized String UserValidation(LoginInfo userDetails) throws SQLException
	{	
		Connection con = null;
		PreparedStatement st = null;
		String validStatus = "";
		String queryUsername = "SELECT username, password, tipo from Utente WHERE (username = ? AND password = ?)";
		String queryEmail = "SELECT email, password, tipo from Utente WHERE (email = ? AND password = ?)";
		try {
			con = DMConnectionPool.getConnection();
			if (isEmail(userDetails.getUsername())) {
                st = con.prepareStatement(queryEmail);
                st.setString(1, userDetails.getUsername());
                st.setString(2, userDetails.getPassword());
            } else {
			st = con.prepareStatement(queryUsername);
			st.setString(1, userDetails.getUsername());
			st.setString(2, userDetails.getPassword());
			      }
			ResultSet rs= st.executeQuery();
			if(rs.next()) {
				validStatus = rs.getString("tipo");
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

