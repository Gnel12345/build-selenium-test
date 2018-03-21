package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class BathroomFaucetsPage extends BasePage{
	
	private By MisenoML641;
	
	

	public BathroomFaucetsPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		
		MisenoML641 = By.linkText("https://www.build.com/miseno-ml641/s1183675?uid=2833726");
		
	}
	
	
	
	
	public WebElement onMisenoML641(){
		
		wait.until(ExpectedConditions.presenceOfElementLocated(MisenoML641));
		return driver.findElement(MisenoML641);
	}

}
