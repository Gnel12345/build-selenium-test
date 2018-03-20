package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class BathroomCategoryPage extends BasePage {
	
	private By faucets;
	
	
	
	

	public BathroomCategoryPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		faucets = By.cssSelector("#section-tab-0 > section:nth-child(2) > div > div:nth-child(1) > div > a > img");
		
		
	}
	
	public WebElement onBathroomFaucets(){
		
		return driver.findElement(faucets);
	}
	
	

}
