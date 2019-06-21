package com.alok.SeleniumTestFramework.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public abstract class BasePage {
	
	protected Wait<WebDriver> wait;
	protected WebDriver driver;
	protected ExtentTest testCaseLogger;
	protected Actions actions;
	
	public BasePage(WebDriver driver, ExtentTest testCaseLogger) {
		this.driver = driver;
		this.testCaseLogger = testCaseLogger;
		wait = new WebDriverWait(this.driver, 50);
		actions = new Actions(this.driver);
	}
	
	public abstract boolean isAt();
	
	public void goToContext(String contextUrl) {
		testCaseLogger.log(Status.INFO, "Hitting the context url");
		driver.get(contextUrl);
	}
	
	public void sleepTime(int timeInSeconds) {
		try {
			testCaseLogger.log(Status.INFO, "Waiting for "+timeInSeconds+" secs");
			Thread.sleep(timeInSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getPageTitle() {
		testCaseLogger.log(Status.INFO, "Getting the Title of the page");
		return driver.getTitle();
	}
	
	public void navigateForward() {
		testCaseLogger.log(Status.INFO, "Navigating forward");
		driver.navigate().forward();
	}
	
	public void navigateBackward() {
		testCaseLogger.log(Status.INFO, "Navigating backward");
		driver.navigate().back();
	}

}
