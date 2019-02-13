package modelMVC;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Schedina {
	
		private int giornata;
		private LocalDateTime dataScadenza;
		private ArrayList<String> gameList;
		private int esito;
		
		public Schedina(int giornata, LocalDateTime dataScadenza, ArrayList<String> gameList, int esito) {
			super();
			this.giornata = giornata;
			this.dataScadenza = dataScadenza;
			this.gameList = gameList;
			this.esito = esito;
		}

		public int getGiornata() {
			return giornata;
		}

		public void setGiornata(int giornata) {
			this.giornata = giornata;
		}

		public LocalDateTime getDataScadenza() {
			return dataScadenza;
		}

		public void setDataScadenza(LocalDateTime dataScadenza) {
			this.dataScadenza = dataScadenza;
		}

		public ArrayList<String> getGameList() {
			return gameList;
		}

		public void setGameList(ArrayList<String> gameList) {
			this.gameList = gameList;
		}

		public int getEsito() {
			return esito;
		}

		public void setEsito(int esito) {
			this.esito = esito;
		}	
				

	}
