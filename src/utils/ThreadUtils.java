package utils;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ThreadUtils {

	static public void waitAndMessage(long millisec) {

		try {
			System.out.println("Waiting time of: " + millisec / 1000 + " sec");
			Thread.sleep(millisec);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static public void waitForDocumentReady(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, 1000);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}