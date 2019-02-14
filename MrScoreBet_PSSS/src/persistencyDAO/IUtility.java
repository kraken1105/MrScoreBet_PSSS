package persistencyDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import modelMVC.*;
import utils.exceptions.UserNotFoundException;

public interface IUtility {
	Utente getUtenteDaPronostico(Pronostico p) throws UserNotFoundException, SQLException;
	ArrayList<Utente> getAllUsers() throws UserNotFoundException, SQLException;
	ArrayList<Pronostico> getPronosticiDaSchedina(Schedina s) throws SQLException;
	Schedina getSchedinaSenzaEsito();

}
