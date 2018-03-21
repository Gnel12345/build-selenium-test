package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class HomePage extends BasePage {
	
	private By buildThemeBody;
	private By coupon;
	private By search;
	private By searchButton;
	private By bathRooms;
	private By refrigeration;
	private By appliancesDropDown;
		
	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		buildThemeBody = By.cssSelector("body.build-theme"); 
		coupon = By.cssSelector("#email-subscribe-splash > div > div > div.modal-header.table.modal-no-title > button > span.close-icon");
		search = By.cssSelector("#search_txt");
		searchButton = By.cssSelector("#site-search > div > button");
		bathRooms = By.cssSelector("#sale-sub-cats-liveitup18 > div:nth-child(1) > div > a");
		appliancesDropDown = By.linkText("https://www.build.com/appliances/c109152");
		refrigeration = By.cssSelector("#header > nav > div > ul > li:nth-child(7) > div > div > div.table.mega-categories > a:nth-child(1)");
	}  
	
	public boolean onBuildTheme() { 
		return wait.until(ExpectedConditions.presenceOfElementLocated(buildThemeBody)) != null;
	}
	public WebElement onCoupon(){
		
		 //wait.until(ExpectedConditions.presenceOfElementLocated(coupon));
		 return driver.findElement(coupon);
	}
	public WebElement onSearch(){
		return driver.findElement(search);
	}
	
	public WebElement onSearchButton(){
		return driver.findElement(searchButton);
		
	}
	
	public WebElement onBathRoomCategoryAd(){
		wait.until(ExpectedConditions.presenceOfElementLocated(bathRooms));
		return driver.findElement(bathRooms);
		
	}
	
	public WebElement onRefrigeration(){
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(refrigeration));
		return driver.findElement((refrigeration));	
	    
		
		
	}
	
	public void onAppliancesDropDown(){
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(appliancesDropDown)).build().perform();
		
	}
	
	
	
	
	
}
