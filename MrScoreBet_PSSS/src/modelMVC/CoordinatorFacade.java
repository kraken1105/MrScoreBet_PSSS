package modelMVC;

import java.sql.SQLException;

import persistencyDAO.*;
import utils.exceptions.UserNotFoundException;
import utils.exceptions.UsernameAlreadyRegisteredException;

public class CoordinatorFacade implements ICoordinatorFacade {

	@Override
	public boolean effettuaRegistrazione(String email, String username, String password)
	throws UsernameAlreadyRegisteredException {		
		Utente utente = new Utente(username,email,password,"utente",0,null,null);
		
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
	

}
