package test.RahulshettyProject1;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import test.PageObjects.MyCartPage;
import test.PageObjects.ProductCatlogPage;
import test.TestComponent.BaseTest;
import test.TestComponent.RetryListener;

public class ErrorValidationsTest extends BaseTest{
	
	
	
  @Test(groups={"ErrorValidation"},retryAnalyzer=RetryListener.class)	
	public void invalidLogin(){
		
		ProductCatlogPage productCatlogPage =landingPage.doLogin("testautomation0691@gmail.com", "Harish@1234");
		String message=landingPage.getInvalidLoginMessage();
		Assert.assertEquals("Incorrect email or password",message);
		
	}
  @Test
  public void productaddedtoCart() throws InterruptedException{
	  String name = "ZARA COAT 3";
	  ProductCatlogPage productCatlogPage =landingPage.doLogin("harishr24690@gmail.com", "Harish@1234");
	  
	//page:2		
		  List<WebElement> products=productCatlogPage.getProductList();
		   productCatlogPage.addProductToCart(name);
		   MyCartPage cartPage =productCatlogPage.goToCartButton();
	//page:3
			Boolean match=cartPage.getCartproducts(name);
		   Assert.assertTrue(match);
	  
  }

}
