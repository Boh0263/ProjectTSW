package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class UserDaoImp implements UserDAO {

	@Override
    public Utente getbyID(int id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs;
        Utente bean = new Utente();
        String getID = "SELECT * FROM UTENTE WHERE id = ?";

        try {
            con = DMConnectionPool.getConnection();
            ps = con.prepareStatement(getID);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if(rs.next()) {
                bean.setCF("CF");
                bean.setCognome("cognome");
                bean.setEmail("email");
                bean.setNome("nome");
                bean.setPassword("password");
                bean.setUsername("username");
            }
            return bean;
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } finally {
                DMConnectionPool.releaseConnection(con);
            }
        }
    }
	

	@Override
	public Collection<Utente> doretrieveAll(String order) throws SQLException {
		//TODO
		
		return null;
	}

	@Override
	public boolean doSave(Utente t) throws SQLException {
		//save user in the database
		Connection con = null;
		PreparedStatement ps = null;
		boolean result = false;	
        // follow the database schema to insert the user
		        String insertSQL = "INSERT INTO Utente (username, password, nome, cognome, email, CF, tipo) VALUES (?, SHA2(?,256), ?, ?, ?, ?, ?)";
		     
		try {
            con = DMConnectionPool.getConnection();
            ps = con.prepareStatement(insertSQL);
            ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getNome());
			ps.setString(4, t.getCognome());
			ps.setString(5, t.getEmail());
			ps.setString(6, t.getCF());
			ps.setString(7, "R");
			
		    int res = ps.executeUpdate();
		    con.commit();
			if (res == 1) {
				result = true;
			} 
			
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				DMConnectionPool.releaseConnection(con);
			}
		}
		return result;
	}

	@Override
	public int insert(Utente t) throws SQLException {
		// TONOTDO
		return 0;
	}

	@Override
	public int update(Utente t) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
	    int result = 0;
	            String updateSQL = "UPDATE Utente SET password = SHA(?,256), email = ?, CF = ?, tipo = ? WHERE username = ?";
	                    try {
							con = DMConnectionPool.getConnection();
							ps = con.prepareStatement(updateSQL);
							ps.setString(1, t.getPassword());
							ps.setString(2, t.getEmail());
							ps.setString(3, t.getCF());
							ps.setString(4, t.getTipo());
							ps.setString(5, t.getUsername());
							result = ps.executeUpdate();
							con.commit();
						} finally {
							try {
								if (ps != null)
									ps.close();
							} finally {
								DMConnectionPool.releaseConnection(con);
							}
							
	                    }
		return result;
	}

	@Override
	public boolean doDelete(Utente t) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String deleteSQL = "DELETE FROM Utente WHERE Utente.username = ?";
			try {
				con = DMConnectionPool.getConnection();
				ps = con.prepareStatement(deleteSQL);
				ps.setString(1, t.getUsername());
				result =  ps.executeUpdate();
				} finally {
					try {
						if (ps != null) ps.close();
					} finally {
						DMConnectionPool.releaseConnection(con);
					}
				}
		return (result != 0);
	}
	
	public boolean doEmail(String email) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean result = false;
        return true; //TODO
	}

	public  boolean doCheck(String username) throws SQLException {
	Connection con = null;
	PreparedStatement ps = null;
	boolean result = false;
	
	String checkSQL = "SELECT * FROM Utente WHERE Utente.username = ?";
	try {
		con = DMConnectionPool.getConnection();
		ps = con.prepareStatement(checkSQL);
		ps.setString(1, username);
		result = ps.executeQuery().next();
	} finally {
		try {
			if (ps != null)
				ps.close();
		} finally {
			DMConnectionPool.releaseConnection(con);
		}
	}
	return result;
	
	}
	
}


