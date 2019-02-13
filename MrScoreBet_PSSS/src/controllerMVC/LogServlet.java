package controllerMVC;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import modelMVC.Utente;
import persistencyDAO.UtenteDAO;
import utils.Utils;
import utils.exceptions.UserNotFoundException;

@WebServlet("/LogServlet")
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = -3405778085547935163L;

	public LogServlet() {super();}
	
    //** Effettua il Logout dell'utente in sessione, se presente **//
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	
    	if(request.getParameter("utente")!=null) {
    		session.removeAttribute("utente");
    		session.setAttribute("info", "Logout effettuato!");
    	}
    	
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
			
			try {
				Utente utente = UtenteDAO.read(username);
				
				session.setAttribute("utente", utente);
				response.sendRedirect(request.getContextPath()+"/user.jsp");
				
			} catch(UserNotFoundException e) {
				session.setAttribute("info", "Email");
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
			catch(SQLException e) {e.printStackTrace();}			
			
		}
		
	}

}


