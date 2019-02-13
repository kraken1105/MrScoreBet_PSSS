package persistencyDAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import modelMVC.Esito;
import modelMVC.Schedina;

public class SchedinaDAO{
	
	// 1) Create
	public static void create(Schedina schedina) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try {
			s = conn.prepareStatement("INSERT INTO SCHEDINE (giornata,dataScadenza," + 
					"match1,match2,match3,match4,match5,match6,match7,match8,match9,match10," + 
					"esito) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			s.setInt(1, schedina.getGiornata());
			s.setString(2, schedina.getDataScadenza().toString());
			s.setNull(13, java.sql.Types.INTEGER);
			
			for(int i=0; i<10; i++)
				s.setString(i+3, schedina.getGameList().get(i));
			
			s.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
		
	}
	
	// 2) Read
	public static Schedina read(int giornata) throws SQLException {
		Schedina schedina = null;
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("SELECT * FROM SCHEDINE WHERE giornata=?");
			s.setInt(1, giornata);
			ResultSet rs = s.executeQuery();
				
			while (rs.next()) {
				int numGiornata = rs.getInt("giornata");
				LocalDateTime dataScadenza = LocalDateTime.parse(rs.getString("dataScadenza"), DateTimeFormatter.ISO_DATE_TIME);
				ArrayList<String> gameList = new ArrayList<String>();				
							
				for(int i=1; i<11; i++)
					gameList.add(new String(rs.getString("match"+i)));
				Esito esito = EsitoDAO.read(rs.getInt("esito"));			
				schedina= new Schedina(numGiornata, dataScadenza, gameList,esito);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (s != null) s.close();
		}
		
		return schedina;		
	}
	
	// 3) Update
	//	  Richiamato solo quando un admin inserisce gli esiti
	public static void update(Schedina schedina) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
			
		try {
			String comando = new String("UPDATE SCHEDINE SET esito=? WHERE giornata=?");
			s = conn.prepareStatement(comando);
			s.setInt(1, schedina.getEsito().getId());
			s.setInt(2, schedina.getGiornata());
			
			s.executeUpdate();
						
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
	}
	
	public static void delete(Schedina schedina) throws SQLException{
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try {
			s = conn.prepareStatement("DELETE FROM SCHEDINE WHERE giornata=?");
			s.setInt(1, schedina.getGiornata());
			
			s.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
	}
}
