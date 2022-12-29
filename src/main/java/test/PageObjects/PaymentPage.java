package test.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaymentPage extends AbstractComponent{
	
	WebDriver driver;
	
	public PaymentPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	By placeOrder = By.cssSelector(".action__submit");
	
	@FindBy(css="input[placeholder='Select Country']")
	private WebElement countryTextBox;
	
	@FindBy(css="button[type='button']")
	private List<WebElement> countrydropdownList;
	
	@FindBy(css=".action__submit")
	private WebElement placeOrderbutton;
	
	public void gotoCountryTextBox(String country){
		countryTextBox.sendKeys(country);
	}
	
	public void selectCountry(){
		for(WebElement option:countrydropdownList){
			   if(option.getText().contains("Argentina")){
				   option.click();   
			   }
			   }
	}
	
	public OrderConfirmationPage goToPlaceOrder(){
		
		scrollBy();
		waitForElementToAppear(placeOrder);
		placeOrderbutton.click();
		return new OrderConfirmationPage(driver);
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit"))).click();
	}

}






//driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Argenti");
/*List<WebElement> country= driver.findElements(By.cssSelector("button[type='button']"));
for(WebElement option:country){
	   if(option.getText().contains("Argentina")){
		   option.click();   
	   }
	   
}*/

//JavascriptExecutor js= (JavascriptExecutor)driver;
//js.executeScript("window.scrollBy(0,500)");
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit"))).click();