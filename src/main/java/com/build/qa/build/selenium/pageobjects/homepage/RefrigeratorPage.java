package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class RefrigeratorPage extends BasePage{
	
	private By finish;
	private By type;

	public RefrigeratorPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		finish = By.cssSelector("#facet-options > li.item.js-group.js-group-finish.active > ul > li:nth-child(2) > label > input");
		type = By.cssSelector("#facet-options > li.item.js-group.js-group-refrigerator-type.active > ul > li:nth-child(3) > label > input");
	}
	
	public WebElement onFinish() throws InterruptedException{
		Thread.sleep(300);
		wait.until(ExpectedConditions.presenceOfElementLocated(finish));
		return driver.findElement(finish);
		
	}
	
	public WebElement onType(){
		
		wait.until(ExpectedConditions.presenceOfElementLocated(type));
		return driver.findElement(type);
	}
	
	

}
