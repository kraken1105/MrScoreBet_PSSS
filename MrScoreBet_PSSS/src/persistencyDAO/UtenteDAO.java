package persistencyDAO;

import java.sql.*;
import java.util.ArrayList;

import modelMVC.Pronostico;
import modelMVC.Schedina;
import modelMVC.Utente;
import utils.exceptions.UserNotFoundException;
import utils.exceptions.UsernameAlreadyRegisteredException;

public class UtenteDAO {
	
	// 1) Create
	public static void create(Utente u) throws SQLException, UsernameAlreadyRegisteredException{
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try {
			s = conn.prepareStatement("INSERT INTO UTENTI VALUES (?,?,?,?,?,?,?)");
			s.setString(1, u.getUsername());
			s.setString(2, u.getEmail());
			s.setString(3, u.getPassword());
			s.setInt(4, 0);
			s.setString(5, u.getRuolo());
			if (u.getToPlayBet()==null) s.setNull(6, java.sql.Types.INTEGER);	// non esiste alcuna toPlayBet nel db
			else s.setInt(6, u.getToPlayBet().getGiornata());
			s.setNull(7, java.sql.Types.INTEGER);	// lastPlayedBet è null	
			
			s.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode()==19) throw new UsernameAlreadyRegisteredException("Username "+u.getUsername()+" già utilizzato!");
			else throw e;
		} finally {
			if (s != null) s.close();
		}
	}
	
	// 2) Read
	//    Nota: se viene lanciata una UserNotFoundException significa che l'utente non è presente nel db,
	//			pertanto va fatta una Create dall'esterno.
	public static Utente read(String username) throws SQLException, UserNotFoundException {
		Utente utente = null;
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("SELECT * FROM UTENTI WHERE username=?");
			s.setString(1, username);
			ResultSet rs = s.executeQuery();
			
			if (rs.next()) {
				String email = rs.getString("email");
				String password = rs.getString("password");
				int crediti = rs.getInt("crediti");
				String ruolo = rs.getString("ruolo");
					
				Pronostico lastPlayedBet = PronosticoDAO.read(rs.getInt("lastPlayedBet"));
				Schedina toPlayBet = SchedinaDAO.read(rs.getInt("toPlayBet"));
				
				utente = new Utente(username, email, password, ruolo, crediti, lastPlayedBet, toPlayBet);
			} else {
				throw new UserNotFoundException("Utente "+username+" non presente nel sistema!");
			}
			
			
		} catch (SQLException|NullPointerException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
	
		return utente;		
	}
	
	// 3) Update
	public static void update(Utente u) throws SQLException {
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
			
		try {
			s = conn.prepareStatement("UPDATE UTENTI SET crediti=?, lastPlayedBet = ?, toPlayBet=? WHERE username=?");
			
			s.setInt(1, u.getCrediti());
			
			if(u.getLastPlayedBet() == null) s.setNull(3, java.sql.Types.INTEGER);
			else s.setInt(3, u.getLastPlayedBet().getSchedina().getGiornata());
			
			if(u.getToPlayBet() == null) s.setNull(4, java.sql.Types.INTEGER);
			else s.setInt(4, u.getToPlayBet().getGiornata());
			
			s.setString(5, u.getUsername());
			
			s.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
	}
	
	// 4) Delete
	public static void delete(Utente u) throws SQLException{
		Pronostico p = u.getLastPlayedBet();
		if(p!=null) PronosticoDAO.delete(p);		
		
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("DELETE FROM UTENTI WHERE username=?");
			s.setString(1, u.getUsername());
			
			s.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
	}

}
