package com.alok.SeleniumTestFramework.Pages.Gmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.alok.SeleniumTestFramework.Utility.WaitsForWebElement;
import com.aventstack.extentreports.ExtentTest;

public class HomePage_HeaderFragment {
	
	private WebDriver driver;
	private ExtentTest testCaseLogger;
	private Actions actions;

	public HomePage_HeaderFragment(WebDriver driver, ExtentTest testCaseLogger, Actions actions) {
		this.driver = driver;
		this.testCaseLogger = testCaseLogger;
		this.actions = actions;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(how = How.CSS, using = "div[aria-label='Main menu']>svg")
	private WebElement mainMenu_Button;
	
	@FindBy(how = How.CSS, using = "button[class='gb_0e gb_1e'][ aria-label='Search Mail']")
	private WebElement searchIcon;
	
	@FindBy(how = How.CSS, using = "input[class='gb_Qe'][aria-label='Search mail']")
	private WebElement searchTextArea;
	
	@FindBy(how = How.CSS, using = "button[aria-label='Advanced search options']>svg")
	private WebElement advanceSearchOptionsIcon;
	
	@FindBy(how = How.CSS, using = "div.SK.ZF-zT")
	private WebElement advanceSearchOptionsArea;
	
	@FindBy(how = How.CSS, using = "a[aria-label='Google apps']>svg")
	private WebElement googleApps_Link;
	
	@FindBy(how = How.CSS, using = "a[class='gb_x gb_Ea gb_f']>span")
	private WebElement profileOptions_Link;

	@FindBy(how = How.ID, using = "gb_71")
	private WebElement signOut_Link;
	
	public void collapseOrExpandTheMainMenu() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 8, mainMenu_Button);
		mainMenu_Button.click();
	}

	public void searchEmailWithoutAnyCriteria(String email) {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 8, searchTextArea);
		searchTextArea.clear();
		searchTextArea.sendKeys(email);
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 5, searchIcon);
		searchIcon.click();
	}
	
	public SearchEmailSection searchEmailUsingAdvanceOptions() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 10, advanceSearchOptionsIcon);
		actions.moveToElement(advanceSearchOptionsIcon).click(advanceSearchOptionsIcon).build().perform();
		SearchEmailSection searchEmailSection = new SearchEmailSection(driver, testCaseLogger);
		return searchEmailSection;
	}
	
	public void openUserProfileOptions() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 5, profileOptions_Link);
		profileOptions_Link.click();
	}
	
	public void clickSignOut() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 8, signOut_Link);
		signOut_Link.click();
		WaitsForWebElement.waitFor(3000);
	}
}
