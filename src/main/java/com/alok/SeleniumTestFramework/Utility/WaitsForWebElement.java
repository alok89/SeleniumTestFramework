package com.alok.SeleniumTestFramework.Utility;

import java.time.Duration;

import com.google.common.base.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitsForWebElement {

	private WaitsForWebElement() {}

	private static Wait<WebDriver> wait;
	private static WebElement element;

	public static WebElement findElementUsingFluentWait(WebDriver driver, long totalTimeInSecondsToWait, long timeInSecondsTopollDOM, 
			final By locatorValueOfElement) {
		wait = initializeFluentWait(driver, totalTimeInSecondsToWait, timeInSecondsTopollDOM);
		element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locatorValueOfElement);
			}
		});
		return element;
	}

	public static WebElement findElementUsingWebDriverWait(WebDriver driver, long totalTimeInSecondsToWait, By locatorValueOfElement) {
		wait = initializeWebDriverWait(driver, totalTimeInSecondsToWait);
		element = wait.until(ExpectedConditions.elementToBeClickable(locatorValueOfElement));
		return element;
	}

	public static WebElement findElementUsingWebDriverWait(WebDriver driver, long totalTimeInSecondsToWait, WebElement element) {
		wait = initializeWebDriverWait(driver, totalTimeInSecondsToWait);
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}

	public static <T> T findElementUsingWebDriverWait(WebDriver driver, long totalTimeinSecondsToWait, ExpectedCondition<T> condition) {
		wait = initializeWebDriverWait(driver, totalTimeinSecondsToWait);
		T object = wait.until(condition);
		return object;
	}

	public static void waitFor(long timeInMilliSeconds) {
		try {
			Thread.sleep(timeInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static Wait<WebDriver> initializeFluentWait(WebDriver driver, long totalTimeInSecondsToWait, long timeInSecondsTopollDOM) {
		FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
										.withTimeout(Duration.ofSeconds(totalTimeInSecondsToWait))
										.pollingEvery(Duration.ofSeconds(timeInSecondsTopollDOM))
										.ignoring(ElementNotVisibleException.class, NoSuchElementException.class);
		return fWait;
	}

	private static Wait<WebDriver> initializeWebDriverWait(WebDriver driver, long totalTimeInSecondsToWait) {
		WebDriverWait wdWait = new WebDriverWait(driver, totalTimeInSecondsToWait);
		return wdWait;
	}

}
