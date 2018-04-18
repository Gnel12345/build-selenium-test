package com.build.qa.build.selenium.framework;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public abstract class BaseFramework {
	protected WebDriver driver;
	protected Wait<WebDriver> wait;
	private static final Logger LOG = LoggerFactory.getLogger(BaseFramework.class);
	private static final String CONFIG_FILE = "./conf/automation.properties";
	private static final String DRIVER_FIREFOX = "firefox";
	private static final String DRIVER_CHROME = "chrome";
	private static final String DRIVER_IE = "ie";
	private static final String DRIVER_OPERA = "opera";
	private static final String Android = "android";
	
	private static Properties configuration;




	@Rule
	public final   JUnitSoftAssertions softly = new JUnitSoftAssertions();
	
	@BeforeClass
	public static void beforeClass() throws IOException {
		configuration = new Properties();
		FileInputStream input;

		LOG.info("Loading in configuration file.");
		input = new FileInputStream(new File(CONFIG_FILE));
		configuration.loadFromXML(input);
		input.close();
	}
	
	@BeforeMethod
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public void setUpBefore() throws MalformedURLException  {
		DesiredCapabilities capabilities;
		// Which driver to use? 
		if (DRIVER_CHROME.equalsIgnoreCase(configuration.getProperty("BROWSER"))){
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			File file = new File("driver/chromedriver.exe");
			 System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
			driver = new ChromeDriver(options);
		} if  (DRIVER_FIREFOX.equalsIgnoreCase(configuration.getProperty("BROWSER"))) {
			capabilities = DesiredCapabilities.firefox();
			File file = new File("driver/geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());

			File pathToBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("dom.webnotifications.enabled", false);
		 	driver = new FirefoxDriver(ffBinary);
			
		}if (DRIVER_IE.equalsIgnoreCase(configuration.getProperty("BROWSER"))){
			File file = new File("driver/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			driver = new InternetExplorerDriver();
		}if (DRIVER_OPERA.equalsIgnoreCase(configuration.getProperty("BROWSER"))){
			OperaOptions op= new OperaOptions();
		    op.setBinary("C:\\Program Files\\Opera\\launcher.exe"); 
		    op.addArguments("--disable-notifications");
			File file = new File("driver/operadriver.exe");
			System.setProperty("webdriver.opera.driver", file.getAbsolutePath());
						
			driver = new OperaDriver(op);
			
		}if (Android.equalsIgnoreCase(configuration.getProperty("BROWSER"))){
			// Create object of  DesiredCapabilities class and specify android platform
			DesiredCapabilities androidCapabilities=DesiredCapabilities.android();
			 
			File file = new File("driver/chromedriver.exe");
			 System.setProperty("webdriver.chrome.driver", file.getAbsolutePath()); 
			// set the capability to execute test in chrome browser
			androidCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
			 
			// set the capability to execute our test in Android Platform
			androidCapabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);
			 
			// we need to define platform name
			androidCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
			 
			// Set the device name as well (you can give any name)
			androidCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Demo");
			 
			 // set the android version as well 
			androidCapabilities.setCapability(MobileCapabilityType.VERSION,"7.0");
			 
			 // Create object of URL class and specify the appium server address
			 URL url= new URL("http://127.0.0.1:4723/wd/hub");
			 
			// Create object of  AndroidDriver class and pass the url and capability that we created
			 driver = new AndroidDriver(url, androidCapabilities);
			
		}
		
		
		
		
		// Define fluent wait
		wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
	}
	
	protected WebDriver getDriver() {
		
		return driver;
	}
	
	protected String getConfiguration(String config) { 
		return configuration.getProperty(config);
	}

	@AfterMethod
	public void tearDownAfter() {
		LOG.info("Quitting driver.");
		driver.quit();
		driver = null;
	}
	
	
	
}
