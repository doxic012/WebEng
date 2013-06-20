package hbrs.webengineering.u5.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Uebung5SeleniumTest {
	private StringBuffer verificationErrors = new StringBuffer();
	private TrackerPageObject Page = null;
	private String baseUrl = "";
	@Before
	public void setUp() throws Exception {

		baseUrl = "http://localhost:8080/WE_Uebung5/";
		Page = new TrackerPageObject(new FirefoxDriver());
	}

	@Test
	public void testLogin() throws Exception {
		Page.login(baseUrl+"login.html", "secret!");
		assertEquals(Page.getUrl(), baseUrl+"tracker.html");
	}

	@After
	public void tearDown() throws Exception {
		Page.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}


}
