package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class UserDaoImp implements UserDAO {

	@Override
	public Utente getbyID(int id) throws SQLException {
		// TODO 
		return null;
	}

	@Override
	public Collection<Utente> doretrieveAll(String order) throws SQLException {
		// TODO 
		return null;
	}

	@Override
	public boolean doSave(Utente t) throws SQLException {
		// TODO
		return false;
	}

	@Override
	public int insert(Utente t) throws SQLException {
		// TODO 
		return 0;
	}

	@Override
	public int update(Utente t) throws SQLException {
		// TODO
		return 0;
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
	}	}


