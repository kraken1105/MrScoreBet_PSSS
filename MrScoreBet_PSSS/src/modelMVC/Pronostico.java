package modelMVC;

import java.sql.SQLException;
import java.util.ArrayList;

import persistencyDAO.PronosticoDAO;
import persistencyDAO.UtenteDAO;
import persistencyDAO.Utility;
import utils.exceptions.UserNotFoundException;

public class Pronostico implements IPronostico {
	
	private Integer id;
	private ArrayList<String> resultsList;
	private Schedina schedina;
	private Integer punti;
	
	public Pronostico(Integer id, ArrayList<String> resultsList, Schedina schedina, Integer punti) {
		super();
		this.id = id;
		this.resultsList = resultsList;
		this.schedina = schedina;
		this.punti = punti;
	}
	
	@Override
	public void calcolaPunti() throws UserNotFoundException, SQLException {
		int pron_corretti = 0;
		
		for(int i=0; i<10; i++)
			if (schedina.getEsito().getResultsList().get(i).equals(resultsList.get(i)))
				pron_corretti++;
		
		// Assegna 10 punti per ogni risultato correttamente pronosticato, ed ulteriori 100 se sono tutti corretti
		setPunti(pron_corretti*10 + ((pron_corretti==10)?100:0) );		
		
		// aggiorna l'utente
		Utente u = Utility.getUtenteDaPronostico(this);
		u.setCrediti(u.getCrediti()+punti);
		UtenteDAO.update(u);
		PronosticoDAO.update(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<String> getResultsList() {
		return resultsList;
	}

	public void setResultsList(ArrayList<String> resultsList) {
		this.resultsList = resultsList;
	}

	public Schedina getSchedina() {
		return schedina;
	}

	public void setSchedina(Schedina schedina) {
		this.schedina = schedina;
	}
	
	public Integer getPunti() {
		return punti;
	}

	public void setPunti(Integer punti) {
		this.punti = punti;
	}
	
	

}
