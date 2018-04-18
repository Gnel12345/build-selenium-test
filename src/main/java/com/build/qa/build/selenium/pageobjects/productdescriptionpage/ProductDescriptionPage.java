package com.build.qa.build.selenium.pageobjects.productdescriptionpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class ProductDescriptionPage extends BasePage{
	public By AddToCart;
	
	

	public ProductDescriptionPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		
		AddToCart = By.id("add-to-cart-wrap");
		
	}
	
	
	public WebElement onAddtoCart() throws InterruptedException{
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(AddToCart));
		return driver.findElement(AddToCart);
		
	}
	
	
	

}
