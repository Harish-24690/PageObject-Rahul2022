package test.RahulshettyProject1;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import test.PageObjects.LandingPage;
import test.PageObjects.MyCartPage;
import test.PageObjects.OrderConfirmationPage;
import test.PageObjects.OrderPage;
import test.PageObjects.PaymentPage;
import test.PageObjects.ProductCatlogPage;
import test.TestComponent.BaseTest;



public class ShoppingPageTest extends BaseTest{
	
	//String name = "ZARA COAT 3";
	String country= "Argenti";
	
	@Test(dataProvider="getData" ,groups="purchase")
	public void submitOrderTest(HashMap<String ,String>input) throws IOException, InterruptedException{
						
//Page:1		
		ProductCatlogPage productCatlogPage =landingPage.doLogin(input.get("email"), input.get("password"));
//page:2		
	  List<WebElement> products=productCatlogPage.getProductList();
	   productCatlogPage.addProductToCart(input.get("product"));
	   MyCartPage cartPage =productCatlogPage.goToCartButton();
//page:3
		Boolean match=cartPage.getCartproducts(input.get("product"));
	   Assert.assertTrue(match);
	   PaymentPage  paymentPage =cartPage.gotoCheckOut(); 
//page :4	
	   paymentPage.gotoCountryTextBox(country);
	   paymentPage.selectCountry();
	   OrderConfirmationPage orderConfirmationPage =paymentPage.goToPlaceOrder();
//Page:5	   
	   String finalMessage=orderConfirmationPage.confirmationText();
	   Assert.assertEquals("THANKYOU FOR THE ORDER.", finalMessage);
	   
	}
	
	
	@Test(dependsOnMethods={"submitOrderTest"} , dataProvider="getData")
	public void orderConfirmation(HashMap<String,String> input) throws InterruptedException{
		
//Page:1		
	landingPage.doLogin(input.get("email"), input.get("password"));
	OrderPage orderPage=landingPage.gotoOrdersTab();
	Boolean match=orderPage.checkforOrderPlaced(input.get("product"));
	Assert.assertTrue(match);

			   
			
		
	}
	
	@DataProvider()
	public Object[][] getData() throws IOException{
		
		
		List<HashMap<String, String>> data = getJsonTOMap("C://Java program//RahulshettyProject1//src//test//java//test//Data//Purchase.json");
		return new Object[][]{ {data.get(0)} ,{data.get(1)}         };
	}
	
}
