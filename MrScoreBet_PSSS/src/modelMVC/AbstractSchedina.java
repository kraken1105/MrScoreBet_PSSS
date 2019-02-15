package modelMVC;

import java.sql.SQLException;
import java.util.ArrayList;

import persistencyDAO.DBManager;
import persistencyDAO.PronosticoDAO;
import persistencyDAO.Utility;
import utils.exceptions.UserNotFoundException;

public abstract class AbstractSchedina {
	
	
	abstract void updatePronostici();

}
