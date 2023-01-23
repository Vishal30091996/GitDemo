package org.vishalsingh.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumActions
{

	//In this file we will write code which is common (Reusable Actions) and will be used in any program.
	
	AndroidDriver driver;
	public AndroidActions (AndroidDriver driver) //Constructor
	{
		this.driver = driver;
	}
	
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
	
	public void scrollToText (String text)
	{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
	}
	
	public void SwipeAction(WebElement ele, String direction)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
			    "direction", direction,
			    "percent", 0.75
			));
	}

}
