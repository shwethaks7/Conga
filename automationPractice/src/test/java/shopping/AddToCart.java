package shopping;

import java.io.IOException;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObj.LoginPage;
import utilities.Base;

public class AddToCart extends Base {
	LoginPage p1;
	@BeforeSuite
	 public void beforeSuite() throws IOException {
		 
		//Set the browser 
			driverObj=  initializedriver();
	 }
	
	@Test
	public void addItems() {
		
		driverObj.get(getLoginUrl());
		
		driverObj.manage().window().maximize();
		
		p1 = new LoginPage(driverObj);
		
		wait.until(ExpectedConditions.elementToBeClickable(p1.getsignIn()));
		p1.getsignIn().click();
		
		wait.until(ExpectedConditions.elementToBeClickable(p1.getemail()));
		p1.getemail().sendKeys(getUserId());
		p1.getpassword().sendKeys(getPaswd());
		p1.getsignInwithCred().click();
		
		wait.until(ExpectedConditions.elementToBeClickable(p1.getlogout()));
		p1.getTShirtTab().click();
		
		wait.until(ExpectedConditions.visibilityOf(p1.getcatalog()));
		
		if(p1.getItem().getText().equals("Faded Short Sleeve T-shirts")) {
			p1.getItem().click();
			
			wait.until(ExpectedConditions.elementToBeClickable(p1.getAddToCart()));
			p1.getAddToCart().click();
			
			wait.until(ExpectedConditions.elementToBeClickable(p1.getcheckOut()));
			
			
			
			Assert.assertEquals(p1.getcheckoutMessage().getText(), "Product successfully added to your shopping cart");
			
			String quantity = p1.getquantity().getText();
			String price = p1.getprice().getText();
			System.out.println(price);
			
			price= price.replaceAll("[$]", "");
			
			int a= Integer.parseInt(quantity);
			double b= Double.parseDouble(price);
			
			
			System.out.println(p1.getprodName().getText());
			System.out.println(p1.getprodDesc().getText());
			System.out.println("Quantity "+p1.getquantity().getText());
			System.out.println("Total $"+ (a*b));
			
			p1.getcheckOut().click();
			
		}
			

	}	
	@AfterClass
	public void afterClass() {
		//p1.getlogout().click();
	}
	@AfterSuite
	 public void afterSuite() {
		 //driverObj.close();
	 }

}
