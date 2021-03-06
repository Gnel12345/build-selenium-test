package com.build.qa.build.selenium.pageobjects.bathroomsinkspage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class BathroomSinksPage extends BasePage{
	
	
	public By KohlerK2355;

	public BathroomSinksPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		KohlerK2355 = By.cssSelector("#product-composite-560600 > div.product-tile > a > div.product-title-description > span");   
	}
	
	public WebElement onUndermountSink(){
		
		
		return driver.findElement(KohlerK2355);
		
		
	
	}

}
