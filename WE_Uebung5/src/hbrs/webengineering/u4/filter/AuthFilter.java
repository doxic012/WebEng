package hbrs.webengineering.u4.filter;

import hbrs.webengineering.u4.config.HtmlConfig;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		HttpServletResponse httpResponse = (HttpServletResponse) resp;
		HttpSession session = httpRequest.getSession(false);

		String UriFilter = httpRequest.getRequestURI();
		
		
		// Session existiert bereits und User ist eingeloggt
		if (UriFilter.contains(HtmlConfig.REDIR_LOGIN) || (session != null && (boolean) session.getAttribute(HtmlConfig.AUTHENTICATED))) {
			chain.doFilter(req, resp);
		} else {
			// redirect nach login.html
			httpResponse.sendRedirect(HtmlConfig.redirect(HtmlConfig.REDIR_LOGIN));
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
