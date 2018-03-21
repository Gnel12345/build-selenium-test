package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class ShoppingCartPage extends BasePage{
	private By emailbutton;
	private By senderName;
	private By senderEmailAddress;
	private By senderMessage;
	private By recieverName;
	private By recieverEmailAddress;
	private By sendEmailButton;
	
	
	

	public ShoppingCartPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		
		emailbutton = By.cssSelector("#page-content > div.container > div > div > section:nth-child(2) > div > div.col-sm-12.col-md-8 > table > tbody > tr.text-center.pad-content > td > button.btn-standard.btn-secondary.btn-email.js-email-cart-button");
		senderName = By.id("yourName");
		senderEmailAddress = By.id("yourEmail");
		recieverName = By.id("recipientName");
		recieverEmailAddress = By.id("recipientEmail");
		senderMessage = By.id("quoteMessage");
		sendEmailButton = By.cssSelector("#cart-email > div > div > div.modal-body.clearfix.pad-content > div.left.js-email-cart-panel > form > div.col-xs-12 > button");
	}
	
	public WebElement onEmailButton() throws InterruptedException{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,70)", "");
		Thread.sleep(300);
		wait.until(ExpectedConditions.presenceOfElementLocated(emailbutton));
		return driver.findElement(emailbutton);
		
		
		
	}
	
	public WebElement onSenderName(){
		return driver.findElement(senderName);
		
	}
	
	public WebElement onSenderEmailAddress(){
		return driver.findElement(senderEmailAddress);		
		
	}
	
	public WebElement onRecieverName(){
		return driver.findElement(recieverName);	
		
	}
	
	public WebElement onRecieverEmailAddress(){
		return driver.findElement(recieverEmailAddress);
		
	}
	
	public WebElement onSenderMessage(){
		
		return driver.findElement(senderMessage);
	}
	
	public WebElement onSendEmailButton(){
		return driver.findElement(sendEmailButton);
		
	}

}
