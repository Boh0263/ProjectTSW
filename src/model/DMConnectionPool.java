package model;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.LinkedList;

public class DMConnectionPool {
	
	private static List<Connection> freeDBConnections;
	
	static {
		freeDBConnections = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			System.out.println("DB driver not found: " + e.getMessage());
		}
	}
	
	private static synchronized Connection createDBConnection() throws SQLException {
	Connection con = null;
	String ip = "127.0.0.1";
	String port = "3306";
	String db = "kiitz";
	String username = "root";
	String password = "rootarded";
	
		con = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
	con.setAutoCommit(false);
	return con;
	}
	
	public static synchronized Connection getConnection() throws SQLException{
		Connection con = null;
		if(!freeDBConnections.isEmpty()) {
			con = (Connection) freeDBConnections.get(0);
			freeDBConnections.remove(0);
			try {
				if (con.isClosed()) {
					con = getConnection();
				}
			} catch (SQLException e) {
				con.close();
				con = getConnection();
			}
		} else {
			con = createDBConnection();
			}
		return con;
	}
	
	public static synchronized void releaseConnection(Connection con) throws SQLException {
		if (con != null) freeDBConnections.add(con);
	}
}
