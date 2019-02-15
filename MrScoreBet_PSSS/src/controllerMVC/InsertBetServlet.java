package controllerMVC;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import modelMVC.*;
import utils.exceptions.EsitoNotInsertedException;

@WebServlet("/admin/InsertBetServlet")
public class InsertBetServlet extends HttpServlet {
	private static final long serialVersionUID = -2106391654584820746L;

	public InsertBetServlet() {super();}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// Convalida i dati della form anche server-side
		boolean datiOk = true;		
		
		// Prelievo delle informazioni sulla giornata
		Integer giornata = Integer.parseInt(request.getParameter("numGiornata"));
		LocalDateTime orarioScadenza = LocalDateTime.parse(request.getParameter("dataScadenza")
				+"T"+request.getParameter("oraScadenza"), DateTimeFormatter.ISO_DATE_TIME);
		if(giornata==null||orarioScadenza==null) datiOk = false;
		
		// Prelievo della lista dei match
		ArrayList<String> gameList = new ArrayList<String>();
		for(int i=1; i<11; i++) {
			String temp = new String(request.getParameter("match"+i));
			if(temp.equals("")) {datiOk = false; break;}
			gameList.add((String) request.getParameter("match"+i));			
		}
		
		if(!datiOk) {
			session.setAttribute("info", "Si è verificato un errore! Tutti i campi sono obbligatori");
			response.sendRedirect(request.getContextPath()+"/admin/insertBet.jsp");
		} else {
			
			// Controlli superati con successo, viene contattato il coordinatore del Model
			ICoordinatorFacade coord = new CoordinatorFacade();
			try {
				boolean esito = coord.inserisciSchedina(gameList, giornata, orarioScadenza);
				
				if (esito) {
					session.setAttribute("info", "Schedina inserita correttamente!");
					response.sendRedirect(request.getContextPath()+"/user.jsp");
				} else {
					session.setAttribute("info", "Si è verificato un errore. Riprova più tardi.");
					response.sendRedirect(request.getContextPath()+"/user.jsp");
				}
				
			} catch(EsitoNotInsertedException e) {
				session.setAttribute("info", e.getMessage());
				response.sendRedirect(request.getContextPath()+"/user.jsp");
			}			
			
		}
	}
	

}
