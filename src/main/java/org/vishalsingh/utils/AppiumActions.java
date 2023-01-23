package org.vishalsingh.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class AppiumActions 
{
	public AppiumDriverLocalService service;	//Appium driver is superior to both android and IOS driver
	
	public Double getFormatedAmount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}
	
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws Exception
	{
		// Convert json file content to json string
		String jsonContent = FileUtils.readFileToString(new File (jsonFilePath), StandardCharsets.UTF_8);
		
		//Convert json string to Hash Map
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, 
				new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;
	}
	
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port)
	{
		//Appium Code --> Appium Server --> Mobile Device
				// Now first start Appium server by passing "Appium" in command prompt ===> Not required as we will now write code to automatically start appium server.
						
				service=new AppiumServiceBuilder()		//This is class with the help of which we will start appium server
						.withAppiumJS(new File ("C:\\Users\\Vishal\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")) //To start appium server you will require to give this path of main.js file
																//This AppData folder you may not find in your system as it in hidden mode so search it in address bar
						.withIPAddress(ipAddress).usingPort(port).build(); // This is IP address and port number of appium server. no need to give "http://" in IP address as it get automatic added by .withIPAddress method
				service.start();  //To start the instance for running appium server
				return service;
	}
	
	public void waitForElementToAppear(WebElement ele , AppiumDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5)); 
		wait.until(ExpectedConditions.attributeContains((ele), "text" , "Cart"));
		
	}
	
	public String getScreenshotPath(String testCaseName , AppiumDriver driver) throws Exception
	{
		File source = driver.getScreenshotAs(OutputType.FILE); //For taking screenshot
		String destinationFile = System.getProperty("user.dir")+"\\reports"+testCaseName+".png"; //For location and name of that screenshot.
		FileUtils.copyFile(source, new File(destinationFile)); //For knowing that from where to copy and where to save the screenshot
		return destinationFile;
		
		//Here 1) Capture & place in folder. 2) Extent report pick file & attach to report.
		
	}

}


