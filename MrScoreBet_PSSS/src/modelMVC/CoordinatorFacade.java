package modelMVC;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import persistencyDAO.*;
import utils.exceptions.EsitoAlreadyInsertedException;
import utils.exceptions.EsitoNotInsertedException;
import utils.exceptions.UserNotFoundException;
import utils.exceptions.UsernameAlreadyRegisteredException;

public class CoordinatorFacade implements ICoordinatorFacade {

	@Override
	public boolean effettuaRegistrazione(String email, String username, String password)
	throws UsernameAlreadyRegisteredException {		
		Utente utente = new Utente(username,email,password,0,"utente",null,null);
		
		DBManager.getInstance().LockDB();							// lock DB
		try {
			UtenteDAO.create(utente);
			return true;
		}
		catch(SQLException e) {e.printStackTrace(); return false;}
		finally {DBManager.getInstance().UnlockDB();}				// unlock DB
	}

	@Override
	public Utente login(String username, String password) throws UserNotFoundException {
		DBManager.getInstance().LockDB();							// lock DB
		try {
			Utente utente = UtenteDAO.read(username);
			return utente;
		}
		catch(SQLException e) {e.printStackTrace(); return null;}
		finally {DBManager.getInstance().UnlockDB();}				// unlock DB
	}

	@Override
	public boolean inserisciSchedina(ArrayList<String> matchlist, int giornata, LocalDateTime dataScadenza) throws EsitoNotInsertedException {
		DBManager.getInstance().LockDB();							// lock DB
		Schedina s;
		try {
			s = Utility.getSchedinaSenzaEsito();
			if(s!=null) 
			throw new EsitoNotInsertedException("L'esito della giornata "+s.getGiornata()+" non è stato ancora inserito");
		else {
				s = new Schedina(giornata, dataScadenza, matchlist, null);
				SchedinaDAO.create(s);
				ArrayList<Utente> utenti = Utility.getAllUsers();
				for(Utente u:utenti) {
					u.setToPlayBet(s);
					UtenteDAO.update(u);
				}
			} 
		}
		catch (UserNotFoundException|SQLException e) {e.printStackTrace(); return false;}
		finally {DBManager.getInstance().UnlockDB();}				// unlock DB
		return true;
	}

	@Override
	public boolean inserisciEsito(int giornata, ArrayList<String> resultsList) throws EsitoAlreadyInsertedException {
		DBManager.getInstance().LockDB();					// unlock DB
		try {
			Schedina s = Utility.getSchedinaSenzaEsito();
			if(s==null) 
				throw new EsitoAlreadyInsertedException("L'esito della giornata "+giornata+" è stato già inserito");
			else {
				Esito e = new Esito(null,resultsList);
				int id = EsitoDAO.create(e);
				e.setId(id);
				s.setEsito(e);
				SchedinaDAO.update(s);
				s.updatePronostici();
				}
		}
		catch (SQLException e) {e.printStackTrace(); return false;}
		finally {DBManager.getInstance().UnlockDB();}				// unlock DB
		return true;
	}
	

}
