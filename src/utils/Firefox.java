package utils;

import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox extends Browser {
	
	FirefoxDriver firefoxDriver;
	//Driver d= new Driver("webdriver.gecko.driver", "\\Rijksmuseum\\drivers\\windows\\firefox\\geckodriver.exe");

	public Firefox(Driver d) {
		super("firefox", d);
		System.setProperty(d.getName(), d.getFileLocalPath());
		this.firefoxDriver = new FirefoxDriver();
	}

	public FirefoxDriver getFirefoxDriver() {
		return firefoxDriver;
	}

	public void setFirefoxDriver(FirefoxDriver firefoxDriver) {
		this.firefoxDriver = firefoxDriver;
	}

	

}
