package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class ShoppingCartPage extends BasePage{
	private By email;
	
	
	

	public ShoppingCartPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		
		email = By.cssSelector("#page-content > div.container > div > div > section:nth-child(2) > div > div.col-sm-12.col-md-8 > table > tbody > tr.text-center.pad-content > td > button.btn-standard.btn-secondary.btn-email.js-email-cart-button");
		
	}
	
	public WebElement onEmail() throws InterruptedException{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,70)", "");
		Thread.sleep(300);
		wait.until(ExpectedConditions.presenceOfElementLocated(email));
		return driver.findElement(email);
		
		
		
	}
	
	

}
