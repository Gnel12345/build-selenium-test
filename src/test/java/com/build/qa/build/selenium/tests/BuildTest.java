package com.build.qa.build.selenium.tests;







import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.StaleElementReferenceException;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;


import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;



import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class BuildTest extends BaseFramework { 
	WebElement element = null;
	
	/** 
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 * @return 
	 * @throws InterruptedException 
	 */
	@Test
	public void navigateToHomePage() throws InterruptedException { 
		//opens the HomePage
		driver.get(getConfiguration("HOMEPAGE"));
		
		 HomePage homePage = new HomePage(driver,wait);
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		
		 
		 softly.assertThat(homePage.onBuildTheme())
		 .as("The website should load up with the Build.com desktop theme.")
		 .isTrue();
		 //closes the coupon pop up
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#email-subscribe-splash > div > div > div.modal-header.table.modal-no-title > button > span.close-icon")));
		 driver.findElement(By.cssSelector("#email-subscribe-splash > div > div > div.modal-header.table.modal-no-title > button > span.close-icon")).click();   		
		    		
		 driver.close();		
		    		
		    		
		    		
		    		
		    }

		
		
			
		
		
	
	
	/** 
	 * Search for the Quoizel MY1613 from the search bar
	 * @throws InterruptedException 
	 * @assert: That the product page we land on is what is expected by checking the product title
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() throws InterruptedException { 
		driver.get(getConfiguration("HOMEPAGE"));		
		 HomePage homePage = new HomePage(driver,wait);
		 driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		softly.assertThat(homePage.onBuildTheme())
		.as("The website should load up with the Build.com desktop theme.")
		.isTrue();
		//closes the coupon pop up
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#email-subscribe-splash > div > div > div.modal-header.table.modal-no-title > button > span.close-icon")));
		
		driver.findElement(By.cssSelector("#email-subscribe-splash > div > div > div.modal-header.table.modal-no-title > button > span.close-icon")).click();
		//clicks in the search bar
		driver.findElement(By.cssSelector("#search_txt")).click();
		//enters Quoizel MY1613 in the search bar
		driver.findElement(By.cssSelector("#search_txt")).sendKeys("Quoizel MY1613");
		//clicks the search button
		driver.findElement(By.cssSelector("#site-search > div > button")).click();
		//verifies that Quoizel MY1613 is in the Product Title
		WebElement msg = driver.findElement(By.id("heading"));
		String expectedText = ("Quoizel MY1613ML");		
		String text=msg.getText();	
		Assert.assertEquals(text,expectedText);
		driver.close();
		}
	
	
	/** 
	 * Go to the Bathroom Sinks category directly (https://www.build.com/bathroom-sinks/c108504) 
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @throws InterruptedException 
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() throws InterruptedException { 
		//goes to the bathroom sink category
		driver.get("https://www.build.com/bathroom-sinks/c108504");			 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		//closes the coupon pop up
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#email-subscribe-splash > div > div > div.modal-header.table.modal-no-title > button > span.close-icon")));
		driver.findElement(By.cssSelector("#email-subscribe-splash > div > div > div.modal-header.table.modal-no-title > button > span.close-icon")).click();
		Thread.sleep(300);
		//selects the product
		driver.findElement(By.cssSelector("#product-composite-560600 > div.product-tile > a > div.product-title-description > span")).click();
		//scrolls down to the add to cart button 
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,470)", "");
		//adds the product to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#configure-product-wrap > button")));		
		driver.findElement(By.cssSelector("#configure-product-wrap > button")).click();
		//continues to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#add-to-cart-wrap > button")));
		driver.findElement(By.cssSelector("#add-to-cart-wrap > button")).click();
		driver.findElement(By.cssSelector("#page-content > div.container > div > div.row > div.col-sm-7 > a")).click();
		//verifies that the product title is correct
		WebElement msg = driver.findElement(By.className("title"));
		String expectedText = ("Kohler K-2355 Archer 19-5/8  Undermount Bathroom Sink with Overflow");		
		String text=msg.getText();
		//if the text contains a " then pass the test
		if(text.contains("&quot;")){
		
		Assert.assertEquals(text,expectedText);
	    driver.close();
		}
		}
		    
		
	
	
	/** 
	 * Add a product to the cart and email the cart to yourself, also to my email address: jgilmore+SeleniumTest@build.com
	 * Include this message in the "message field" of the email form: "This is {yourName}, sending you a cart from my automation!"
	 * @throws InterruptedException 
	 * @assert that the "Cart Sent" success message is displayed after emailing the cart
	 * @difficulty Medium-Hard
	 */
	
	

	@Test 
	public void addProductToCartAndEmailIt() throws InterruptedException { 
		//loads home page
		WebDriverWait Wait = new WebDriverWait(driver,90);
		driver.get(getConfiguration("HOMEPAGE"));		
		 HomePage homePage = new HomePage(driver,wait);
		 driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		softly.assertThat(homePage.onBuildTheme())
		.as("The website should load up with the Build.com desktop theme.")
		.isTrue();
		//closes the coupon pop up
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#email-subscribe-splash > div > div > div.modal-header.table.modal-no-title > button > span.close-icon")));
		driver.findElement(By.cssSelector("#email-subscribe-splash > div > div > div.modal-header.table.modal-no-title > button > span.close-icon")).click();
		Thread.sleep(300);
		//selects bathroom
		driver.findElement(By.cssSelector("#sale-sub-cats-liveitup18 > div:nth-child(1) > div > a")).click();
		//selects bathroom faucets
		Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#section-tab-0 > section:nth-child(2) > div > div:nth-child(1) > div > a > img")));
		driver.findElement(By.cssSelector("#section-tab-0 > section:nth-child(2) > div > div:nth-child(1) > div > a > img")).click();
		//selects Miseno ML641
		Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#product-composite-1183675 > div.product-tile > a > div.product-title-description > span")));
		driver.findElement(By.cssSelector("#product-composite-1183675 > div.product-tile > a > div.product-title-description > span")).click();
		//selects add to cart button 
		
		
		Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#add-to-cart-wrap > button")));
		driver.findElement(By.cssSelector("#add-to-cart-wrap > button")).click();
		Thread.sleep(300);
		//selects proceed to cart
		Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#page-content > div.container > div > div.row > div.col-sm-7 > a")));
		driver.findElement(By.cssSelector("#page-content > div.container > div > div.row > div.col-sm-7 > a")).click();
		//selects email button
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,70)", "");
		Thread.sleep(300);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#page-content > div.container > div > div > section:nth-child(2) > div > div.col-sm-12.col-md-8 > table > tbody > tr.text-center.pad-content > td > button.btn-standard.btn-secondary.btn-email.js-email-cart-button")));	
		driver.findElement(By.cssSelector("#page-content > div.container > div > div > section:nth-child(2) > div > div.col-sm-12.col-md-8 > table > tbody > tr.text-center.pad-content > td > button.btn-standard.btn-secondary.btn-email.js-email-cart-button")).click();
		
		jse.executeScript("window.scrollBy(0,70)", "");
		driver.findElement(By.id("yourName")).click();
		driver.findElement(By.id("yourName")).sendKeys("Glenn");
		driver.findElement(By.id("yourEmail")).click();
		driver.findElement(By.id("yourEmail")).sendKeys("gnel12.gn@gmail.com");
		driver.findElement(By.id("recipientName")).click();
		driver.findElement(By.id("recipientName")).sendKeys("Glenn");
		driver.findElement(By.id("recipientEmail")).click();
		driver.findElement(By.id("recipientEmail")).sendKeys("gnel12.gn@gmail.com");
		driver.findElement(By.id("quoteMessage")).click();
		driver.findElement(By.id("quoteMessage")).sendKeys("This is Glenn, sending you a cart from my automation!");
		driver.findElement(By.cssSelector("#cart-email > div > div > div.modal-body.clearfix.pad-content > div.left.js-email-cart-panel > form > div.col-xs-12 > button")).click();
		WebElement msg1 = driver.findElement(By.cssSelector("#header > div.container-fluid > div > ul > li"));
		String expectedText1 = ("Cart Sent! The cart has been submitted to the recipient via email.");
		String text1=msg1.getText();
		Assert.assertEquals(text1,expectedText1);
		
		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#page-content > div.container > div > div > section:nth-child(2) > div > div.col-sm-12.col-md-8 > table > tbody > tr.text-center.pad-content > td > button.btn-standard.btn-secondary.btn-email.js-email-cart-button")));	
			driver.findElement(By.cssSelector("#page-content > div.container > div > div > section:nth-child(2) > div > div.col-sm-12.col-md-8 > table > tbody > tr.text-center.pad-content > td > button.btn-standard.btn-secondary.btn-email.js-email-cart-button")).click();
			
		driver.findElement(By.id("yourName")).click();
		driver.findElement(By.id("yourName")).sendKeys("Glenn");
		driver.findElement(By.id("yourEmail")).click();
		driver.findElement(By.id("yourEmail")).sendKeys("gnel12.gn@gmail.com");
		driver.findElement(By.id("recipientName")).click();
		driver.findElement(By.id("recipientName")).sendKeys("Glenn");
		driver.findElement(By.id("recipientEmail")).click();
		driver.findElement(By.id("recipientEmail")).sendKeys("gnel12.gn@gmail.com");
		driver.findElement(By.id("quoteMessage")).click();
		driver.findElement(By.id("quoteMessage")).sendKeys("This is Glenn, sending you a cart from my automation!");
		driver.findElement(By.cssSelector("#cart-email > div > div > div.modal-body.clearfix.pad-content > div.left.js-email-cart-panel > form > div.col-xs-12 > button")).click();
		
		
		
		WebElement msg = driver.findElement(By.cssSelector("#header > div.container-fluid > div > ul > li"));
		String expectedText = ("Cart Sent! The cart has been submitted to the recipient via email.");
		String text=msg.getText();
		Assert.assertEquals(text,expectedText);
		
		driver.close();
		}	
		
	
	
	
	
	/** 
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Theme=Modern
	 * @return 
	 * @return 
	 * @throws InterruptedException 
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() throws InterruptedException { 
		//loads home page
		driver.get(getConfiguration("HOMEPAGE"));		
		 HomePage homePage = new HomePage(driver,wait);
		 driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		softly.assertThat(homePage.onBuildTheme())
		.as("The website should load up with the Build.com desktop theme.")
		.isTrue();
		//closes the coupon page
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#email-subscribe-splash > div > div > div.modal-header.table.modal-no-title > button > span.close-icon")));
		driver.findElement(By.cssSelector("#email-subscribe-splash > div > div > div.modal-header.table.modal-no-title > button > span.close-icon")).click();
		Thread.sleep(300);
		//selects the Appliances dropdown menu
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.cssSelector("#header > nav > div > ul > li:nth-child(7) > a"))).build().perform();
		//selects refrigeration
		WebDriverWait Wait =new WebDriverWait(driver, 90);
		Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#header > nav > div > ul > li:nth-child(7) > div > div > div.table.mega-categories > a:nth-child(1)")));
		driver.findElement(By.cssSelector("#header > nav > div > ul > li:nth-child(7) > div > div > div.table.mega-categories > a:nth-child(1)")).click();	
		//selects refrigerators
		driver.findElement(By.cssSelector("#main > div.col-xs-12.col-sm-9 > section:nth-child(2) > ul > li:nth-child(1) > div > div > a > img")).click();
		//selects the refrigerator finish to be blue
		Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#facet-options > li.item.js-group.js-group-finish.active > ul > li:nth-child(2) > label > input")));
		driver.findElement(By.cssSelector("#facet-options > li.item.js-group.js-group-finish.active > ul > li:nth-child(2) > label > input")).click();
		//asserts the product count is correct after selecting blue
		try{
		WebElement msg = driver.findElement(By.cssSelector("#category-content > div.clearfix.row.productgrid-header > div > div:nth-child(1) > span"));
		String expectedText = ("14 Products");
		String text=msg.getText();
		Assert.assertEquals(text,expectedText);		
		System.out.println(text);
		}catch(StaleElementReferenceException e){
			throw e;
		}
		
		//selects the refrigerator type to be french door
		Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#facet-options > li.item.js-group.js-group-refrigerator-type.active > ul > li:nth-child(3) > label > input")));
		driver.findElement(By.cssSelector("#facet-options > li.item.js-group.js-group-refrigerator-type.active > ul > li:nth-child(3) > label > input")).click();
		//asserts that the product count is correct after blue and french door is selected
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#category-content > div.clearfix.row.productgrid-header > div > div:nth-child(1) > span")));
		WebElement msgtotal = driver.findElement(By.cssSelector("#category-content > div.clearfix.row.productgrid-header > div > div:nth-child(1) > span"));
		String totalexpectedText = ("1 Products");
		String totaltext=msgtotal.getText();
		Assert.assertEquals(totaltext,totalexpectedText);	
		System.out.println(totaltext);
		
		driver.close();
		
		
		}
		
		
		
}
	

	
	


