package controllerMVC;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import modelMVC.*;
import utils.Utils;
import utils.exceptions.UserNotFoundException;

@WebServlet("/LogServlet")
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = -3405778085547935163L;

	public LogServlet() {super();}
	
    //** Effettua il Logout dell'utente in sessione, se presente **//
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session =  request.getSession();
    	
    	session.removeAttribute("utente");
    	session.setAttribute("info", "Logout effettuato!");
    	
    	response.sendRedirect(request.getContextPath());
	}
	
    
    
    //** Effettua il login dell'utente, verificando la correttezza dei campi **//
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username1");
		String psw = request.getParameter("psw1");
		
		// Convalida i dati della form anche server-side
		if(!Utils.validaPswUser(username)) {
			session.setAttribute("info", "L'username deve contenere almeno 6 caratteri e massimo 20!");
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} else if(!Utils.validaPswUser(psw)) {
			session.setAttribute("info", "La password deve contenere almeno 6 caratteri e massimo 20!");
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} else {
			
			// Controlli superati con successo, viene contattato il coordinatore del Model
			ICoordinatorFacade coord = new CoordinatorFacade();
			try {
				String sha256hex_psw = org.apache.commons.codec.digest.DigestUtils.sha256Hex(psw);
				Utente utente = coord.login(username, sha256hex_psw);
				
				if(utente!=null) {
					session.setAttribute("utente", utente);
					response.sendRedirect(request.getContextPath()+"/user.jsp");
				}
				else {
					session.setAttribute("info", "Si è verificato un errore. Riprova più tardi.");
					response.sendRedirect(request.getContextPath()+"/index.jsp");
				}
				
			} catch(UserNotFoundException e) {
				session.setAttribute("info", e.getMessage());
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}			
			
		}
		
	}

}


