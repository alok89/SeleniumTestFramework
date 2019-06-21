package com.alok.SeleniumTestFramework.Pages.Gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.alok.SeleniumTestFramework.Utility.WaitsForWebElement;
import com.aventstack.extentreports.ExtentTest;

public class SearchEmailSection {

	private WebDriver driver;
	
	public SearchEmailSection(WebDriver driver, ExtentTest testCaseLogger) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = "input.ZH.nr.aQa")
	private WebElement from_UserEmail_TextArea;
	
	@FindBy(how = How.CSS, using = "input.ZH.nr.aQd")
	private WebElement subject_TextArea;
	
	@FindBy(how = How.CSS, using = "div.T-axO.T-I.T-I-ax7.aaa.J-J5-Ji.J-JN-M-I")
	private WebElement dateWithin_SelectArea;
	
	@FindBy(how = How.XPATH, using = "//div[text()='2 months']")
	private WebElement dateValue;
	
	@FindBy(how = How.CSS, using = "div[data-tooltip='Search Mail']")
	private WebElement search_Button;
	
	@FindBy(how = How.CSS, using = "div.T-axO.T-I.T-I-ax7.J-J5-Ji.J-JN-M-I.ZE")
	private WebElement searchTextArea;
	
	public void enterTheFromUserEmail(String fromUserEmailID) {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 20, from_UserEmail_TextArea);
		from_UserEmail_TextArea.clear();
		from_UserEmail_TextArea.sendKeys(fromUserEmailID);
	}
	
	public void enterTheSubject(String subject) {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 20, subject_TextArea);
		subject_TextArea.clear();
		subject_TextArea.sendKeys(subject);
	}
	
	public void clickTheDateWithinAreaAndSelectDateValue() {
		dateWithin_SelectArea.click();
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 8, dateValue);
		dateValue.click();
	}
	
	public void clickSearchButton() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 5, search_Button);
		search_Button.click();
		WaitsForWebElement.waitFor(4000);
	}
	
	public void clickSearchTextArea(String enterText) {
		By locator = By.cssSelector("div[title='"+enterText+"']");
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 8, locator);
		driver.findElement(locator).click();
	}
	
	public String getTextOfSearchTextArea() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 8, searchTextArea);
		String text = searchTextArea.getAttribute("aria-label");
		return text;
	}
}
