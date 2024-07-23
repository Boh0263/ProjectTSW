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
		ResultSet rsAddress;
		Utente user = null;
		String getID = "SELECT * FROM Utente WHERE ID = ?";
		String selectAddressSQL = "SELECT * FROM Indirizzo WHERE Utente_ID = ?";

		try {
			con = DMConnectionPool.getConnection();
			ps = con.prepareStatement(getID);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
					Indirizzo address;
					ps = con.prepareStatement(selectAddressSQL);
					ps.setInt(1, id);
					rsAddress = ps.executeQuery();
					if(rsAddress.next()) { //Prende solo il primo indirizzo.
		                address = new Indirizzo(
		                        rsAddress.getString("Via"),
		                        rsAddress.getString("Città"),
		                        rsAddress.getString("Provincia"),
		                        rsAddress.getString("CAP")
		                        );
		            } else {
		                address = new Indirizzo("","","","");
		            }
					
					 user = new Utente(
							rs.getString("username"),
							rs.getString("password"),
		                    rs.getString("nome"),
		                    rs.getString("cognome"),
		                    rs.getString("CF"),
		                    rs.getString("email"),
		                    rs.getString("tipo"),
		                    rs.getString("data_nascita"),
		                    rs.getString("telefono"),
		                    address
							);
					
				}	
			return user;
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				DMConnectionPool.releaseConnection(con);
			}
		}
	}
    
    
	public Utente doRetrieveByName(String username) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;
		ResultSet rsAddress;
		Utente user = null;
		String getID = "SELECT * FROM Utente WHERE Username = ?";
		String selectAddressSQL = "SELECT * FROM Indirizzo WHERE Utente_ID = ?";

		try {
			con = DMConnectionPool.getConnection();
			ps = con.prepareStatement(getID);
			ps.setString(1, username);

			rs = ps.executeQuery();

			if (rs.next()) {
					Indirizzo address;
					ps = con.prepareStatement(selectAddressSQL);
					ps.setInt(1, rs.getInt("ID"));
					rsAddress = ps.executeQuery();
					if(rsAddress.next()) { //Prende solo il primo indirizzo.
		                address = new Indirizzo(
		                        rsAddress.getString("Via"),
		                        rsAddress.getString("Città"),
		                        rsAddress.getString("Provincia"),
		                        rsAddress.getString("CAP")
		                        );
		            } else {
		                address = new Indirizzo("","","","");
		            }
					
					 user = new Utente(
							rs.getString("username"),
							rs.getString("password"),
		                    rs.getString("nome"),
		                    rs.getString("cognome"),
		                    rs.getString("CF"),
		                    rs.getString("email"),
		                    rs.getString("tipo"),
		                    rs.getString("data_nascita"),
		                    rs.getString("telefono"),
		                    address
							);
					
				}	
			return user;
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
			ps.setInt(1, rs.getInt("ID"));
			rsAddress = ps.executeQuery();
			if(rsAddress.next()) { //Prende solo il primo indirizzo.
                address = new Indirizzo(
                        rsAddress.getString("Via"),
                        rsAddress.getString("Città"),
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
                    rs.getString("CF"),
                    rs.getString("email"),
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
		        String insertSQL = "INSERT INTO Utente (username, password, nome, cognome, email, CF, tipo, telefono, data_nascita) VALUES (?, SHA2(?,256), ?, ?, ?, ?, ?, ?, ?)";
		        String insertAddressSQL = "INSERT INTO Indirizzo(Via, Città, Provincia, CAP, Utente_ID) VALUES (?, ?, ?, ?, ?)";
		try {
            con = DMConnectionPool.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            
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
			
			if (res == 1) {
				ResultSet keys = ps.getGeneratedKeys();	
				keys.next();
				int user_id = keys.getInt(1);
				
				ps = con.prepareStatement(insertAddressSQL);
				
				ps.setString(1, t.getIndirizzo().getVia());
				ps.setString(2, t.getIndirizzo().getCitta());
				ps.setString(3, t.getIndirizzo().getProvincia());
				ps.setString(4, t.getIndirizzo().getCAP());
				ps.setInt(5, user_id);
				
				res = ps.executeUpdate();
				
				if (res == 1) {
				con.commit();
				result = true;
				} else { 
					con.rollback();
				}
			} else {
			     con.rollback();
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
	public int update(Utente utente) throws SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		String UPDATE = "UPDATE Utente SET Email = COALESCE(?, Email), "
	               + "Password = COALESCE(SHA2(?, 256), Password), "
	               + "Nome = COALESCE(?, Nome), "
	               + "Cognome = COALESCE(?, Cognome), "
	               + "CF = COALESCE(?, CF), "
	               + "Telefono = COALESCE(?, Telefono), "
	               + "Data_Nascita = COALESCE(?, Data_Nascita), "
	               + "WHERE username = ?";
		
		String updateAddress = "UPDATE Indirizzo SET Via = COALESCE(?, Via), " + "CAP = COALESCE(?, CAP), "
				+ "Città = COALESCE(?, Città), " + "Provincia = COALESCE(?, Provincia) "
				+ "WHERE Utente_ID IN (SELECT ID FROM Utente WHERE username = ?)";
		try {
			con = DMConnectionPool.getConnection();
			st = con.prepareStatement(UPDATE); 
		
			con.setAutoCommit(false);
			st.setString(1, utente.getEmail());
			st.setString(2, utente.getPassword());
			st.setString(3, utente.getNome());
			st.setString(4, utente.getCognome());
			st.setString(5, utente.getCF());
			st.setString(6, utente.getTelefono());
			st.setString(7, utente.getDataNascita());
			st.setString(8, utente.getUsername());
			st.setString(9, utente.getTipo());
			
			result = st.executeUpdate();
			
			if (result >= 0) {	
				st1 = con.prepareStatement(updateAddress);
				if(utente.getIndirizzo() != null) {
				st1.setString(1, utente.getIndirizzo().getVia());
				st1.setString(2, utente.getIndirizzo().getCAP());
				st1.setString(3, utente.getIndirizzo().getCitta());
				st1.setString(4, utente.getIndirizzo().getProvincia());
				st1.setString(5, utente.getUsername());
				result = st1.executeUpdate();
				}
				
			if (result >= 0) {	
			con.commit();
			} else {
                con.rollback();
		}
			} 
			else {
			con.rollback();
			} } 
		finally {
                if (st != null)
                    st.close();
                if (st1 != null)
                    st1.close();
                DMConnectionPool.releaseConnection(con); 
            } 
			
		return result;        
	}
	
	public synchronized boolean doPriviledge(Utente u) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		boolean result = false;
		
		String updateSQL = "UPDATE Utente SET tipo = ? WHERE username = ?";
		
		try {
			con = DMConnectionPool.getConnection();
			ps = con.prepareStatement(updateSQL);
			ps.setString(1, u.getTipo());
			ps.setString(2, u.getUsername());
			ps.executeUpdate();
			result = true;
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
		return result;
	}

	
	
	@Override
	public boolean doDelete(Utente t) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String deleteSQL = "DELETE FROM Utente WHERE username = ?";
			try {
				con = DMConnectionPool.getConnection();
				ps = con.prepareStatement(deleteSQL);
				ps.setString(1, t.getUsername());
				result =  ps.executeUpdate();
				con.commit();
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


