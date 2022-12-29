package test.RahulshettyProject1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;



public class RoughTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		String name = "ZARA COAT 3";
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5000));
//Page:1		
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("testautomation0691@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Harish@123");
		driver.findElement(By.cssSelector("#login")).click();
		
//page:2		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream()
				.filter(product->product.findElement(By.cssSelector("b")).getText().contains(name))
				.findFirst().orElse(null);
		System.out.println(prod.getText());
		prod.findElement(By.cssSelector("button:last-of-type")).click();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
//page:3
		
		List<WebElement>cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(name));
		Assert.assertTrue(match);
	    driver.findElement(By.cssSelector("button[type='button']:nth-child(1)")).click();
	    
	   driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Argenti");
	   
	   List<WebElement> country= driver.findElements(By.cssSelector("button[type='button']"));
	   for(WebElement option:country){
		   if(option.getText().contains("Argentina")){
			   option.click();   
		   }
		   
	   }
	   JavascriptExecutor js= (JavascriptExecutor)driver;
	   js.executeScript("window.scrollBy(0,500)");
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit"))).click();
//Page:4	   
	   
	   //driver.findElement(By.cssSelector(".action__submit")).click();
	   String finalMessage =driver.findElement(By.cssSelector("h1.hero-primary")).getText();
	   Assert.assertEquals("THANKYOU FOR THE ORDER.", finalMessage);
	   
	}
	
}
