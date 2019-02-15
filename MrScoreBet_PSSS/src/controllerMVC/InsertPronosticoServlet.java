package controllerMVC;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import modelMVC.*;
import utils.exceptions.SchedinaNotAvailableException;

@WebServlet("/utente/InsertPronosticoServlet")
public class InsertPronosticoServlet extends HttpServlet {
	private static final long serialVersionUID = 6715754017525255251L;

	public InsertPronosticoServlet() {super();}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Utente utente = (Utente) session.getAttribute("utente");
		
		// Convalida i dati della form anche server-side
		boolean datiOk = true;		
		ArrayList<String> gameList = new ArrayList<String>();
		for(int i=1; i<11; i++) {
			String temp = new String(request.getParameter("match"+i));
			if(temp.equals("")) {datiOk = false; break;}
			gameList.add((String) request.getParameter("match"+i));			
		}
				
		if(!datiOk) {
			session.setAttribute("info", "Si è verificato un errore! Tutti i pronostici sono obbligatori");
			response.sendRedirect(request.getContextPath()+"/utente/placeMyBet.jsp");
		} else {
			
			// Controlli superati con successo, viene contattato il coordinatore del Model
			ICoordinatorFacade coord = new CoordinatorFacade();
			try {
				boolean esito = coord.inserisciPronostico(utente, gameList);
				
				if (esito) {
					session.setAttribute("info", "Pronostici inseriti correttamente!");
					response.sendRedirect(request.getContextPath()+"/user.jsp");
				} else {
					session.setAttribute("info", "Si è verificato un errore. Riprova più tardi.");
					response.sendRedirect(request.getContextPath()+"/user.jsp");
				}
				
			} catch(SchedinaNotAvailableException e) {
				session.setAttribute("info", e.getMessage());
				response.sendRedirect(request.getContextPath()+"/user.jsp");
			}			
			
		}
	}

}
