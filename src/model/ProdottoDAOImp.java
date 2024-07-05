package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.Abbigliamento;
import model.Arma;
import model.Armatura;
import model.Accessorio;

public class ProdottoDAOImp implements ProdottoDAO {

	@Override
	public Prodotto doRetrieveByKey(int id) throws SQLException {
		// TODO 
		return null;
	}

	@Override
	public int insert(Prodotto t) throws SQLException {
		// TODO 
		return 0;
	}

	@Override
	public int update(Prodotto t) throws SQLException {
		
		String[] queries = { "UPDATE Prodotto SET Prezzo = ?, IVA = ?, Giacenza = ?, Descrizione = ?, img1 = ?, img2 = ?, img3 = ? WHERE Nome = ?;",
				"UPDATE Arma SET Materiale = ?, Tipo = ?, Utilizzo = ? WHERE ID_Prodotto = ?",
				"UPDATE Abbigliamento SET Tipo = ?, Materiale = ? WHERE ID_Prodotto = ?"
		};
		
		Connection con = null;
		PreparedStatement ps = null;
		int modifiche = 0;
		
		try {
			con = DMConnectionPool.getConnection();
			ps = con.prepareStatement(queries[0]);
			ps.setDouble(1, t.getPrezzo());
			ps.setDouble(2, t.getIVA());
			ps.setInt(3, t.getGiacenza());
			ps.setString(4, t.getDescrizione());
			ps.setInt(5, t.getImg1());
			ps.setInt(6, t.getImg2());
			ps.setInt(7, t.getImg3());
			ps.setString(8, t.getNome());
			
			modifiche += ps.executeUpdate();
			if (t instanceof Arma) {
				ps = con.prepareStatement(queries[1]);
				ps.setString(1, ((Arma) t).getMateriale());
				ps.setString(2, ((Arma) t).getTipo());
				ps.setString(3, ((Arma) t).getUtilizzo());
				ps.setString(3, t.getNome());
				
				modifiche += ps.executeUpdate();
			} else if (t instanceof Abbigliamento) {
				ps = con.prepareStatement(queries[2]);
				ps.setString(1, ((Abbigliamento) t).getTipo());
				ps.setString(2, ((Abbigliamento) t).getGenere());
				ps.setString(2, t.getNome());
				
				modifiche += ps.executeUpdate();
			}					
		} finally {
			if (ps != null) con.close();
		}
		
		return modifiche;
	
	}

	@SuppressWarnings("resource")
	@Override
	public synchronized Collection<Prodotto> doretrieveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		Collection<Prodotto> prodotti = new LinkedList<Prodotto>();
		
		String[] queries = { "SELECT * FROM Prodotto p JOIN Abbigliamento a ON p.Nome = a.ID_Prodotto",
		                     "SELECT * FROM Prodotto p JOIN Accessorio a ON p.Nome = a.ID_Prodotto", //togliere il nome delle tabelle dalle query.
		                     "SELECT * FROM Prodotto p JOIN Arma a ON p.Nome = a.ID_Prodotto",
		                     "SELECT * FROM Prodotto p JOIN Armatura a ON p.Nome = a.ID_Prodotto"
		                   };
	for (int i = 0; i <  4; i++) {	
		if (order != null && !order.equals("")) {
			queries[i] += " GROUP BY p.Nome ORDER BY " + order;
			
		}
	}
		try {
			con = DMConnectionPool.getConnection();
			
			ps = con.prepareStatement(queries[0]);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) { 
				Prodotto prod = new Abbigliamento(
						rs.getString("Nome"),
						rs.getDouble("Prezzo"),
						rs.getString("Descrizione"),
						rs.getInt("Giacenza"),
						rs.getInt("img1"),
						rs.getInt("img2"),
						rs.getInt("img3"),
						rs.getInt("ID_Abbigliamento"),
						rs.getString("Tipo"),
						rs.getString("Materiale")
						);
				prodotti.add(prod);
			}
			
            ps = con.prepareStatement(queries[1]);
		    rs = ps.executeQuery();
		    
		    while (rs.next()) { 
				Prodotto prod = new Accessorio(
						rs.getString("Nome"),
						rs.getDouble("Prezzo"),
						rs.getString("Descrizione"),
						rs.getInt("Giacenza"),
						rs.getInt("img1"),
						rs.getInt("img2"),
						rs.getInt("img3"),
						rs.getInt("ID_Accessorio")
						);
				prodotti.add(prod);
		    }
		   ps = con.prepareStatement(queries[2]);
		   rs = ps.executeQuery();
		   
		   while (rs.next()) { 
				Prodotto prod = new Arma(
						rs.getString("Nome"),
						rs.getDouble("Prezzo"),
						rs.getString("Descrizione"),
						rs.getInt("Giacenza"),
						rs.getInt("img1"),
						rs.getInt("img2"),
						rs.getInt("img3"),
						rs.getInt("ID_Arma"),
						rs.getString("Materiale"),
						rs.getString("Tipo"),
						rs.getString("Utilizzo")
						);
				prodotti.add(prod);
			   }
		   ps = con.prepareStatement(queries[3]);
		   rs = ps.executeQuery();
		   
		   while (rs.next()) { 
				Prodotto prod = new Armatura(
						rs.getString("Nome"),
						rs.getDouble("Prezzo"),
						rs.getString("Descrizione"),
						rs.getInt("Giacenza"),
						rs.getInt("img1"),
						rs.getInt("img2"),
						rs.getInt("img3"),
						rs.getInt("ID_Armatura"),
						rs.getString("Materiale"),
						rs.getString("Prezzo") 
						);
				prodotti.add(prod);
						        
		   }
				
		} finally {
			try {
				if (ps != null) ps.close();
			} finally {
				DMConnectionPool.releaseConnection(con);
			}
		}
 		
		return prodotti;
	}

	@Override
	public synchronized boolean doSave(Prodotto t) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psg = null;
		String tempID = "";
		
		String insertSQLM = "INSERT INTO " + "Prodotto " + "(Nome, Prezzo, IVA, Giacenza, Descrizione, img1, img2, img3) VALUES(?,?,?,?,?,?,?)";
		String insertSQL1 = "INSERT INTO Accessorio (ID_Prodotto) VALUES (?)";
		String insertSQL2 = "INSERT INTO Arma (ID_Prodotto, Materiale, Tipo, Utilizzo) VALUES (?,?,?,?)"; // i nomi delle tabelle non dovranno apparire nella stringa, rischio data leak!.
		String insertSQL3 =" INSERT INTO Armatura (ID_Prodotto, Materiale, Pezzo) VALUES (?,?,?)";
		String insertSQL4 = "INSERT INTO Abbigliamento (ID_Prodotto, Materiale, Tipo) VALUES (?,?,?)";
		
		String generatedColumns[] = {"Nome"};
		
		try {
			con = DMConnectionPool.getConnection();
			ps = con.prepareStatement(insertSQLM, generatedColumns);
			
			ps.setString(1, t.getNome());
			ps.setDouble(2, t.getPrezzo());
			ps.setInt(3, t.getGiacenza());
			ps.setDouble(4, t.getIVA());
			ps.setString(5, t.getDescrizione());
			ps.setInt(6, t.getImg1());
			
			if (t.getImg2() != 1) {
				ps.setInt(7, t.getImg2());
				} else {
					ps.setNull(8, java.sql.Types.NULL);
			}
			
			if (t.getImg3() != 1) {
				ps.setInt(8, t.getImg3());
				} else {
					ps.setNull(8, java.sql.Types.NULL);
			}
			
			ps.executeUpdate();
			
			ResultSet keys = ps.getGeneratedKeys();
			if (keys.next()) {
				tempID = keys.getString(1);
			}
			
			if (!(tempID.isEmpty())) {
			if (t instanceof Accessorio) {
				psg = con.prepareStatement(insertSQL1);
				psg.setString(1, tempID);
			} else if (t instanceof Arma) {
				psg = con.prepareStatement(insertSQL2);
				psg.setString(1, tempID);
				psg.setString(2,((Arma) t).getMateriale());
				psg.setString(3, ((Arma) t).getTipo());
				psg.setString(4,((Arma) t).getUtilizzo());
			} else if (t instanceof Armatura) {
				psg = con.prepareStatement(insertSQL3);
				psg.setString(1, tempID);
				psg.setString(2, ((Armatura) t).getMateriale());
				psg.setString(3, ((Armatura) t).getPezzo());
			}
			else if (t instanceof Abbigliamento) {
				psg = con.prepareStatement(insertSQL4);
				psg.setString(1, tempID);
				psg.setString(2, ((Abbigliamento) t).getGenere());
				psg.setString(3, ((Abbigliamento) t).getTipo());
				} else con.rollback();
			} else con.rollback();
			
			psg.executeUpdate();
			con.commit();
			
		} finally {
			try {
				if (ps != null) ps.close();
			} finally {
			DMConnectionPool.releaseConnection(con);
		}
	}
	return true;
	}

	public synchronized Prodotto doRetrieveByKey(String code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String categoria = "";
		Prodotto obj = null;
		
		String selectSQL = "SELECT * FROM Prodotto p LEFT JOIN Arma a1 on p.Nome = a1.ID_Prodotto"
				+ " LEFT JOIN Armatura a2 on p.Nome = a2.ID_Prodotto"
				+ " LEFT JOIN Abbigliamento a3 on p.Nome = a3.ID_Prodotto"
				+ " LEFT JOIN Accessorio a4 on p.Nome = a4.ID_Prodotto"
				+ " WHERE p.Nome = ?";
		
		try {	
			con = DMConnectionPool.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				if(rs.getString("ID_Abbigliamento") != null) categoria = "Abbigliamento";
                else if(rs.getString("ID_Accessorio") != null) categoria = "Accessorio";
                else if(rs.getString("ID_Arma") != null) categoria = "Arma";
                else if(rs.getString("ID_Armatura") != null) categoria = "Armatura";
                else throw new SQLException("Errore nel recupero del prodotto");
				
				switch(categoria) {
				
				case "Abbigliamento": {
				  obj = new Abbigliamento(
						  rs.getString("Nome"),
						  rs.getDouble("Prezzo"),
						  rs.getString("Descrizione"),
						  rs.getInt("Giacenza"),
						  rs.getInt("img1"),
						  rs.getInt("img2"),
						  rs.getInt("img3"),
						  rs.getInt("ID_Abbigliamento"),
						  rs.getString("Tipo"),
						  rs.getString("Materiale")
						  );
				  		break;

				}
				case "Accessorio": {
					obj = new Accessorio(
							rs.getString("Nome"),
							rs.getDouble("Prezzo"),
							rs.getString("Descrizione"),
							rs.getInt("Giacenza"),
							rs.getInt("img1"),
							rs.getInt("img2"),
							rs.getInt("img3"),
							rs.getInt("ID_Accessorio")
							  );
					break;
				}
				case "Arma": {
					obj = new Arma(
							  rs.getString("Nome"),
							  rs.getDouble("Prezzo"),
							  rs.getString("Descrizione"),
							  rs.getInt("Giacenza"),
							  rs.getInt("img1"),
							  rs.getInt("img2"),
							  rs.getInt("img3"),
							  rs.getInt("ID_Arma"),
							  rs.getString("Materiale"),
							  rs.getString("Tipo"),
							  rs.getString("Utilizzo")
							  );
					break;
				}
				case "Armatura": {
					obj = new Armatura(
					  rs.getString("Nome"),
					  rs.getDouble("Prezzo"),
					  rs.getString("Descrizione"),
					  rs.getInt("Giacenza"),
					  rs.getInt("img1"),
					  rs.getInt("img2"),
					  rs.getInt("img3"),
					  rs.getInt("ID_Armatura"),
					  rs.getString("Materiale"),
					  rs.getString("Pezzo")
					);
					break;
				}
				default : {obj = null; break;}
				}
				
			}
			} finally {
				try {
					if (ps != null) ps.close();
				} finally {
					DMConnectionPool.releaseConnection(con);
				}
			}
		return obj;
		
	}
	
	
	@Override
	public synchronized boolean doDelete(Prodotto t) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String deleteSQL = "DELETE FROM Prodotto WHERE Prodotto.ID = ?";
			try {
				con = DMConnectionPool.getConnection();
				ps = con.prepareStatement(deleteSQL);
				ps.setString(1, t.getNome());
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
	
	@Override 
	public synchronized boolean doDeletebyID(String Nome) throws SQLException {
			Connection con = null;
			PreparedStatement ps = null;
			int result = 0;
			
			String deleteSQL = "DELETE FROM Prodotto WHERE Prodotto.Nome = ?";
				try {
					con = DMConnectionPool.getConnection();
					ps = con.prepareStatement(deleteSQL);
					ps.setString(1, Nome);
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

	@Override
	public synchronized Collection<Prodotto> doRetrieveByCategory(String tipo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String categoria = tipo;
		Collection<Prodotto> obj = new LinkedList<Prodotto>();
		
		String selectSQL = "SELECT * FROM Prodotto p JOIN "+ categoria +" c ON p.Nome = c.ID_Prodotto"; 
		try {	
			con = DMConnectionPool.getConnection();
			ps = con.prepareStatement(selectSQL);
			rs = ps.executeQuery();
			switch(categoria) {
				case "Abbigliamento": {
				  while(rs.next()) {
				  obj.add( new Abbigliamento(
						  rs.getString("Nome"),
						  rs.getDouble("Prezzo"),
						  rs.getString("Descrizione"),
						  rs.getInt("Giacenza"),
						  rs.getInt("img1"),
						  rs.getInt("img2"),
						  rs.getInt("img3"),
						  rs.getInt("ID_Abbigliamento"),
						  rs.getString("Tipo"),
						  rs.getString("Genere")
						  ));
				  }
				  break;
				}
				case "Accessorio": {
					while (rs.next()) {
					obj.add( new Accessorio(
							rs.getString("Nome"),
							rs.getDouble("Prezzo"),
							rs.getString("Descrizione"),
							rs.getInt("Giacenza"),
							rs.getInt("img1"),
							rs.getInt("img2"),
							rs.getInt("img3"),
							rs.getInt("ID_Accessorio")
						  ));
				       }
					break;
				}
				case "Arma": {
					while(rs.next()) {
					obj.add(new Arma(
							  rs.getString("Nome"),
							  rs.getDouble("Prezzo"),
							  rs.getString("Descrizione"),
							  rs.getInt("Giacenza"),
							  rs.getInt("img1"),
							  rs.getInt("img2"),
							  rs.getInt("img3"),
							  rs.getInt("ID_Arma"),
							  rs.getString("Materiale"),
							  rs.getString("Tipo"),
							  rs.getString("Utilizzo")
							  ));
					}
					break;
				}
				case "Armatura": {
					while(rs.next()) {
					obj.add( new Armatura(
					  rs.getString("Nome"),
					  rs.getDouble("Prezzo"),
					  rs.getString("Descrizione"),
					  rs.getInt("Giacenza"),
					  rs.getInt("img1"),
					  rs.getInt("img2"),
					  rs.getInt("img3"),
					  rs.getInt("ID_Armatura"),
					  rs.getString("Materiale"),
					  rs.getString("Pezzo")
					));
					}
					break;
				}
				default : {obj.add(new Abbigliamento("Nome", 0, "Descrizione", 0, 1, 1, 1, 1, "", "")); break;}
				}
			} finally {
				try {
					if (ps != null) ps.close();
				} finally {
					DMConnectionPool.releaseConnection(con);
				}
			}
		return obj;
	  }
	}
	
