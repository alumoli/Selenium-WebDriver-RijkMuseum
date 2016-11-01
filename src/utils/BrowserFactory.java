package utils;

public class BrowserFactory {
	
	public static Browser getBrowser(BrowserName browserName){
		
		Driver d;
		
		switch(browserName)	{
		  case FIREFOX:
			 
			  d= new Driver("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\windows\\firefox\\geckodriver.exe");
			  return new Firefox(d);
			  
		  case CHROME:
			
			  d= new Driver("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\windows\\chrome\\chromedriver.exe");
			  return new Chrome(d);
			 
			              
          default:
        	  Driver d1= new Driver("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\windows\\firefox\\geckodriver.exe");
        	  return new Firefox(d1);
              
			
		}
		
		
	}

}
