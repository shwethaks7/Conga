package utilities;


import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public static WebDriver driverObj;
	public Properties prop;
	public WebDriverWait wait;
	
	public WebDriver initializedriver() throws IOException
	{
		String userDir = System.getProperty("user.dir");
				
		prop= new Properties();
   		FileInputStream fp = new FileInputStream(userDir+"\\src\\main\\java\\resources\\data.properties");
		prop.load(fp);
   		
   		
   		WebDriverManager.chromedriver().setup();
		driverObj = new ChromeDriver();
		driverObj.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driverObj,10);
		return driverObj;
		
	}
	
	public String getUserId() {
		 return prop.getProperty("userId");
   	}
	public String getPaswd() {
		  return prop.getProperty("paswd");
	}
	
	public String getLoginUrl() {
		  return prop.getProperty("url");
	}
	
	
}
