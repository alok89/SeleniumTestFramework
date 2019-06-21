package com.alok.SeleniumTestFramework.Pages.Gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.alok.SeleniumTestFramework.Utility.KeyboardActions;
import com.alok.SeleniumTestFramework.Utility.WaitsForWebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ComposeEmailSection {
	
	private WebDriver driver;
	private ExtentTest testCaseLogger;

	public ComposeEmailSection(WebDriver driver, ExtentTest testCaseLogger) {
		this.driver = driver;
		this.testCaseLogger = testCaseLogger;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(how = How.CSS, using = "div.nH.Hd")
	private WebElement compose_Messagebox;
	
	@FindBy(how = How.NAME, using = "to")
	private WebElement to_Textbox;
	
	@FindBy(how = How.NAME, using = "subjectbox")
	private WebElement subject_Textbox;
	
	@FindBy(how = How.CSS, using = "div[aria-label='Message Body']")
	private WebElement body_Textbox;
	
	@FindBy(how = How.CSS, using = "div.T-I.J-J5-Ji.aoO.v7.T-I-atl.L3")
	private WebElement send_Button;
	
	@FindBy(how = How.CSS, using = "span[aria-label='Add Cc recipients ‪(Ctrl-Shift-C)‬']")
	private WebElement cc_Link;
	
	@FindBy(how = How.NAME, using = "cc")
	private WebElement cc_Textbox;
	
	@FindBy(how = How.CSS, using = "img[aria-label='Save & close']")
	private WebElement closeIcon;
	
	@FindBy(how = How.CSS, using = "div.a1.aaA.aMZ")
	private WebElement attachFiles_Icon;
	
	public void enterTheUserEmailID(String... toEmailID) {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 20, to_Textbox);
		testCaseLogger.log(Status.INFO, "Entering the user email Id");
		to_Textbox.clear();
		to_Textbox.sendKeys(toEmailID);
	}
	
	public void enterTheSubject(String subject) {
		subject_Textbox.clear();
		subject_Textbox.sendKeys(subject);
	}
	
	public void enterTheMessageBody(String messageBody) {
		body_Textbox.clear();
		body_Textbox.sendKeys(messageBody);
	}
	
	public boolean isComposeMessageSectionOpen() {
		try {
			if(compose_Messagebox.isDisplayed())
				return true;
			else
				return false;
		}catch(StaleElementReferenceException e) {
			return false;
		}
	}
	
	public void clickSendButton() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 10, send_Button);
		send_Button.click();
	}
	
	public void enterTheCCEmailId(String... ccEmailID) {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 10, cc_Link);
		cc_Link.click();
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 5, cc_Textbox).sendKeys(ccEmailID);
	}
	
	public void closeTheComposeMessageSection() {
		closeIcon.click();
	}
	
	public boolean isComposeMessageSectionClosed(String subject) {
		By locator = By.cssSelector("div[aria-label='"+subject+"']");
		WebElement composeMessageSection = driver.findElement(locator);
		if(composeMessageSection.isDisplayed())
			return false;
		else 
			return true;
	}
	
	public void uploadFile(String filePath) {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 10, attachFiles_Icon);
		attachFiles_Icon.click();
		WaitsForWebElement.waitFor(2000);
		KeyboardActions.pasteValuesOnWindowPopups(filePath);
		WaitsForWebElement.waitFor(4000);
	}

}
