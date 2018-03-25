package com.build.qa.build.selenium.pageobjects.addedtocartpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class AddedToCartPage extends BasePage{
	private By ProceedToCart;
	
	
	

	public AddedToCartPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		
		ProceedToCart = By.cssSelector("#page-content > div.container > div > div.row > div.col-sm-7 > a");
		
		
	}
	
	public WebElement onProceedToCart(){
		wait.until(ExpectedConditions.presenceOfElementLocated(ProceedToCart));
				return driver.findElement(ProceedToCart);
		
		
		
	}

}
