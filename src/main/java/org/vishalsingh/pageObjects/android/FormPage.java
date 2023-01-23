package org.vishalsingh.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.vishalsingh.utils.AndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions
{
	AndroidDriver driver;

	//We will create one constructor to give driver knowledge
	public FormPage (AndroidDriver driver)
	{ 
		super(driver);  // Super means it will call parent class constructor. So, that it will get idea of driver.
		this.driver = driver; //Here "this" keyword is used to refer the detail to the current class variable.
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);  //initElements select from ".. FieldDecorator"
		//initElements ==> will take all the inputs and will automatically convert into driver.findElement(By.id("com.androidsample.generalstore:id/nameField"));
	}
	
	//driver.findElement(By.id("com.androidsample.generalstore:id/nameField"));
	//Above line we will write in below way in Page Factory pattern
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")  //It is used when working on Android apps. (Not for IOS,etc)
	private WebElement NameField; 
	//Now in this "nameField" variable above @AndroidFindBy..... will get store. While storing it will automatically stored as "driver.findElement(By.id("com.androidsample.generalstore:id/nameField"));"

	//driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']"));
	@AndroidFindBy (xpath = "//android.widget.RadioButton[@text='Female']")
	private WebElement FemaleOption;
	
	@AndroidFindBy (xpath = "//android.widget.RadioButton[@text='Male']")
	private WebElement MaleOption;
	
	@AndroidFindBy (id = "android:id/text1")
	private WebElement CountrySelection;
	
	@AndroidFindBy (id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement ShopButton;
	
	public void setNameField (String name)
	{
		NameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void setActivity()
	{
		//screen to home page
		Activity activity=new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}
	
	public void setGender (String gender)
	{
		if (gender.contains("female"))
			FemaleOption.click();
		else
			MaleOption.click();
	}
	
	public void setCountrySelection(String countryName)
	{
		CountrySelection.click();
		scrollToText(countryName);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='"+countryName+"']")).click(); //For this page Factory is not created because it will change as per country.
	}
	
	public ProductCatalogue submitForm ()
	{
		ShopButton.click();
		return new ProductCatalogue(driver);
	}
	
	
}
