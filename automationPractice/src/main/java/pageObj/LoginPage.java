package pageObj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	
	
	WebDriver driverObj;
	public LoginPage(WebDriver obj)
	{
		driverObj= obj;
		PageFactory.initElements(driverObj, this);
	}
	
	@FindBy(css = ".login")
	WebElement signIn;
	
	@FindBy(css="#email")
	WebElement email;
	
	@FindBy(css="#passwd")
	WebElement paswd;	
	
	@FindBy(css="#SubmitLogin")
	WebElement signInwithCredential;
	
	@FindBy(css=".logout")
	WebElement logout;
	
	
	@FindBy(css="#block_top_menu > ul > li:nth-child(3)")
	WebElement tShirtTab;
	
	@FindBy(css="#layered_block_left > p")
	WebElement catalog;
	
	@FindBy(css="#center_column > ul > li > div > div.right-block > h5 > a")
	WebElement getItem;
	
	@FindBy(css="#add_to_cart > button")
	WebElement addToCart;
	
	@FindBy(css="a[title='Proceed to checkout'] > span")
	WebElement checkOut;
	
	@FindBy(css="#layer_cart > div.clearfix > div > h2")
	WebElement checkoutMessage;
	
	
	@FindBy(css="#layer_cart_product_title")
	WebElement prodName;
	@FindBy(css="#layer_cart_product_attributes")
	WebElement prodDesc;
	@FindBy(css="#layer_cart_product_quantity")
	WebElement quantity;
	@FindBy(css="#layer_cart_product_price")
	WebElement price;
	
	public WebElement getsignIn(){
		return signIn;
	}
	public WebElement getemail(){
		return email;
	}
	public WebElement getpassword(){
		return paswd;
	}
	public WebElement getsignInwithCred(){
		return signInwithCredential;
	}
	public WebElement getlogout(){
		return logout;
	}
	public WebElement getTShirtTab(){
		return tShirtTab;
	}
	public WebElement getcatalog(){
		return catalog;
	}
	public WebElement getItem(){
		return getItem;
	}
	public WebElement getAddToCart(){
		return addToCart;
	}

	public WebElement getcheckOut(){
		return checkOut;
	}
	public WebElement getcheckoutMessage(){
		return checkoutMessage;
	}
	
	public WebElement getprodName(){
		return prodName;
	}
	public WebElement getprodDesc(){
		return prodDesc;
	}
	public WebElement getquantity(){
		return quantity;
	}
	public WebElement getprice(){
		return price;
	}
}
