package utils;

import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome extends Browser {
	
	ChromeDriver chromeDriver;
	
	public Chrome(Driver d) {
		super("chrome", d);
		System.setProperty(d.getName(), d.getFileLocalPath());
		this.chromeDriver = new ChromeDriver();
	}

	public ChromeDriver getChromeDriver() {
		return chromeDriver;
	}

	public void setChromeDriver(ChromeDriver chromeDriver) {
		this.chromeDriver = chromeDriver;
	}


}
