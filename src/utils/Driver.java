package utils;

public class Driver {
	
	 String name;
	 String fileLocalPath;
	 
	 public Driver(String driverName, String driverFileLocalPath) {
		this.name = driverName;
		this.fileLocalPath = driverFileLocalPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileLocalPath() {
		return fileLocalPath;
	}

	public void setFileLocalPath(String fileLocalPath) {
		this.fileLocalPath = fileLocalPath;
	}

	
}
