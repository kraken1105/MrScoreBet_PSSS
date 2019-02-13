package controllerMVC;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.crypto.provider.KeyGeneratorCore.HmacSHA2KG.SHA256;

import modelMVC.Utente;
import persistencyDAO.UtenteDAO;
import sun.security.provider.SHA;
import utils.exceptions.UsernameAlreadyRegisteredException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {super();}
	
    
    //** Consente di inserire nel sistema sia una nuova schedina che i risultati di una già inserita (in base a un parametro) **//
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email2");
		String username = request.getParameter("username");
		String psw = request.getParameter("psw2");
		
		// Convalida i dati della form anche server-side
		if(!validaEmail(email)) {
			session.setAttribute("info", "Indirizzo email non valido!");
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} else if(!validaPswUser(username)) {
			session.setAttribute("info", "L'username deve contenere almeno 6 caratteri e massimo 20!");
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} else if(!validaPswUser(psw)) {
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
			
		}
		
	}
	
	private boolean validaEmail(String email) {
		if (email == null) return false;

		Pattern p = Pattern.compile("([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(email);
		return m.matches();  
	}
	
	private boolean validaPswUser(String text) {
		if (text == null) return false;

		Pattern p = Pattern.compile(".{6,20}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(text);
		return m.matches();
	}

}

