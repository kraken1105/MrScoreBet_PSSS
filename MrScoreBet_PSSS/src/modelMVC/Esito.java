package modelMVC;

import java.util.ArrayList;

public class Esito {

	private Integer id;
	private ArrayList<String> resultsList;
	
	public Esito(Integer id, ArrayList<String> resultsList) {
		super();
		this.id = id;
		this.resultsList = resultsList;
	}

	public Integer getId() {
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
	
}
