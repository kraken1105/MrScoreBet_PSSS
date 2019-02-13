package modelMVC;

import utils.exceptions.*;

public interface ICoordinatorFacade {
	boolean effettuaRegistrazione(String email, String username, String password) throws UsernameAlreadyRegisteredException;
	Utente login(String username, String password) throws UserNotFoundException;
}
