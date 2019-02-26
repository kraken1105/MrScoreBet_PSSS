package persistencyDAO;

import java.sql.*;
import java.util.ArrayList;

import modelMVC.Pronostico;
import modelMVC.Schedina;

public class PronosticoDAO {
	
	// 1) Create			
	public static int create(Pronostico pronostico) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		int generated_ID = -1;
		
		try {
			s = conn.prepareStatement("INSERT INTO PRONOSTICI ("+
					"match1,match2,match3,match4,match5,match6,match7," + 
					"match8,match9,match10,schedina,punti) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			for(int i=1; i<11; i++)
				s.setString(i, pronostico.getResultsList().get(i-1));
			s.setInt(11, pronostico.getSchedina().getGiornata());
			s.setNull(12, java.sql.Types.INTEGER);
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
				for(int i=1; i<11; i++) array.add(rs.getString("match"+i));
				schedina = SchedinaDAO.read(rs.getInt("schedina"));
				int punti = rs.getInt("punti");
				pronostico = new Pronostico(ID,array,schedina,punti);
			}
			
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
	public static void update(Pronostico p) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
			
		try {
			String comando = new String("UPDATE PRONOSTICI SET punti=? WHERE id=?");
			s = conn.prepareStatement(comando);
			s.setInt(1, p.getPunti());
			s.setInt(2, p.getId());
			
			s.executeUpdate();
						
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
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