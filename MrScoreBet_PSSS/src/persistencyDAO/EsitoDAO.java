package persistencyDAO;

import java.sql.*;
import java.util.ArrayList;

import modelMVC.Esito;

public class EsitoDAO {
		
		// 1) Create			
		public static int create(Esito esito) throws SQLException {
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement s = null;
			int generated_ID = -1;
			
			try {
				s = conn.prepareStatement("INSERT INTO ESITI ("+
						"match1,match2,match3,match4,match5,match6,match7," + 
						"match8,match9,match10) VALUES (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				
				for(int i=1; i<11; i++)
					s.setString(i, esito.getResultsList().get(i-1));
				
				s.executeUpdate();
				
				ResultSet rs = s.getGeneratedKeys();
			    if(rs.next()) {
			    	generated_ID = rs.getInt(1);
			    }
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				if (s != null) s.close();
			}
			
			return generated_ID;
		}
		
		// 2) Read
		public static Esito read(int id) throws SQLException {
			Esito esito= null;
			ArrayList<String> array = new ArrayList<String>();
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement s = null;
			
			try { 
				s = conn.prepareStatement("SELECT * FROM ESITI WHERE id=?");
				s.setInt(1, id);
				ResultSet rs = s.executeQuery();
					
				while (rs.next()) {
					for(int i=1; i<11; i++) array.add(rs.getString("match"+i));
					esito = new Esito(id,array);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (NullPointerException ex) {
				System.out.println(ex.getMessage());
			} finally {
				if (s != null) s.close();
			}
			
			return esito;		
		}
		
		// 3) Update
		public static void update(Esito e) throws SQLException {
			/*da implementare in base alle esigenze
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement s = null;
				
			try {
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				if (s != null) s.close();
			}
			*/
		}
		
		
		// 4) Delete
			public static void delete(Esito esito) throws SQLException{
				Connection conn = DBManager.getInstance().getConnection();
				PreparedStatement s = null;
				
				try {
					s = conn.prepareStatement("DELETE FROM ESITI WHERE id=?");
					s.setInt(1, esito.getId());
					
					s.executeUpdate();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} finally {
					if (s != null) s.close();
				}
			}
			

	}

