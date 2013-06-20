package hbrs.webengineering.u4.config;

public final class HtmlConfig {
	public static final String AUTHENTICATED = "authenticated";
	
	public static final String PASSPHRASE = "login_password";
	public static final String HTML_LOGIN = "login.html";
	public static final String HTML_TRACKER = "tracker.html";

	public static final String REDIR_LOGIN = "/login";
	public static final String REDIR_TRACKER = "/tracker";
	
	public static String redirect(String redir) {
		return "."+redir+".html";
	}
}
