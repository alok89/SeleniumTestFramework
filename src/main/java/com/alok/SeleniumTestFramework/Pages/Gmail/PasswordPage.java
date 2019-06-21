package com.alok.SeleniumTestFramework.Pages.Gmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.alok.SeleniumTestFramework.Pages.BasePage;
import com.aventstack.extentreports.ExtentTest;

public class PasswordPage extends BasePage {

	public PasswordPage(WebDriver driver, ExtentTest testCaseLogger) {
		super(driver, testCaseLogger);
		PageFactory.initElements(super.driver, this);
	}

	@FindBy(how = How.NAME, using = "password")
	private WebElement password_Textbox;

	@FindBy(how = How.XPATH, using = "//span[text()='Next']")
	private WebElement next_Button;

	@FindBy(how = How.ID, using = "initialView")
	private WebElement passwordArea;

	private void enterPassword(String password) {
		if(this.isAt()) {
			wait.until(ExpectedConditions.elementToBeClickable(password_Textbox));
			password_Textbox.clear();
			password_Textbox.sendKeys(password);
		}
	}

	private void clickNextButton() {
		next_Button.click();
	}

	public HomePage enterPasswordAndGoToHomePage(String password) {
		enterPassword(password);
		clickNextButton();
		HomePage homePage = new HomePage(driver, testCaseLogger);
		return homePage;
	}

	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(passwordArea));
		return passwordArea.isDisplayed();
	}

}
