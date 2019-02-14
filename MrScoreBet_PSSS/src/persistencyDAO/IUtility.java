package persistencyDAO;

import java.util.ArrayList;

import modelMVC.*;

public interface IUtility {
	Utente getUtenteDaPronostico(Pronostico p);
	ArrayList<Utente> getAllUsers();
	ArrayList<Pronostico> getPronosticiDaSchedina(Schedina s);
	Schedina getSchedinaSenzaEsito();

}
