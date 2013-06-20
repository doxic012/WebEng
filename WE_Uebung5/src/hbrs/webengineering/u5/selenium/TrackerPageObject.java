package hbrs.webengineering.u5.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TrackerPageObject {
	private WebDriver driver = null;

	public TrackerPageObject(WebDriver driver) {
		if (driver == null)
			this.driver = new FirefoxDriver();
		else
			this.driver = driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void login(String url, String password) {
		driver.get(url);
		driver.findElement(By.name("login_password")).clear();
		driver.findElement(By.name("login_password")).sendKeys(password);
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();		
	}
	
	public void quit () {
		driver.quit();
	}
	
	public String getUrl() {
		return driver.getCurrentUrl();
	}
	
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText(boolean acceptNextAlert) {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
