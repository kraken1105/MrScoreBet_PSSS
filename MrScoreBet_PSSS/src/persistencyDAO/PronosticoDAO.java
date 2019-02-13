package persistencyDAO;

import java.sql.*;
import java.util.ArrayList;

import modelMVC.Pronostico;
import modelMVC.Schedina;

public class PronosticoDAO {
	
	// 1) Create
	//	  Nota: restituisce l'ID generato dal DB, il chiamante lo deve assegnare alla schedina.
	//			Questo metodo viene invocato unicamente quando un utente gioca una nuova schedina.			
	public static int create(Pronostico pronostico) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		int generated_ID = -1;
		
		try {
			s = conn.prepareStatement("INSERT INTO PRONOSTICI ("+
					"match1,match2,match3,match4,match5,match6,match7," + 
					"match8,match9,match10,schedina) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			for(int i=1; i<11; i++)
				s.setString(i, pronostico.getResultsList().get(i));
			s.setInt(11, pronostico.getSchedina().getGiornata());
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
	public static Pronostico read(int ID) throws SQLException {
		Schedina schedina = null;
		Pronostico pronostico= null;
		ArrayList<String> array = new ArrayList<String>();
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("SELECT * FROM PRONOSTICI WHERE id=?");
			s.setInt(1, ID);
			ResultSet rs = s.executeQuery();
				
			while (rs.next()) {
				for(int i=1; i<11; i++) array.set(i-1,rs.getString("match"+i));
				schedina = SchedinaDAO.read(rs.getInt("schedina"));
			}
			
			pronostico = new Pronostico(ID,array,schedina);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (s != null) s.close();
		}
		
		return pronostico;		
	}
	
	// 3) Update
	public static void update(Pronostico b) throws SQLException {
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
		public static void delete(Pronostico pronostico) throws SQLException{
			Connection conn = DBManager.getInstance().getConnection();
			PreparedStatement s = null;
			
			try {
				s = conn.prepareStatement("DELETE FROM PRONOSTICI WHERE id=?");
				s.setInt(1, pronostico.getId());
				
				s.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				if (s != null) s.close();
			}
		}
		

}