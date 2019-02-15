package modelMVC;

import java.time.LocalDateTime;
import java.util.ArrayList;

import utils.exceptions.*;

public interface ICoordinatorFacade {
	boolean effettuaRegistrazione(String email, String username, String password) throws UsernameAlreadyRegisteredException;
	Utente login(String username, String password) throws UserNotFoundException;
	boolean inserisciSchedina(ArrayList<String> matchlist, int giornata, LocalDateTime dataScadenza) throws EsitoNotInsertedException;
	boolean inserisciEsito(int giornata, ArrayList<String> risultsList) throws EsitoAlreadyInsertedException;
	boolean inserisciPronostico(Utente u, ArrayList<String> pronList) throws SchedinaNotAvailableException;
}
