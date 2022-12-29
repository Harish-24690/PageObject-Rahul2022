package test.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyCartPage extends AbstractComponent{
	
	WebDriver driver;

	public MyCartPage(WebDriver driver ){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
		}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartList;
	
	@FindBy(css="button[type='button']:nth-child(1)")
	private WebElement checkOutButton;
	
	
	public Boolean getCartproducts(String productName){
		Boolean match=cartList.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public PaymentPage gotoCheckOut(){
		checkOutButton.click();
		return new PaymentPage(driver);
	}
	

}

//List<WebElement>cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
//Boolean match=cartProducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(name));
// driver.findElement(By.cssSelector("button[type='button']:nth-child(1)")).click();