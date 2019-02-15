package persistencyDAO;



import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modelMVC.Esito;
import modelMVC.Pronostico;
import modelMVC.Schedina;
import modelMVC.Utente;
import utils.exceptions.UserNotFoundException;
import utils.exceptions.UsernameAlreadyRegisteredException;

public class testDAO {

	public static void main(String[] args) throws SQLException, UserNotFoundException, UsernameAlreadyRegisteredException {
		
		/*TEST UTENTEDAO
		Utente u=new Utente("marco","CASA@lui.it","password19191919191919",0,"utente",null,null);
		UtenteDAO.delete(u);
		UtenteDAO.create(u);
		LocalDateTime l=LocalDateTime.of(2018, 12, 23, 12, 30);
		ArrayList<String> a = new ArrayList<String>();
		for(int i=0;i<10;i++) a.add("a");
		Schedina s = new Schedina(2,l,a,null);
		u.setToPlayBet(s);
		u.setCrediti(100);
		UtenteDAO.update(u);
		*/
				
		
		/*TEST PRONOSTICODAO
		//********* Inserimento pronostico ****************
		LocalDateTime l=LocalDateTime.of(2018, 12, 23, 12, 30);
		ArrayList<String> gameList = new ArrayList<String>();
		gameList.add("Juventus-Inter");
		gameList.add("Napoli-Frosinone");
		gameList.add("Cagliari-Roma");
		gameList.add("Lazio-Sampdoria");
		gameList.add("Sassuolo-Fiorentina");
		gameList.add("Empoli-Bologna");
		gameList.add("Parma-Chievo");
		gameList.add("Udinese-Atalanta");
		gameList.add("Genoa-Spal");
		gameList.add("Milan-Torino");	
				ArrayList<String> resultList = new ArrayList<String>();
				for(int i=0;i<10;i++) resultList.add("X");	
				Schedina s = new Schedina(2,l,gameList,null);
				SchedinaDAO.create(s);
				Pronostico p = new Pronostico(null,resultList,s,null);
				System.out.println(PronosticoDAO.read(3).getSchedina().getGiornata());
			*/	
				
		/*TEST SCHEDINA	
		ArrayList<String> gameList = new ArrayList<String>();
		gameList.add("Juventus-Inter");
		gameList.add("Napoli-Frosinone");
		gameList.add("Cagliari-Roma");
		gameList.add("Lazio-Sampdoria");
		gameList.add("Sassuolo-Fiorentina");
		gameList.add("Empoli-Bologna");
		gameList.add("Parma-Chievo");
		gameList.add("Udinese-Atalanta");
		gameList.add("Genoa-Spal");
		gameList.add("Milan-Torino");
		LocalDateTime l=LocalDateTime.of(2018, 12, 23, 12, 30);
		Schedina s=SchedinaDAO.read(2);
		for(int i=0;i<10;i++) System.out.println(s.getGameList().get(i));
		Schedina s1 = new Schedina(3,l,gameList,null);
		SchedinaDAO.delete(s1);
		*/
		
		/*TEST ESITODAO
		ArrayList<String> a = new ArrayList<String>();
		for(int i=0;i<10;i++) a.add("a");
		Esito e =new Esito(null,a);
		e.setId(1);
		EsitoDAO.delete(e);
		*/

	}

}
