package com.alok.SeleniumTestFramework.Pages.Gmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.alok.SeleniumTestFramework.Pages.BasePage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class EmailIDPage extends BasePage {
	
	public EmailIDPage(WebDriver driver, ExtentTest testCaseLogger) {
		super(driver, testCaseLogger);
		PageFactory.initElements(super.driver, this);
	}
	
	@FindBy(how = How.NAME, using = "identifier")
	private WebElement emailID_Textbox;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Next']")
	private WebElement next_Button;
	
	private static final String GMAILPAGE_TITLE = "Gmail";

	private void enterEmailID(String emailID) {
		wait.until(ExpectedConditions.elementToBeClickable(emailID_Textbox));
		emailID_Textbox.clear();
		emailID_Textbox.sendKeys(emailID);
	}
	
	private void clickNextButton() {
		next_Button.click();
	}
	
	public boolean hitContextUrlAndGoToEmailIdPage(String contextUrl) {
		goToContext(contextUrl);
		return this.isAt();
	}
	
	public PasswordPage enterEmailIdAndGoToPasswordPage(String emailId) {
		enterEmailID(emailId);
		clickNextButton();
		PasswordPage passwordPage = new PasswordPage(driver, testCaseLogger);
		return passwordPage;
	}

	@Override
	public boolean isAt() {
		String title = getPageTitle();
		testCaseLogger.log(Status.INFO, "Verifying the Title of the EmailId Page");
		return (GMAILPAGE_TITLE.equals(title));
	}

}
