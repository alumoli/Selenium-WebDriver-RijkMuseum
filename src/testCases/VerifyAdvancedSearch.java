package testCases;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObjects.AdvancedSearchPage;
import utils.BrowserFactory;
import utils.BrowserName;
import utils.Chrome;
import utils.ThreadUtils;

public class VerifyAdvancedSearch {

	public static final String URL = "http://www.rijksmuseum.nl";
	AdvancedSearchPage advancedSearchPage;
	By diveIntoCollectionLink = By.xpath("/html/body/section[1]/nav/section[2]/button/span/span");
	By searchInRijkStudioLink = By.xpath("/html/body/section[1]/nav/section[2]/div/ul/li[2]/a");
	By advancedSearchLink = By.cssSelector("body > section.layout-wide > div:nth-child(2) > section > div > div.item.tablet-p-hide > ul > li:nth-child(2) > a");
	
	By allFigures = By.cssSelector("#infinite-scroll-page-results > figure");
	
	
	WebDriver wd;
	JavascriptExecutor js;

	@Before
	public void SetUp() {
		Chrome chrome = (Chrome) BrowserFactory.getBrowser(BrowserName.CHROME);
		wd = chrome.getChromeDriver();
	}

	@Test
	public void verifyAdvancedSearch() {

		try {
			wd.get(URL);
			js = (JavascriptExecutor) wd;
			advancedSearchPage = new AdvancedSearchPage(wd, js);
			wd.manage().window().maximize();
			ThreadUtils.waitForDocumentReady(wd);
			advancedSearchPage.clickOnElement(diveIntoCollectionLink);
			ThreadUtils.waitForDocumentReady(wd);
			advancedSearchPage.clickOnElement(searchInRijkStudioLink);
			ThreadUtils.waitForDocumentReady(wd);
			gotoAdvancedSearch();
			ThreadUtils.waitForDocumentReady(wd);
			advancedSearchPage.typeName();
			advancedSearchPage.typeTitle();
			ThreadUtils.waitForDocumentReady(wd);
			checkBox();
			advancedSearchPage.typeStartDateMade();
			advancedSearchPage.typeEndDateMade();
			ThreadUtils.waitForDocumentReady(wd);
			search();
			ThreadUtils.waitForDocumentReady(wd);
			checkErrorInPage();
			verifyNumberOfPicsFounded();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			wd.quit();
		}
	}

	public void verifyNumberOfPicsFounded() throws InterruptedException {
		Assert.assertEquals(3, getResultedNumberOfPictures());
	}

	@SuppressWarnings("unchecked")
	public long getResultedNumberOfPictures() {

		long n = 0;
		
		List<WebElement> list = new ArrayList<WebElement>();

		for (int i = 0; i < 1000; i++) {

			list = (List<WebElement>) js.executeScript("return document.querySelectorAll('#infinite-scroll-page-results > figure')");
			
			if (list.size() > 0) {
				n = (long) (js.executeScript("return document.querySelectorAll('#infinite-scroll-page-results > figure').length;"));
				break;
			}
			else {
					ThreadUtils.waitAndMessage(1000);
			}
		}

		return n;
	}

	public void checkBox() {
		js.executeScript("return document.getElementById('QueryDescriptor_AdvancedSearchOptions_ObjectCriteria_ShowImage').checked = true;");
	}

	public void search(){
		try {
				advancedSearchPage.clickOnSearchButton();
	
			} catch (org.openqa.selenium.WebDriverException e) {
				 js.executeScript("return document.getElementsByClassName('button-primary button-big button-bold button-combo-start button-search')[0].click()");
		}
	}

	public void gotoAdvancedSearch() {
		try {
			advancedSearchPage.clickOnElement(advancedSearchLink);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			js.executeScript("return document.querySelector('body > section.layout-wide > div:nth-child(2) > section > div > div.item.tablet-p-hide > ul > li:nth-child(2) > a').click()");		
		}
	}
	
	
	public void checkErrorInPage(){
		if("https://www.rijksmuseum.nl/nl/search/advanced/redirect?p=1&ps=12&st=OBJECTS".equalsIgnoreCase(wd.getCurrentUrl())){
			System.out.println("ERROR ON PAGE");
			wd.quit();
		}
		
		
	}
	
	
}
