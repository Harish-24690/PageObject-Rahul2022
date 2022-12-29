package test.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductCatlogPage extends AbstractComponent {
	
	WebDriver driver;

	public ProductCatlogPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	private List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	private WebElement spinner;
	
	
	
	By productBy= By.cssSelector(".mb-3");
	By addTOCartButton =By.cssSelector("button:last-of-type");
	By toastContainer= By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList(){
		
		waitForElementToAppear(productBy);
		return products;
		
	}
	public WebElement getProductByName(String productname){
		
		WebElement prod=getProductList().stream()
				.filter(product->product.findElement(By.cssSelector("b")).getText().contains(productname))
				.findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productname) throws InterruptedException{
		WebElement prod=getProductByName(productname);
		prod.findElement(addTOCartButton).click();
		//waitForElementToAppear(toastContainer);
		//waitForElementToDisapper(spinner) ;
		Thread.sleep(4000);
		
	}
	
	
	
	
	
	
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	//List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
	//WebElement prod=products.stream()
			//.filter(product->product.findElement(By.cssSelector("b")).getText().contains(name))
			//.findFirst().orElse(null);
	//prod.findElement(By.cssSelector("button:last-of-type")).click();
	//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	//driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
}
