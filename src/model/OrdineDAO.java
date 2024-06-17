package model;
import java.sql.SQLException;
import java.util.Collection;

public interface OrdineDAO extends DAO<Ordine> {
	public Collection<Ordine> doRetrieveByUser(Utente t) throws SQLException;; 
}
