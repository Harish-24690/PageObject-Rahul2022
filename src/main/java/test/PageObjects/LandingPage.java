package test.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver){
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="#userEmail")
	private WebElement emailTextBox;
	
	@FindBy(css="#userPassword")
	private WebElement passwordTextBox;
	
	@FindBy(css="#login")
	private WebElement submitButton;
	
	@FindBy(css="div[class*='flyInOut']")
	private WebElement invalidLoginErrorMessage;
	
	public ProductCatlogPage doLogin(String username,String password){
		emailTextBox.sendKeys(username);
		passwordTextBox.sendKeys(password);
		submitButton.click();
		return new ProductCatlogPage(driver);
		
	}
	public void goTO(){
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getInvalidLoginMessage(){
		
	return	invalidLoginErrorMessage.getText();
	}

}
