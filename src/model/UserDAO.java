package model;

import java.sql.SQLException;

public interface UserDAO extends DAO<Utente>{
	Utente doRetrieveByName(String username) throws SQLException;

}
