package modelMVC;

import java.util.ArrayList;

public class Pronostico implements IPronostico {
	
	private int id;
	private ArrayList<String> resultsList;
	private Schedina schedina;
	private int punti;
	
	public Pronostico(int id, ArrayList<String> resultsList, Schedina schedina) {
		super();
		this.id = id;
		this.resultsList = resultsList;
		this.schedina = schedina;
	}
	
	@Override
	public void calcolaPunti() {
		int pron_corretti = 0;
		
		for(int i=0; i<10; i++)
			if (schedina.getEsito().getResultsList().get(i).equals(resultsList.get(i)))
				pron_corretti++;
		
		// Assegna 10 punti per ogni risultato correttamente pronosticato, ed ulteriori 100 se sono tutti corretti
		setPunti(pron_corretti*10 + ((pron_corretti==10)?100:0) );
		
		
		// aggiorna l'utenteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	public int getPunti() {
		return punti;
	}

	public void setPunti(int punti) {
		this.punti = punti;
	}
	
	

}
