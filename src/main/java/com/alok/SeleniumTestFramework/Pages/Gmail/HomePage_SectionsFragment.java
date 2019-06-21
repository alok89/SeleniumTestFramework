package com.alok.SeleniumTestFramework.Pages.Gmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.alok.SeleniumTestFramework.Utility.WaitsForWebElement;
import com.aventstack.extentreports.ExtentTest;

public class HomePage_SectionsFragment {
	
	private WebDriver driver;
	private ExtentTest testCaseLogger;

	public HomePage_SectionsFragment(WebDriver driver, ExtentTest testCaseLogger) {
		this.driver = driver;
		this.testCaseLogger = testCaseLogger;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(how = How.CSS, using = "div.T-I.J-J5-Ji.T-I-KE.L3")
	private WebElement compose_Button;
	
	@FindBy(how = How.CSS, using = "a[title='Inbox']")
	private WebElement inbox_Link;
	
	@FindBy(how = How.CSS, using = "a[title='Drafts']")
	private WebElement drafts_Link;
	
	@FindBy(how = How.CSS, using = "div.n6 span.CJ")
	private WebElement moreOptions_Button;
	
	@FindBy(how = How.CSS, using = "a[title='Sent']")
	private WebElement sent_Link;
	
	@FindBy(how = How.LINK_TEXT, using = "Trash")
	private WebElement trash_Link;
	
	public ComposeEmailSection openComposeEmailMessageSection() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 25, compose_Button);
		compose_Button.click();
		ComposeEmailSection composeEmailSection = new ComposeEmailSection(driver, testCaseLogger);
		return composeEmailSection;
	}
	
	public void openDraftsSection() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 5, drafts_Link);
		drafts_Link.click();
	}
	
	public void openInboxSection() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 5, inbox_Link);
		inbox_Link.click();
	}
	
	public void openSentEmailsSection() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 5, sent_Link);
		sent_Link.click();
	}
	
	public void goToMoreOptionsForSections() {
		if(moreOptions_Button.isDisplayed()) {
			WaitsForWebElement.findElementUsingWebDriverWait(driver, 10, moreOptions_Button);
			if(moreOptions_Button.getText().equals("More")) 
				moreOptions_Button.click();
			else if(moreOptions_Button.getText().equals("Less")) {
				moreOptions_Button.click();
				WaitsForWebElement.waitFor(2000);
				moreOptions_Button.click();
			}
		}
	}
	
	public void openTrashSection() {
		goToMoreOptionsForSections();
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 20, trash_Link);
		trash_Link.click();
	}

}
