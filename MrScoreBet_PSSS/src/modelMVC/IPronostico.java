package modelMVC;

import java.sql.SQLException;

import utils.exceptions.UserNotFoundException;

public interface IPronostico {
	void calcolaPunti() throws UserNotFoundException, SQLException;
}
