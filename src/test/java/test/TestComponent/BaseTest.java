package test.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import test.PageObjects.LandingPage;
import test.Resources.DriverFactory;
import test.Resources.DriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	public static String screenshotPath;
	public static String screenshotName;
	private Boolean grid = false;

	public void initailizeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C://Java program//RahulshettyProject1//src//main//java//test//Resources//GlobalProperties.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		
		 if (System.getenv("ExceutionType") != null &&System.getenv("ExceutionType").equals("Grid")) {
		  grid = true; 
		  }
		 
		DriverFactory.setRemote(grid);
		if (DriverFactory.isRemote()) {
			DesiredCapabilities cap = null;
			if (browserName.contains("chrome")) {
				cap = new DesiredCapabilities();
				cap.setCapability(CapabilityType.BROWSER_NAME, "chrome");
				cap.setPlatform(Platform.ANY);

			} else if (browserName.equalsIgnoreCase("firefox")) {
				cap = new DesiredCapabilities();
				cap.setCapability(CapabilityType.BROWSER_NAME, "firefox");
				cap.setPlatform(Platform.ANY);
			}
			try {
				driver = new RemoteWebDriver(new URL("http://172.27.176.1:4444"), cap);
				// logInfo("Grid has been launched");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));

		} else if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C://Java program//RahulshettyProject1//executables//geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Edge")) {
			// WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		DriverManager.setWebDriver(driver);
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		//return DriverManager.getDriver();
	}

	public List<HashMap<String, String>> getJsonTOMap(String filepath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	/*
	 * public String getScreenshot(String testCaseName,WebDriver driver) throws
	 * IOException { TakesScreenshot ts = (TakesScreenshot)driver; File source =
	 * ts.getScreenshotAs(OutputType.FILE); File file = new
	 * File(System.getProperty("user.dir") + "//reports//" + testCaseName +
	 * ".png"); FileUtils.copyFile(source, file); return
	 * System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"; }
	 */

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
         FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));
		FileUtils.copyFile(scrFile, new File(".\\reports\\" + screenshotName));
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		//driver = initailizeDriver();
		initailizeDriver();
		landingPage = new LandingPage(DriverManager.getDriver());
		landingPage.goTO();
		return landingPage;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		DriverManager.getDriver().close();
	}
	/*@AfterTest(alwaysRun=true)
	public void quit(){
		if(driver!=null){
			DriverManager.getDriver().quit();
		}
	}*/

}
