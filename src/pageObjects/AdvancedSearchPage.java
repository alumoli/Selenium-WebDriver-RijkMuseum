package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class AdvancedSearchPage {
	
	public static final String NAME = "Utagawa Kuniyoshi";
	public static final String TITLE = "De CHofu Tama";
	public static final String START_DATE= "1847";
	public static final String END_DATE= "1850";
	
	WebDriver webDriver;
	JavascriptExecutor js;
	
	By name = By.id("token-input-QueryDescriptor_AdvancedSearchOptions_ArtistCriteria_InvolvedMakerName");
	By title = By.id("QueryDescriptor_AdvancedSearchOptions_ObjectCriteria_Title");
	By onlyWithImageBox= By.id("QueryDescriptor_AdvancedSearchOptions_ObjectCriteria_ShowImage");
	By startDate= By.id("QueryDescriptor_AdvancedSearchOptions_ObjectCriteria_DatingPeriod_StartDate");
	By endDate= By.id("QueryDescriptor_AdvancedSearchOptions_ObjectCriteria_DatingPeriod_EndDate");
	By searchButton= By.xpath("/html/body/section[2]/form/div[2]/section[5]/div/fieldset/input");
	
	public AdvancedSearchPage(WebDriver webDriver, JavascriptExecutor js) {
		this.webDriver = webDriver;
		this.js = js;
	}

	public void typeName() {
		
		webDriver.findElement(name).sendKeys(NAME);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		webDriver.findElement(name).sendKeys(Keys.ENTER);
	}

	public void typeTitle() {
		webDriver.findElement(title).sendKeys(TITLE);
	}

	public void checkOnlyWithImageBoxRadioButton() {
		clickOnElement(onlyWithImageBox);
	}
	
	public void typeStartDateMade() {
		webDriver.findElement(startDate).sendKeys(START_DATE);
	}
	
	public void typeEndDateMade() {
		webDriver.findElement(endDate).sendKeys(END_DATE);
	}
	
	public void clickOnSearchButton() {
		clickOnElement(searchButton);
	}
	
	public void clickOnElement(By element) {
		webDriver.findElement(element).click();
	}
	
}
