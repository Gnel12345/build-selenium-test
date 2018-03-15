package com.build.qa.build.selenium.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
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
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUpBefore()  {
		DesiredCapabilities capabilities;
		// Which driver to use? 
		if (DRIVER_CHROME.equalsIgnoreCase(configuration.getProperty("BROWSER"))){
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications,--privileged");
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

	@After
	public void tearDownAfter() {
		LOG.info("Quitting driver.");
		driver.quit();
		driver = null;
	}
	
}
