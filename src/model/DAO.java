package model;
import java.util.*;
import java.sql.SQLException;

public interface DAO<T> {
	T doRetrieveByKey(int id) throws SQLException;
	Collection<T> doretrieveAll(String order) throws SQLException;
	boolean doSave(T t) throws SQLException;
	int insert(T t) throws SQLException;
	int update (T t) throws SQLException;
	boolean doDelete(T t) throws SQLException;
}
