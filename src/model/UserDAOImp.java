package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class UserDAOImp implements UserDAO {

	@Override
    public Utente doRetrieveByKey(int id) throws SQLException {
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

            if (rs.next()) {
				bean = new Utente(
						rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("email"),
                        rs.getString("CF")
                        );
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
    
	public Utente doRetrieveByKey(String username) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;
		Utente bean = null;
		String getID = "SELECT * FROM Utente WHERE Username = ?";

		try {
			con = DMConnectionPool.getConnection();
			ps = con.prepareStatement(getID);
			ps.setString(1, username);

			rs = ps.executeQuery();

			if (rs.next()) {
				bean = new Utente(
						rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("email"),
                        rs.getString("CF")
                        );
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
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;
		ResultSet rsAddress;
		Collection<Utente> users = new LinkedList<Utente>();
		String selectSQL = "SELECT * FROM Utente";
		String selectAddressSQL = "SELECT * FROM Indirizzo WHERE Utente_ID = ?";
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
		con = DMConnectionPool.getConnection();
		ps = con.prepareStatement(selectSQL);
		rs = ps.executeQuery();
		while (rs.next()) {
			Indirizzo address;
			ps = con.prepareStatement(selectAddressSQL);
			ps.setString(1, rs.getString("username"));
			rsAddress = ps.executeQuery();
			if(rsAddress.next()) { //Prende solo il primo indirizzo.
                address = new Indirizzo(
                        rsAddress.getString("Via"),
                        rsAddress.getString("Citta"),
                        rsAddress.getString("Provincia"),
                        rsAddress.getString("CAP")
                        );
            } else {
                address = new Indirizzo("","","","");
            }
			
			Utente user = new Utente(
					rs.getString("username"),
					rs.getString("password"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("email"),
                    rs.getString("CF"),
                    rs.getString("tipo"),
                    rs.getString("data_nascita"),
                    rs.getString("telefono"),
                    address
					);
			users.add(user);	
		}
	} finally {
        try {
            if (ps != null)
                ps.close();
        } finally {
            DMConnectionPool.releaseConnection(con);
        }
      }
	 return users;
	}

	@Override
	public boolean doSave(Utente t) throws SQLException {
		//save user in the database
		Connection con = null;
		PreparedStatement ps = null;
		boolean result = false;	
        // follow the database schema to insert the user
		        String insertSQL = "INSERT INTO Utente (username, password, nome, cognome, email, CF, tipo, telefono, data_nascita) VALUES (?, SHA2(?,256), ?, ?, ?, ?, ?)";
		     
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
			ps.setString(8, t.getTelefono());
			ps.setString(9, t.getDataNascita());
			
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
	            String updateSQL = "UPDATE Utente SET password = COALESCE(SHA2(?, 256), password), email = COALESCE(?, email), CF = COALESCE(?, CF),  tipo = COALESCE(?, tipo),  telefono = COALESCE(?, telefono), data_nascita = COALESCE(?, data_nascita) WHERE username = ?;";
	            	   

	                    try {
							con = DMConnectionPool.getConnection();
							ps = con.prepareStatement(updateSQL);
							ps.setString(1, t.getPassword());
							ps.setString(2, t.getEmail());
							ps.setString(3, t.getCF());
							ps.setString(4, t.getTipo());
							ps.setString(5, t.getTelefono());
							ps.setString(6, t.getDataNascita());
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
	
	public boolean doEmail(String email) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		boolean exists = false;
        
		String checkSQL = "SELECT * FROM Utente WHERE Utente.email = ?";
		try {
			con = DMConnectionPool.getConnection();
			ps = con.prepareStatement(checkSQL);
			ps.setString(1, email);
			result = ps.executeQuery();
			if (result.next()) {
				exists = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				DMConnectionPool.releaseConnection(con);
			}
		}
		return exists;
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


