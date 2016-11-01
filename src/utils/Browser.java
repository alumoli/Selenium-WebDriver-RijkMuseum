package utils;

public class Browser {
	
	 String name;
	 Driver phisycalDriver;
	
	 public Browser(String browserName, Driver driver){
		 this.name = browserName;
		 this.phisycalDriver = driver;
	 }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
