package testCases;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import pageObjects.CreateFirstCollectionPage;
import pageObjects.LoginPage;
import utils.BrowserFactory;
import utils.BrowserName;
import utils.Chrome;
import utils.MathUtils;
import utils.ThreadUtils;

public class VerifyCreateFirstCollection {
	
	 public static final String URL= "http://www.rijksmuseum.nl";
	 
	 CreateFirstCollectionPage exploreRijksStudioPage;
	 LoginPage loginPage;
	 By loginLink= By.cssSelector("body > header > div > nav > div > nav > button");
	 By diveIntoCollectionLink =By.xpath("/html/body/section[1]/nav/section[2]/button/span/span");	
	 By exploreRijkStudio = By.xpath("/html/body/section[1]/nav/section[2]/div/ul/li[1]/a");
	 By numberOPicturesInCollection = By.cssSelector("#infinite-scroll-page-results > figure > figcaption > p > span:nth-child(1))");
	                                               	
	 WebDriver wd;
	 JavascriptExecutor js;
	 
	 @Before
	 public void SetUp() {
			Chrome chrome = (Chrome)BrowserFactory.getBrowser(BrowserName.CHROME);
			wd = chrome.getChromeDriver();
			wd.get(URL);
			js = (JavascriptExecutor) wd;
			loginPage = new LoginPage(wd ,js);
	}

	 @Test
	 public void verifyCreateFirstCollection(){
		 try {
			 wd.manage().window().maximize();
			 doLogin(loginPage);
			 exploreRijksStudioPage = new CreateFirstCollectionPage(wd, js); 
			 ThreadUtils.waitForDocumentReady(wd);
			 exploreRijksStudioPage.goToMyFirstCollection();
			 ThreadUtils.waitForDocumentReady(wd);
			 long initialNumberOfPictures = exploreRijksStudioPage.getNumberOfPicturesInMyFirstCollection();
			 System.out.println("Initial number of pictures in my first collection: " + initialNumberOfPictures);
			 wd.get(URL);
			 ThreadUtils.waitForDocumentReady(wd);
			 exploreRijksStudioPage.clickOnElement(diveIntoCollectionLink);
			 ThreadUtils.waitForDocumentReady(wd);
			 exploreRijksStudioPage.clickOnElement(exploreRijkStudio);	
			 ThreadUtils.waitForDocumentReady(wd);
			 exploreRijksStudioPage.clickOnCollection();
			 ThreadUtils.waitForDocumentReady(wd);
			 exploreRijksStudioPage.getAllPictures();
			 int picturesNumber = exploreRijksStudioPage.getPictures().size();
			 System.out.println("Total of pictures in museum collection: "+ picturesNumber);
			 int selectedPicture = MathUtils.generateRandom(1, picturesNumber);
			 String hrefOfSelectedPictureInMuseumCollection =  exploreRijksStudioPage.getImageHrefInMuseumCollection(selectedPicture);
			 System.out.println("Picture to choose: "+ selectedPicture +" href: " + hrefOfSelectedPictureInMuseumCollection);
			 ThreadUtils.waitForDocumentReady(wd);
			 exploreRijksStudioPage.addPictureToCollection(selectedPicture);
			 ThreadUtils.waitForDocumentReady(wd);
			 exploreRijksStudioPage.confirmAddingPictureToFirstCollection();
			 ThreadUtils.waitForDocumentReady(wd);
			 exploreRijksStudioPage.goToMyFirstCollection();
			 ThreadUtils.waitForDocumentReady(wd);
			 long finalNumberOfPictures = exploreRijksStudioPage.getNumberOfPicturesInMyFirstCollection();
			 String hrefOfSelectedPictureInMyFirstCollection =  exploreRijksStudioPage.getImageHrefInMyFirstCollection();
			 System.out.println("Final number of pictures in my first collection: " + finalNumberOfPictures);
			 System.out.println("Href of first: " + hrefOfSelectedPictureInMyFirstCollection);
			 verifyPictureWasAdded(initialNumberOfPictures, finalNumberOfPictures, hrefOfSelectedPictureInMuseumCollection, hrefOfSelectedPictureInMyFirstCollection);
		 }
		finally{
			wd.quit();
		}
	 }
	 
	 public void verifyPictureWasAdded(long initialNumberOfPictures, long finalNumberOfPictures, String href1, String href2){
			Assert.assertEquals(initialNumberOfPictures+1, finalNumberOfPictures);
			Assert.assertEquals(href1, href2);
		}
	 
	 public void doLogin(LoginPage loginPage){
			loginPage.clickOnElement(loginLink);	
			ThreadUtils.waitForDocumentReady(wd);
			loginPage.typeEmail();
			loginPage.typePassword();
			loginPage.clickOnLoginButton();
	 }
}
