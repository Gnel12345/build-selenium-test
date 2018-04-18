package com.build.qa.build.selenium.tests;







import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.DOMConfiguration;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;




import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.addadditionaltocartpage.AddAdditionalToCartPage;
import com.build.qa.build.selenium.pageobjects.addedtocartpage.AddedToCartPage;
import com.build.qa.build.selenium.pageobjects.bathroomfaucetspage.BathroomFaucetsPage;
import com.build.qa.build.selenium.pageobjects.bathroomsinkspage.BathroomSinksPage;

import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import com.build.qa.build.selenium.pageobjects.productdescriptionpage.ProductDescriptionPage;
import com.build.qa.build.selenium.pageobjects.refrigerationpage.RefrigerationPage;
import com.build.qa.build.selenium.pageobjects.refrigeratorpage.RefrigeratorPage;
import com.build.qa.build.selenium.pageobjects.shoppingcartpage.ShoppingCartPage;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;


public class BuildTest extends BaseFramework { 
	@BeforeMethod
	public void beforeTests(){
		DOMConfigurator.configure("log4j.xml");
	}
	
	WebElement element = null;
	
	public static Logger Log = LogManager.getLogger(BuildTest.class.getName());
	
	/** 
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 * 
	 * @return 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@Test
	public  void navigateToHomePage() throws InterruptedException, IOException { 
		//opens the HomePage
		driver.get(getConfiguration("HOMEPAGE"));
		Log.info("Driver successfully initialized");
		 HomePage homePage = new HomePage(driver,wait);
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		
		 
		 softly.assertThat(homePage.onBuildTheme())
		 .as("The website should load up with the Build.com desktop theme.")
		 .isTrue();
		 Log.info("Website successfully loaded");
		 
		 //closes the coupon pop up
		 
		homePage.onCoupon().click();
		//Log.info("Coupon pop up successfully closed");
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("/test-output/screenshots/screenshot.jpeg"));
		 driver.close();	
		 //Log.info("Driver successfully closed");
		    		
		    		
		    		
		    		
		    }

		
		
			
		
		
	
	
	/** 
	 * Search for the Quoizel MY1613 from the search bar
	 * @param result 
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @assert: That the product page we land on is what is expected by checking the product title
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() throws InterruptedException, IOException { 
		driver.get(getConfiguration("HOMEPAGE"));	
		//Log.info("Driver successfully initialized");
		 HomePage homePage = new HomePage(driver,wait);
		 driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		softly.assertThat(homePage.onBuildTheme())
		.as("The website should load up with the Build.com desktop theme.")
		.isTrue();
		//Log.info("Website successfully loaded");
		getScreenshot();
		//closes the coupon pop up
		if(homePage.onCoupon().isDisplayed()){
		homePage.onCoupon().click();
		driver.manage().timeouts().implicitlyWait(80,TimeUnit.SECONDS);
		homePage.onSearch().click();
		//Log.info("Search bar successfully clicked ");
		getScreenshot();
		//enters Quoizel MY1613 in the search bar
		homePage.onSearch().sendKeys("Quoizel MY1613");
		//Log.info("Quoizel MY1613 entered");
		getScreenshot();
		//clicks the search button
		homePage.onSearchButton().click();
		//Log.info("Search button clicked");
		//verifies that Quoizel MY1613 is in the Product Title
		WebElement msg = driver.findElement(By.id("heading"));
		String expectedText = ("Quoizel MY1613ML");		
		String text=msg.getText();	
		AssertJUnit.assertEquals(text,expectedText);
		//Log.info(text);
		driver.close();
		//clicks in the search bar
		}else{
		driver.manage().timeouts().implicitlyWait(80,TimeUnit.SECONDS);
		homePage.onSearch().click();
		//enters Quoizel MY1613 in the search bar
		homePage.onSearch().sendKeys("Quoizel MY1613");
		//clicks the search button
		homePage.onSearchButton().click();
		//verifies that Quoizel MY1613 is in the Product Title
		WebElement msg = driver.findElement(By.id("heading"));
		String expectedText = ("Quoizel MY1613ML");		
		String text=msg.getText();	
		AssertJUnit.assertEquals(text,expectedText);
		System.out.println(text);
		driver.close();
		}
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
		//Log.info("Bathroom sinks page successfully loaded");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		//closes the coupon pop up
		HomePage homePage = new HomePage(driver, wait);
		homePage.onCoupon().click();
		Thread.sleep(300);
		//selects the product
		BathroomSinksPage BSP = new BathroomSinksPage(driver, wait);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250","");
		
		BSP.onUndermountSink().click();
		//scrolls down to the add to cart button 
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollBy(0,470)", "");
		//adds the product to cart
		ProductDescriptionPage PD = new ProductDescriptionPage(driver, wait);
		PD.onAddtoCart().click();
		//continues to cart
		AddAdditionalToCartPage AddMore = new AddAdditionalToCartPage(driver, wait);
		wait.until(ExpectedConditions.presenceOfElementLocated((By) AddMore.onAddMore()));
		AddMore.onAddMore().click();
		AddedToCartPage addedtoCart = new AddedToCartPage(driver, wait);
		addedtoCart.onProceedToCart().click();
		//verifies that the product title is correct
		WebElement msg = driver.findElement(By.className("title"));
		String expectedText = ("Kohler K-2355 Archer 19-5/8  Undermount Bathroom Sink with Overflow");		
		String text=msg.getText();
		//if the text contains a " then pass the test
		if(text.contains("&quot;")){
		
		AssertJUnit.assertEquals(text,expectedText);
		System.out.println(text);
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
		
		driver.get(getConfiguration("HOMEPAGE"));
		//Log.info("Driver successfully initialized");
		 HomePage homePage = new HomePage(driver,wait);
		 driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		softly.assertThat(homePage.onBuildTheme())
		.as("The website should load up with the Build.com desktop theme.")
		.isTrue();
		//closes the coupon pop up
		
		homePage.onCoupon().click();
		//selects bathroom
		driver.manage().timeouts().implicitlyWait(150,TimeUnit.SECONDS);
		homePage.onBathRoomDropDown().build().perform();
		//Thread.sleep(300);
		//selects bathroom faucets
		
		homePage.onBathroomFaucets().click();
		//selects Miseno ML641
		BathroomFaucetsPage BSFP = new BathroomFaucetsPage(driver, wait);
		driver.manage().timeouts().implicitlyWait(80,TimeUnit.SECONDS);
		JavascriptExecutor jsee = (JavascriptExecutor)driver;
		jsee.executeScript("window.scrollBy(0,470)", "");
		BSFP.onMisenoML641().click();
		//selects add to cart button 
		ProductDescriptionPage PD = new ProductDescriptionPage(driver, wait);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,470)", "");
		PD.onAddtoCart().click();
		
		
		//selects proceed to cart
		AddedToCartPage addedtoCart = new AddedToCartPage(driver, wait);
		addedtoCart.onProceedToCart().click();
		//selects email button
		ShoppingCartPage shoppingCart = new ShoppingCartPage(driver, wait);
		if(shoppingCart.onEmailButton().isDisplayed()){
		shoppingCart.onEmailButton().click();
	    //fills out form
		shoppingCart.onSenderName().click();
		shoppingCart.onSenderName().sendKeys("Glenn");
		shoppingCart.onSenderEmailAddress().click();
		shoppingCart.onSenderEmailAddress().sendKeys("gnel1234@yahoo.com");
		shoppingCart.onRecieverName().click();
		shoppingCart.onRecieverName().sendKeys("Glenn");
		shoppingCart.onRecieverEmailAddress().click();
		shoppingCart.onRecieverEmailAddress().sendKeys("gnel1234@yahoo.com");
		shoppingCart.onSenderMessage().click();
		shoppingCart.onSenderMessage().sendKeys("This is Glenn, sending you a cart from my automation!");
		shoppingCart.onEmailButton().click();
		
		
		
		
		WebElement msg1 = driver.findElement(By.cssSelector("#header > div.container-fluid > div > ul > li"));
		String expectedText1 = ("Cart Sent! The cart has been submitted to the recipient via email.");
		String text1=msg1.getText();
		AssertJUnit.assertEquals(text1,expectedText1);
		System.out.println(text1);
		
		
		shoppingCart.onEmailButton().click();
			
		shoppingCart.onSenderName().click();
		shoppingCart.onSenderName().sendKeys("Glenn");
		shoppingCart.onSenderEmailAddress().click();
		shoppingCart.onSenderEmailAddress().sendKeys("gnel1234@yahoo.com");
		shoppingCart.onRecieverName().click();
		shoppingCart.onRecieverName().sendKeys("Glenn");
		shoppingCart.onRecieverEmailAddress().click();
		shoppingCart.onRecieverEmailAddress().sendKeys("gnel1234@yahoo.com");
		shoppingCart.onSenderMessage().click();
		shoppingCart.onSenderMessage().sendKeys("This is Glenn, sending you a cart from my automation!");
		shoppingCart.onEmailButton().click();
		
		
		
		WebElement msg = driver.findElement(By.cssSelector("#header > div.container-fluid > div > ul > li"));
		String expectedText = ("Cart Sent! The cart has been submitted to the recipient via email.");
		String text=msg.getText();
		AssertJUnit.assertEquals(text,expectedText);
		System.out.println(text);
		}else{
		driver.close();
		}
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
		//Log.info("Driver successfully initialized");
		 HomePage homePage = new HomePage(driver,wait);
		 driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		softly.assertThat(homePage.onBuildTheme())
		.as("The website should load up with the Build.com desktop theme.")
		.isTrue();
		//closes the coupon page
		homePage.onCoupon().click();
		//selects the Appliances dropdown menu	
		
		homePage.onAppliancesDropDown().build().perform();
		
		//selects refrigeration
		///wait.until(ExpectedConditions.presenceOfElementLocated((By) homePage.onRefrigeration())).isDisplayed();
		homePage.onRefrigeration().click();
		//selects refrigerators
		RefrigerationPage RP = new RefrigerationPage(driver, wait);
		RP.onRefrigerators().click();
		//selects the refrigerator finish to be blue
		RefrigeratorPage categories = new RefrigeratorPage(driver, wait);
		
		driver.manage().timeouts().implicitlyWait(80,TimeUnit.SECONDS);
		categories.onFinish().click();
		
		//asserts the product count is correct after selecting blue
		try{
		WebElement msg = driver.findElement(By.cssSelector("#category-content > div.clearfix.row.productgrid-header > div > div:nth-child(1) > span"));
		String expectedText = ("14 Products");
		String text=msg.getText();
		AssertJUnit.assertEquals(text,expectedText);		
		System.out.println(text);
		}catch(StaleElementReferenceException e){
			throw e;
		}
		
		//selects the refrigerator type to be french door
		categories.onType().click();
		//asserts that the product count is correct after blue and french door is selected
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#category-content > div.clearfix.row.productgrid-header > div > div:nth-child(1) > span")));
		WebElement msgtotal = driver.findElement(By.cssSelector("#category-content > div.clearfix.row.productgrid-header > div > div:nth-child(1) > span"));
		String totalexpectedText = ("1 Products");
		String totaltext=msgtotal.getText();
		AssertJUnit.assertEquals(totaltext,totalexpectedText);	
		System.out.println(totaltext);
		
		driver.close();
		
		
		}
		
	public  void getScreenshot() throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C://Users//Glenns//Desktop//Screenshots//screenshot.jpeg"));
		
	}
		
}
	

	
	


