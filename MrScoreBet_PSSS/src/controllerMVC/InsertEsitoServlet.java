package controllerMVC;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import modelMVC.*;
import utils.exceptions.EsitoAlreadyInsertedException;

@WebServlet("/admin/InsertEsitoServlet")
public class InsertEsitoServlet extends HttpServlet {
	private static final long serialVersionUID = 4153925506563936612L;

	public InsertEsitoServlet() {super();}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// Convalida i dati della form anche server-side
		boolean datiOk = true;		
		
		// Prelievo delle informazioni sulla giornata				
		if(request.getParameter("numGiornata")==null) datiOk = false;
		Integer giornata = Integer.parseInt(request.getParameter("numGiornata"));
		
		// Prelievo degli esiti dei match
		ArrayList<String> gameList = new ArrayList<String>();
		for(int i=1; i<11; i++) {
			String temp = new String(request.getParameter("match"+i));
			if(temp.equals("")) {datiOk = false; break;}
			gameList.add((String) request.getParameter("match"+i));			
		}
		
		if(!datiOk) {
			session.setAttribute("info", "Si � verificato un errore! Tutti i campi sono obbligatori");
			response.sendRedirect(request.getContextPath()+"/admin/insertEsito.jsp");
		} else {
			
			// Controlli superati con successo, viene contattato il coordinatore del Model
			ICoordinatorFacade coord = new CoordinatorFacade();
			try {
				boolean esito = coord.inserisciEsito(giornata, gameList);
				
				if (esito) {
					session.setAttribute("info", "Esiti inseriti correttamente!");
					response.sendRedirect(request.getContextPath()+"/user.jsp");
				} else {
					session.setAttribute("info", "Si � verificato un errore. Riprova pi� tardi.");
					response.sendRedirect(request.getContextPath()+"/user.jsp");
				}
				
			} catch(EsitoAlreadyInsertedException e) {
				session.setAttribute("info", e.getMessage());
				response.sendRedirect(request.getContextPath()+"/user.jsp");
			}			
			
		}
	}

}
