package modelMVC;

import java.sql.SQLException;

import persistencyDAO.DBManager;
import persistencyDAO.PronosticoDAO;

public abstract class AbstractSchedina {
	
	void updatePronostici() {
		boolean stop = false;
		int i = 1;
		while(!stop) {
			DBManager.getInstance().LockDB();							// lock DB
			try {
				Pronostico p = PronosticoDAO.read(i);
				if(p!=null) {
					p.calcolaPunti();
					PronosticoDAO.update(p);
				}
				else stop=true;			
			} catch (SQLException e) {e.printStackTrace();}
			finally {DBManager.getInstance().UnlockDB();}				// unlock DB
		}
		
	}

}
