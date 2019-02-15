package modelMVC;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import utils.exceptions.*;

public interface ICoordinatorFacade {
	boolean effettuaRegistrazione(String email, String username, String password) throws UsernameAlreadyRegisteredException;
	Utente login(String username, String password) throws UserNotFoundException;
	boolean inserisciSchedina(ArrayList<String> matchlist, int giornata, LocalDateTime dataScadenza) throws SQLException, EsitoNotInsertedException;
	boolean inserisciEsito(int giornata, ArrayList<String> risultsList) throws EsitoAlreadyInsertedException;
}
