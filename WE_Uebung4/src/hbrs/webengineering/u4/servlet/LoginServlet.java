package hbrs.webengineering.u4.servlet;

import hbrs.webengineering.u4.general.HtmlConfig;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

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
		//super.doPost(req, resp);
		
		// Http-Session with login_password
		HttpSession session = req.getSession(true);
		
		// Bei neuer session authenticated auf false per default
		if(session.isNew()){
			session.setAttribute(HtmlConfig.AUTHENTICATED, new Boolean(false));
		}
			
//		boolean auth = (boolean) session.getAttribute(HtmlConfig.AUTHENTICATED);
		String pass = req.getParameter(HtmlConfig.PASSPHRASE);
	
		
		String uri = req.getRequestURI();
		System.out.println("Req uri:" +uri);

		// Passphrase stimmt oder bereits authentifiziert
		if(pass.equals(passPhrase)){
			session.setAttribute(HtmlConfig.AUTHENTICATED, new Boolean(true));
			resp.sendRedirect("/tracker.html");
		}
		else {
			session.setAttribute(HtmlConfig.AUTHENTICATED, new Boolean(false));			
			resp.sendRedirect("/login.html");
		}
		
	}
}
