package persistencyDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelMVC.*;
import utils.exceptions.UserNotFoundException;

public class Utility implements IUtility {

	@Override
	public Utente getUtenteDaPronostico(Pronostico p) throws UserNotFoundException, SQLException {
		Utente utente = null;
		String username=null;
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		try { 
			s = conn.prepareStatement("SELECT * FROM UTENTI WHERE lastPlayedBet=?");
			s.setInt(1, p.getId());
			ResultSet rs = s.executeQuery();
			
			if (rs.next()) {
				username = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int crediti = rs.getInt("crediti");
				String ruolo = rs.getString("ruolo");
					
				Schedina toPlayBet = SchedinaDAO.read(rs.getInt("toPlayBet"));
				
				utente = new Utente(username, email, password, crediti,ruolo, p, toPlayBet);
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

	@Override
	public ArrayList<Utente> getAllUsers() throws UserNotFoundException, SQLException {
		ArrayList<Utente> utenti = new ArrayList<Utente>();
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("SELECT * FROM UTENTI");
			ResultSet rs = s.executeQuery();
			
			while (rs.next()) {
				String username = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int crediti = rs.getInt("crediti");
				String ruolo = rs.getString("ruolo");
				Pronostico lastPlayedBet = PronosticoDAO.read(rs.getInt("lastPlayedBet"));
				Schedina toPlayBet = SchedinaDAO.read(rs.getInt("toPlayBet"));
				Utente utente = new Utente(username, email, password, crediti, ruolo, lastPlayedBet, toPlayBet);
				utenti.add(utente);
			} 
			if(utenti.size()==0) throw new UserNotFoundException("Nessun utente nel sistema!");
		} catch (SQLException|NullPointerException e) {
			System.out.println(e.getMessage());
		} finally {
			if (s != null) s.close();
		}
	
		return utenti;
	}

	@Override
	public ArrayList<Pronostico> getPronosticiDaSchedina(Schedina schedina) throws SQLException {
		ArrayList<Pronostico> pronostici= new ArrayList<Pronostico>();
		ArrayList<String> array = new ArrayList<String>();
		Connection conn = DBManager.getInstance().getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("SELECT * FROM PRONOSTICI WHERE schedina=?");
			s.setInt(1, schedina.getGiornata());
			ResultSet rs = s.executeQuery();
				
			while (rs.next()) {
				int id = rs.getInt("id");
				int punti= rs.getInt("punti");
				for(int i=1; i<11; i++) array.set(i-1,rs.getString("match"+i));
				Pronostico pronostico = new Pronostico(id,array,schedina,punti);
				pronostici.add(pronostico);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (s != null) s.close();
		}
		
		return pronostici;	
	}

	@Override
	public Schedina getSchedinaSenzaEsito() {
		// TODO Auto-generated method stub
		return null;
	}

}
