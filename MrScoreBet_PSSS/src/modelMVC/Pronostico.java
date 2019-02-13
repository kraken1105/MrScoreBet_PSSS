package modelMVC;

import java.util.ArrayList;

public class Pronostico {
	
	private int id;
	private ArrayList<String> resultsList;
	private Schedina schedina;
	
	public Pronostico(int id, ArrayList<String> resultsList, Schedina schedina) {
		super();
		this.id = id;
		this.resultsList = resultsList;
		this.schedina = schedina;
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
	
	

}
