package org.vishalsingh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.vishalsingh.TestUtils.BaseTest_ECommerce_APP;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class ECommerce_TC_1 extends BaseTest_ECommerce_APP
{
	
	@BeforeMethod  // this will run before every @Test so that home page will be visible for testing.
	public void preSetup()
	{
		//screen to home page
		Activity activity=new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		// Above package & Activity name we get from command prompt. for that open the app page and then in command prompt pass the below command
		// %adb shell dumpsys window | grep -E 'mCurrentFocus'
		driver.startActivity(activity);
		
	}
	
	@Test
	public void fillForm_PositiveFlow() throws Exception
	{
		
				driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vishal Singh");
				driver.hideKeyboard();
				driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
				driver.findElement(By.id("android:id/text1")).click();
				driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
				driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']")).click();
				driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
				Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
				
		
		/*driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vishal Singh");
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
		*/
		
	}
	
	@Test
	public void fillForm_ErrorValidation() throws Exception
	{
		
		
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vishal Singh");
		//driver.hideKeyboard(); //to hide keyboard
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String toastMessage = driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(toastMessage, "Please enter your name");
		
		
	}
	

}
