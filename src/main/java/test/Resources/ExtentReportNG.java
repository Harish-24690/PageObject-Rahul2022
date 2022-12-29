package test.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static  ExtentReports getReporter(){
		
		String filepath =System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filepath);
		reporter.config().setReportName("WebAutomation Test");
		reporter.config().setDocumentTitle("Automation Tests");
		
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Harish.R");
		return extent;
	}

}
