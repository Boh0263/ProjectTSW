package model;

import java.sql.SQLException;
import java.util.Collection;

public interface ProdottoDAO extends DAO<Prodotto> {
  
      boolean doDeletebyID(String ID) throws SQLException;
      Collection<Prodotto> doRetrieveByCategory(String tipo) throws SQLException;
      Prodotto doRetrieveByKey(String ID) throws SQLException;
      
}
