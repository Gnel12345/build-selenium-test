package com.build.qa.build.selenium.pageobjects.refrigerationpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class RefrigerationPage extends BasePage {
	
	private By refrigerators;
	
	
	
	
	
	public RefrigerationPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		refrigerators = By.cssSelector("#main > div.col-xs-12.col-sm-9 > section:nth-child(2) > ul > li:nth-child(1) > div > div > a > img");
		
	}

	public WebElement onRefrigerators(){
		
		wait.until(ExpectedConditions.presenceOfElementLocated(refrigerators));
		return driver.findElement(refrigerators);
		
	}

}
