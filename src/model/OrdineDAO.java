package model;
import java.util.List;

public interface OrdineDAO extends DAO<Ordine> {
	public List<Ordine> doRetrieveByUser(Utente t); 
}
