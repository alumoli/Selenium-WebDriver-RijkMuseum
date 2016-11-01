package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ThreadUtils;

	public class CreateFirstCollectionPage {
		WebDriver webDriver;
		JavascriptExecutor js;

		By allPictures =By.xpath("//div[@id='infinite-scroll-page-results']/figure");
		By myFirstCollectionButton = By.cssSelector(".button-like .button-primary .button-big");
		By myProfileLink = By.xpath("/html/body/header/div/nav/div/nav/a");
		By myFirstCollectionLink = By.cssSelector("#infinite-scroll-page-results > figure > figcaption > h2 > a");
		By lightBoxCloseButton = By.cssSelector("body > div.lightbox-container.faded-in > section > div > button");
	
		List<WebElement> pictures = new ArrayList<WebElement>();

		public CreateFirstCollectionPage(WebDriver webDriver, JavascriptExecutor js) {
			this.webDriver = webDriver;
			this.js = js;
		}
		
		public void getAllPictures(){
			this.pictures =  this.webDriver.findElements(allPictures);
		}
		
		 public void clickOnCollection(){		  
			 js.executeScript("return document.querySelector('body > section.layout-wide.offset-parent > div.mini-page-wrapper.bg-lightest.div-bottom > section > div > ul > li:nth-child(2) > figure > a.set').click()");
		 }
		 
		public void addPictureToCollection(int i){
			clickOnHeart(i);		
			ThreadUtils.waitForDocumentReady(webDriver);
			selectMyFirstCollection();
		}
		
		public String getImageHrefInMuseumCollection(int i){
			 WebElement img = (WebElement)js.executeScript("return document.querySelector('#infinite-scroll-page-results > figure:nth-child("+i+") > div > a')");
			 return(img.getAttribute("href"));
		}
		
		public void clickOnHeart(int i){
			js.executeScript("return document.querySelector('#infinite-scroll-page-results > figure:nth-child("+i+") > div > a.button-like.button-icon.button-fav-no.button-primary.button-hover-lightest.corner-bottom-right.touch-faded-in.faded-out').click();");
		}
		
		public void selectMyFirstCollection(){
			js.executeScript("return document.querySelector('body > div.lightbox-container.faded-in > section > div > div.set-picker-viewer.offset-parent.bg-lighter > div > p > a').click();");
		}
		
		public void confirmAddingPictureToFirstCollection(){
			try{
				clickOnElement(myFirstCollectionButton);
			}
			catch(org.openqa.selenium.NoSuchElementException ex){
			}
		}
		
		public void clickOnMyProfile(){
			clickOnElement(myProfileLink);
		}
		
		public void clickOnMyFirstCollectionLink(){
			clickOnElement(myFirstCollectionLink);
		}
		
		public void goToMyFirstCollection(){
			try{
				clickOnMyProfile();
			}
			catch(org.openqa.selenium.WebDriverException ex){
				clickOnElement(lightBoxCloseButton);
				ThreadUtils.waitForDocumentReady(webDriver);
				clickOnMyProfile();
			}
			
			
			ThreadUtils.waitForDocumentReady(webDriver);
			clickOnMyFirstCollectionLink();
		}
		
		public long getNumberOfPicturesInMyFirstCollection(){	 
			return (long) (js.executeScript("return document.querySelectorAll('#rijksstudio-results > figure').length;"));
		}
		
		public String getImageHrefInMyFirstCollection(){
			 WebElement img = (WebElement)js.executeScript("return document.querySelector('#rijksstudio-results > figure:nth-child(1) > div > a')");
			 return(img.getAttribute("href"));
		}
		
		public List<WebElement> getPictures() {
			return pictures;
		}	
		
		public void clickOnElement(By element) {
			webDriver.findElement(element).click();
		}
}
