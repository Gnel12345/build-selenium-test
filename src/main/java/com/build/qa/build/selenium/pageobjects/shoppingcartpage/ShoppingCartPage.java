package com.build.qa.build.selenium.pageobjects.shoppingcartpage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class ShoppingCartPage extends BasePage{
	public By emailbutton;
	public By senderName;
	public By senderEmailAddress;
	public By senderMessage;
	public By recieverName;
	public By recieverEmailAddress;
	public By sendEmailButton;
	
	
	

	public ShoppingCartPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		
		emailbutton = By.cssSelector("#page-content > div.container > div > div > section:nth-child(2) > div > div.col-sm-12.col-md-8 > table > tbody > tr.text-center.pad-content > td > button.btn-standard.btn-secondary.btn-email.js-email-cart-button");
		senderName = By.id("yourName");
		senderEmailAddress = By.id("yourEmail");
		recieverName = By.id("recipientName");
		recieverEmailAddress = By.id("recipientEmail");
		senderMessage = By.id("quoteMessage");
		sendEmailButton = By.xpath("//*[@id=cart-email]/div/div/div[2]/div[2]/form/div[4]/button");
	}
	
	public WebElement onEmailButton() throws InterruptedException{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,70)", "");
		Thread.sleep(300);
		wait.until(ExpectedConditions.presenceOfElementLocated(emailbutton));
		return driver.findElement(emailbutton);
		
		
		
	}
	
	public WebElement onSenderName(){
		wait.until(ExpectedConditions.presenceOfElementLocated(senderName));
		return driver.findElement(senderName);
		
	}
	
	public WebElement onSenderEmailAddress(){
		wait.until(ExpectedConditions.presenceOfElementLocated(senderEmailAddress));
		return driver.findElement(senderEmailAddress);		
		
	}
	
	public WebElement onRecieverName(){
		wait.until(ExpectedConditions.presenceOfElementLocated(recieverName));
		return driver.findElement(recieverName);	
		
	}
	
	public WebElement onRecieverEmailAddress(){
		wait.until(ExpectedConditions.presenceOfElementLocated(recieverEmailAddress));
		return driver.findElement(recieverEmailAddress);
		
	}
	
	public WebElement onSenderMessage(){
		wait.until(ExpectedConditions.presenceOfElementLocated(senderMessage));
		return driver.findElement(senderMessage);
	}
	
	public WebElement onSendEmailButton(){
		wait.until(ExpectedConditions.presenceOfElementLocated(sendEmailButton));
		return driver.findElement(sendEmailButton);
		
	}

}
