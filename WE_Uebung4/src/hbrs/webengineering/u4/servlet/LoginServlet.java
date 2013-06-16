package hbrs.webengineering.u4.servlet;

import hbrs.webengineering.u4.config.HtmlConfig;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	
	private String passPhrase = "secret!";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		// Http-Session with login_password
		HttpSession session = req.getSession(true);
		
		// Bei neuer session authenticated auf false per default
		if(session.isNew()){
			session.setAttribute(HtmlConfig.AUTHENTICATED, new Boolean(false));
		}
			
		String pass = req.getParameter(HtmlConfig.PASSPHRASE);
		
		// Passphrase stimmt oder bereits authentifiziert
		if(pass.equals(passPhrase)){
			session.setAttribute(HtmlConfig.AUTHENTICATED, new Boolean(true));
			resp.sendRedirect("./"+HtmlConfig.HTML_TRACKER);
		}
		else {
			session.setAttribute(HtmlConfig.AUTHENTICATED, new Boolean(false));			
			resp.sendRedirect("./"+HtmlConfig.HTML_LOGIN);
		}
		
	}
}
