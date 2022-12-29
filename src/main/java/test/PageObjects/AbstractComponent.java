package test.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	             
	@FindBy(css="button[routerlink='/dashboard/cart']")
	private WebElement cartButton;
	
	@FindBy(css="button[routerlink$='/dashboard/myorders']")
	private WebElement orderbutton;
	
	
	public void waitForElementToAppear(By findBy ){
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));	
	}
	
	public void waitForElementToDisapper(WebElement ele){
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5000));
	wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void scrollBy(){
		JavascriptExecutor js= (JavascriptExecutor)driver;
		 js.executeScript("window.scrollBy(0,500)");
	}
	public MyCartPage goToCartButton(){
		cartButton.click();
		return new MyCartPage(driver);
	}
	public OrderPage gotoOrdersTab(){
		orderbutton.click();
		return new OrderPage(driver);
	}
	
}


