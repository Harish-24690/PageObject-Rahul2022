package test.Resources;

public class DriverFactory {
	
	private static boolean isRemote;
	
	

	public static boolean isRemote() {
		return isRemote;
	}

	public static void setRemote(boolean isRemote) {
		DriverFactory.isRemote = isRemote;
	}

}
