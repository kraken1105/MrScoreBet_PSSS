package controllerMVC;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import modelMVC.Utente;
import persistencyDAO.UtenteDAO;
import utils.Utils;
import utils.exceptions.UsernameAlreadyRegisteredException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 3373267284331709872L;

	public RegisterServlet() {super();}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email2");
		String username = request.getParameter("username2");
		String psw = request.getParameter("psw2");
		
		// Convalida i dati della form anche server-side
		if(!Utils.validaEmail(email)) {
			session.setAttribute("info", "Indirizzo email non valido!");
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} else if(!Utils.validaPswUser(username)) {
			session.setAttribute("info", "L'username deve contenere almeno 6 caratteri e massimo 20!");
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} else if(!Utils.validaPswUser(psw)) {
			session.setAttribute("info", "La password deve contenere almeno 6 caratteri e massimo 20!");
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} else {
			
			try {
				String sha256hex_psw = org.apache.commons.codec.digest.DigestUtils.sha256Hex(psw);
				
				Utente utente = new Utente(username,email,sha256hex_psw,"utente",0,null,null);
				UtenteDAO.create(utente);
				
				session.setAttribute("info", "Registrazione completata! Effettua il login");
				response.sendRedirect(request.getContextPath()+"/index.jsp");
				
			}			
			catch(UsernameAlreadyRegisteredException e) {
				session.setAttribute("info", "Username già presente nel sistema! Scegli un username differente");
				response.sendRedirect(request.getContextPath()+"/index.jsp");	
			} 
			catch(SQLException e) {e.printStackTrace();}
			
		}
		
	}

}

