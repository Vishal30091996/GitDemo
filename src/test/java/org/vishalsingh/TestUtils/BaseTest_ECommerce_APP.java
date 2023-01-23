package org.vishalsingh.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.vishalsingh.pageObjects.android.FormPage;
import org.vishalsingh.utils.AppiumActions;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest_ECommerce_APP extends AppiumActions
{
	public AndroidDriver driver;
	public FormPage formPage;
	
	@BeforeClass(alwaysRun=true) //(alwaysRun=true) this is provided as this is must to run before every test. no need to check for grouping (Smoke/regression,etc) concept.
	public void ConfigureAppium() throws Exception
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\org\\vishalsingh\\resources\\data.properties");
		String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		prop.load(fis);
		//String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		//Note that all the values from properties file will be saved as String. So for integer values we will have to convert it.
		
		service = startAppiumServer(ipAddress, Integer.parseInt(port)); //port need to convert from string to int
		
		UiAutomator2Options options=new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDeviceName")); //For getting connection to your phone
		options.setChromedriverExecutable("C:\\Users\\Vishal\\eclipse-workspace_Appium\\Appium\\src\\test\\java\\resources\\chromedriver.exe");
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\org\\vishalsingh\\resources\\General-Store.apk"); //App path which we are going to test
		
		//driver=new AndroidDriver(new URL("http://127.0.0.1:4723"), options );// In java to provide any URL there is one class known as "URL"
		driver=new AndroidDriver(service.getUrl(), options );  //getUrl() method will automatically fetch the ipAddress "http://127.0.0.1:4723"
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
		
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() throws Exception
	{
		Thread.sleep(3000);
		driver.quit();
		service.stop(); //To stop the instance for running appium server
	}
	
	/*
	public void LongPressAction(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(), "duration",2000));
		//for more above mobile gesture link just visit: https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/android/android-mobile-gestures.md
				
	}
	
	public void ScrollToEndAction() throws Exception
	{
		boolean CanScrollMore;
		do  //do-while loop is used just to scroll till the end.
		{
			CanScrollMore = (Boolean) ((JavascriptExecutor)driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
					"left", 100, "top", 100, "width", 200, "height", 200,
					"direction", "down",
					"percent", 3.0));
		}while(CanScrollMore);
		Thread.sleep(1000);
	}
	
	public void SwipeAction(WebElement ele, String direction)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
			    "direction", direction,
			    "percent", 0.75
			));
	}
	*/
	
	

}
