package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	public static final String EMAIL = "test39862@gmail.com";
	public static final String PASSWORD = "P@ssw0rd";

	WebDriver webDriver;
	JavascriptExecutor js;

	By email = By.name("email");
	By wachtwoord = By.cssSelector("#wachtwoord");
	By loginButton = By.xpath("/html/body/div[5]/section/section[2]/div[1]/div/form/fieldset/ol/li[4]/input");
	
	public LoginPage(WebDriver webDriver, JavascriptExecutor js) {
		this.webDriver = webDriver;
		this.js = js;
	}

	public void typeEmail() {
		webDriver.findElement(email).sendKeys(EMAIL);
	}

	public void typePassword() {
		webDriver.findElement(wachtwoord).sendKeys(PASSWORD);
	}
	
	public void clickOnLoginButton(){
		clickOnElement(loginButton);
	}
	
	public void clickOnElement(By element) {
		webDriver.findElement(element).click();
	}
	
}
