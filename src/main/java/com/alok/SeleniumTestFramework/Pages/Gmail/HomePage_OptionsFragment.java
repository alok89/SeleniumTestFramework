package com.alok.SeleniumTestFramework.Pages.Gmail;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.alok.SeleniumTestFramework.Utility.WaitsForWebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class HomePage_OptionsFragment {
	
	private WebDriver driver;
	private ExtentTest testCaseLogger;

	public HomePage_OptionsFragment(WebDriver driver, ExtentTest testCaseLogger) {
		this.driver = driver;
		this.testCaseLogger = testCaseLogger;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(how = How.CSS, using = "div[gh='mtb'] span[class='T-Jo J-J5-Ji']")
	private WebElement selectAll_Checkbox;
	
	@FindBy(how = How.CSS, using = "div[gh='mtb'] div[data-tooltip='Select'] div.G-asx.T-I-J3.J-J5-Ji")
	private WebElement selectDropDown_Icon;
	
	@FindBy(how = How.CSS, using = "div[gh='tm'] div[class='J-M jQjAxd'] div.J-N-Jz")
	private List<WebElement> selectEmailFilterCriteria_Options;
	
	@FindBy(how = How.CSS, using = "div.G-Ni.J-J5-Ji>div.T-I.J-J5-Ji.nu.T-I-ax7.L3")
	private WebElement refresh_Icon;
	
	@FindBy(how = How.CSS, using = "div[gh='tm'] div[title='Delete']>div")
	private WebElement delete_Icon;
	
	@FindBy(how = How.CSS, using = "div[data-tooltip='Older']>img")
	private WebElement emailsNextDisplay_Icon;
	
	@FindBy(how = How.CSS, using = "div[data-tooltip='Newer']>img")
	private WebElement emailsPreviousDisplay_Icon;
	
	public void selectAllDisplayedEmails() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 8, ExpectedConditions.elementToBeSelected(selectAll_Checkbox));
		testCaseLogger.log(Status.INFO, "Check All checbox to select all displayed emails");
		selectAll_Checkbox.click();
		WaitsForWebElement.waitFor(2000);
	}

	public void selectEmailsBasedOnOptions(String optionName) {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 8, selectDropDown_Icon);
		selectDropDown_Icon.click();
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 8, ExpectedConditions.visibilityOfAllElements(selectEmailFilterCriteria_Options));
		for(WebElement element : selectEmailFilterCriteria_Options) {
			if(element.getText().equalsIgnoreCase(optionName))
				element.click();
		}
		WaitsForWebElement.waitFor(2000);
	}
	
	public void deleteAllSelectedEmails() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 15, delete_Icon);
		delete_Icon.click();
	}
	
	public void refreshAllEmails() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 15, refresh_Icon);
		refresh_Icon.click();
	}
 }
