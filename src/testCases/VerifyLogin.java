package testCases;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import pageObjects.LoginPage;
import utils.BrowserFactory;
import utils.BrowserName;
import utils.Chrome;
import utils.ThreadUtils;

public class VerifyLogin {

	 public static final String URL= "http://www.rijksmuseum.nl";
	 By loginLink= By.cssSelector("body > header > div > nav > div > nav > button");
	 LoginPage loginPage;
	 WebDriver wd;
	 JavascriptExecutor js;
	 
	 @Before
	 public void SetUp() {
		Chrome chrome = (Chrome)BrowserFactory.getBrowser(BrowserName.CHROME);
		wd = chrome.getChromeDriver();
	}
	 
//	 @Before
//	 public void SetUp() {
//		Firefox fx = (Firefox)BrowserFactory.getBrowser(BrowserName.FIREFOX);
//		wd = fx.getFirefoxDriver();
//	}
	
	@Test
	public void verifyLogin() {
		try{
			wd.get(URL);
			js = (JavascriptExecutor) wd;
			loginPage = new LoginPage(wd, js);
			wd.manage().window().maximize();
			ThreadUtils.waitForDocumentReady(wd);
			loginPage.clickOnElement(loginLink);	
			ThreadUtils.waitForDocumentReady(wd);
			loginPage.typeEmail();
			loginPage.typePassword();
			loginPage.clickOnLoginButton();
			verifyLoggedIn();
		}
		finally{
			wd.quit();
		}
	}
	
	public void verifyLoggedIn(){
		Assert.assertEquals(true, isUserLoggedIn());
	}
	
	public boolean isUserLoggedIn(){
		boolean isUserLoggedIn = true;
		
		try{
			wd.findElement(loginLink);
		}
		catch(org.openqa.selenium.NoSuchElementException ex){
			isUserLoggedIn = false;
		}
		return isUserLoggedIn;
	}
}