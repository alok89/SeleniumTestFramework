package com.alok.SeleniumTestFramework.ManageBrowserDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.alok.SeleniumTestFramework.Prerequisites.BaseTest;

public abstract class BrowserDriverManager {

	protected WebDriver driver;
	protected static Properties properties = BaseTest.properties;
	private String pageLoadTimeUnitProperty = properties.getProperty("TimeUnit");
	private String pageLoadTimeProperty = properties.getProperty("PageLoadTime");
	private TimeUnit unit;
	
	public BrowserDriverManager() {
	}
	
	protected abstract void initializeBrowserDriver();
	protected abstract void startBrowserDriverServer();
	protected abstract void stopBrowserDriverServer();
	
	protected void configureBrowser() {
		if(driver != null) {
			driver.manage().window().maximize();
			if(pageLoadTimeUnitProperty.equalsIgnoreCase("Minutes"))
				unit = TimeUnit.MINUTES;
			else if(pageLoadTimeUnitProperty.equalsIgnoreCase("Seconds"))
				unit = TimeUnit.SECONDS;
			driver.manage().timeouts().pageLoadTimeout(Long.parseLong(pageLoadTimeProperty), unit);
		}
	}
	
	public WebDriver startTheBrowserDriverServerAndGetTheBrowserDriver() {
		startBrowserDriverServer();
		initializeBrowserDriver();
		return driver;
	}
	
	private void terminateBrowserDriverSession() {
		if(driver != null) {
			System.out.println("Closing all the browser instances");
			driver.quit();
		}
	}
	
	public void terminateBrowserDriverSessionAndShutDownTheServer() {
		terminateBrowserDriverSession();
		stopBrowserDriverServer();
	}
	
	public void closeBrowser() {
		if(driver != null) {
			System.out.println("Closing the single browser instance");
			driver.close();
		}
	}

}
