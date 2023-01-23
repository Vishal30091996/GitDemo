package org.vishalsingh.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.vishalsingh.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions
{
	AndroidDriver driver;
	public CartPage (AndroidDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this); 
		
	}
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")  //It is used when working on Android apps. (Not for IOS,etc)
	private List<WebElement> productList; 
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")  //It is used when working on Android apps. (Not for IOS,etc)
	private WebElement totalAmount; 
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")  //It is used when working on Android apps. (Not for IOS,etc)
	private WebElement terms; 
	
	@AndroidFindBy(id="android:id/button1")  //It is used when working on Android apps. (Not for IOS,etc)
	private WebElement acceptButton; 
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")  //It is used when working on Android apps. (Not for IOS,etc)
	private WebElement proceed;
	
	@AndroidFindBy(className="android.widget.CheckBox")  //It is used when working on Android apps. (Not for IOS,etc)
	private WebElement checkBox;
	
	public List<WebElement> getProductList()
	{
		return productList;
	}
	public double getProductSum()
	{
		int count = productList.size();
		double totalSum = 0;
		for (int i=0; i<count; i++)
		{
			String amountString = productList.get(i).getText();
			Double price = getFormattedAmount(amountString);
			totalSum = totalSum + price;
			
		}
		return totalSum;
	}
	public Double getTotalAmountDisplayed()
	{
		return getFormattedAmount(totalAmount.getText());
	}
	public void acceptTermsConditions()
	{
		LongPressAction(terms);
		acceptButton.click();
	}
	public Double getFormattedAmount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}
	public void submitOrder()
	{
		checkBox.click();
		proceed.click();
	}
	
}
