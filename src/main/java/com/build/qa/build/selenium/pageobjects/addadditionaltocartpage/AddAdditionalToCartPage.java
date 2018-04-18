package com.build.qa.build.selenium.pageobjects.addadditionaltocartpage;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class AddAdditionalToCartPage extends BasePage {
	
	public By AddAdditional;
	

	public AddAdditionalToCartPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		AddAdditional = By.cssSelector("#add-to-cart-wrap > button");
	}
	
	public WebElement onAddMore() throws InterruptedException{
		
		wait.until(ExpectedConditions.presenceOfElementLocated(AddAdditional));
		return driver.findElement(AddAdditional);
		
		
		
	}
	
	
	
	
	

}
