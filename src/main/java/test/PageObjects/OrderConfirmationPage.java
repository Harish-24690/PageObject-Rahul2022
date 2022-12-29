package test.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class OrderConfirmationPage extends AbstractComponent{
	WebDriver driver;

	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		}
	
	@FindBy(css="h1.hero-primary")
	private WebElement confirmationMessage;
	
  public String confirmationText(){
	
	String message=confirmationMessage.getText();
	return message;
	
}

}



//String finalMessage =driver.findElement(By.cssSelector("h1.hero-primary")).getText();
//Assert.assertEquals("THANKYOU FOR THE ORDER.", finalMessage);