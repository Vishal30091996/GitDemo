package org.vishalsingh;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.vishalsingh.TestUtils.BaseTest_ECommerce_APP;
import org.vishalsingh.pageObjects.android.CartPage;
import org.vishalsingh.pageObjects.android.FormPage;
import org.vishalsingh.pageObjects.android.ProductCatalogue;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class ECommerce_TC_4_Hybrid extends BaseTest_ECommerce_APP
{
	
	@Test(dataProvider = "getData" , groups = {"Smoke"}) //"groups" keyword is used so that you can run selected test when you required
	public void fillForm(HashMap<String,String> input) throws Exception //Here "input" is the object name it can be anything of your choice.
	{
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender")); 
		formPage.setCountrySelection(input.get("country"));
		ProductCatalogue productCatalogue = formPage.submitForm(); //ProductCatalogue productCatalogue=new ProductCatalogue(driver);		
		
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCartPage();
		 
		Thread.sleep(2000);
		double Totalsum = cartPage.getProductSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(Totalsum, displayFormattedSum);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();
		Thread.sleep(5000);
		
	}
	@BeforeMethod(alwaysRun=true) 
	public void preSetup()
	{
		//screen to home page
		formPage.setActivity();
	}
	
	@DataProvider
	public Object[][] getData() throws Exception
	{
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\org\\vishalsingh\\testData\\ecommerce.json");
		return new Object [][] { {data.get(0)},{data.get(1)} };
	}
	
}
