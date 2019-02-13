package modelMVC;

public class Utente {
	
		private String username;
		private String email;
		private String password;
		private String ruolo;
		private int crediti;	
		private Pronostico lastPlayedBet;
		private Schedina toPlayBet;
		
		public Utente(String username, String email, String password, String ruolo, int crediti, Pronostico lastPlayedBet, Schedina toPlayBet) {
			super();
			this.username = username;
			this.email = email;
			this.password= password;
			this.ruolo = ruolo;
			this.crediti = crediti;
			this.lastPlayedBet = lastPlayedBet;
			this.toPlayBet = toPlayBet;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRuolo() {
			return ruolo;
		}

		public void setRuolo(String ruolo) {
			this.ruolo = ruolo;
		}

		public int getCrediti() {
			return crediti;
		}

		public void setCrediti(int crediti) {
			this.crediti = crediti;
		}

		public Pronostico getLastPlayedBet() {
			return lastPlayedBet;
		}

		public void setLastPlayedBet(Pronostico lastPlayedBet) {
			this.lastPlayedBet = lastPlayedBet;
		}

		public Schedina getToPlayBet() {
			return toPlayBet;
		}

		public void setToPlayBet(Schedina toPlayBet) {
			this.toPlayBet = toPlayBet;
		}

}
