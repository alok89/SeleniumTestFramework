package com.alok.SeleniumTestFramework.Pages.Gmail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.alok.SeleniumTestFramework.Pages.BasePage;
import com.alok.SeleniumTestFramework.Utility.WaitsForWebElement;
import com.aventstack.extentreports.ExtentTest;

public class HomePage extends BasePage {

	public ComposeEmailSection composeEmailSection;
	public SearchEmailSection searchEmailSection;
	private HomePage_SectionsFragment differentSections;
	private HomePage_HeaderFragment headerOptions;
	private HomePage_OptionsFragment optionsForEmails;

	public HomePage(WebDriver driver, ExtentTest testCaseLogger) {
		super(driver, testCaseLogger);
		PageFactory.initElements(super.driver, this);
		differentSections = new HomePage_SectionsFragment(super.driver, super.testCaseLogger);
		headerOptions = new HomePage_HeaderFragment(super.driver, super.testCaseLogger, super.actions);
		optionsForEmails = new HomePage_OptionsFragment(super.driver, super.testCaseLogger);
	}

	private static final String HOMEPAGE_TITLE = "jogendrasingh.sabran@gmail.com - Gmail";

	@FindBy(how = How.CSS, using = "div.ae4.UI.UJ div.oZ-jc.T-Jo.J-J5-Ji")
	private List<WebElement> email_Checkboxes;

	@FindBy(how = How.CSS, using = "div[gh='tm'] div[title='Delete']>div")
	private WebElement commonDelete_Button;

	@FindBy(how = How.XPATH, using = "//div[@class='Bn'][contains(text(),'Delete forever')]")
	private WebElement deleteForeverText;

	@FindBy(how = How.CSS, using = "span.bAq")
	private WebElement mailsMovedNotification;

	@FindBy(how = How.CSS, using = "div.bBe")
	private WebElement mailsMovedNotificationCloseIcon;

	@FindBy(how = How.CSS, using = "span.bAq")
	private WebElement emailSentNotification;

	@Override
	public boolean isAt() {
		return wait.until(ExpectedConditions.titleContains(HOMEPAGE_TITLE));
	}

	public ComposeEmailSection goToComposeEmailSectionUsingComposeButton() {
		composeEmailSection = differentSections.openComposeEmailMessageSection();
		return composeEmailSection;
	}
	
	public ComposeEmailSection goToComposeEmailSectionUsingDraftsSection(String subject) {
		goToDraftsSection();
		openAnEmailUsingSubject(subject);
		composeEmailSection = new ComposeEmailSection(driver, testCaseLogger);
		return composeEmailSection;
	}

	public void goToSentEmailsSection() {
		differentSections.openSentEmailsSection();
	}

	public void goToUserProfileOptions() {
		headerOptions.openUserProfileOptions();
	}

	public void signOut() {
		headerOptions.clickSignOut();
	}
	
	public SearchEmailSection goToAdvanceSearchOptions() {
		searchEmailSection = headerOptions.searchEmailUsingAdvanceOptions();
		return searchEmailSection;
	}

	public boolean verifyLogOutPageTitle() {
		sleepTime(3000);
		return wait.until(ExpectedConditions.titleContains("Gmail"));
	}
	
	public void selectAllCurrentlyDisplayedEmails() {
		optionsForEmails.selectAllDisplayedEmails();
	}
	
	public void selectOnlyThoseEmailsMatchingTheOption(String option) {
		optionsForEmails.selectEmailsBasedOnOptions(option);
	}
	
	public void goToTrashSection() {
		differentSections.openTrashSection();
	}
	
	public void deleteAllSelectedEmailsAndMoveToTrash() {
		optionsForEmails.deleteAllSelectedEmails();
	}
	
	public void goToDraftsSection() {
		differentSections.openDraftsSection();
		WaitsForWebElement.waitFor(3000);
	}

	/*public boolean openTheSentEMail(String subject) {
		WebElement sentEmail = getSentEmailObject(subject);
		if(subject.equals(sentEmail.getText())) {
			sentEmail.click();
			return true;
		}else
			return false;
	}*/
	
	//(//div[@class='aeF']//div[@role='link']//span[contains(text(),'Draft Email')])[2]
	public boolean openAnEmailUsingSubject(String subject) {
		By locator = By.xpath("(//div[@class='aeF']//div[@role='link']//span[contains(text(),'"+subject+"')])[2]");
		WebElement email = WaitsForWebElement.findElementUsingWebDriverWait(driver, 15, locator);
		if(email.isDisplayed()) {
			email.click();
			return true;
		}else
			return false;
	}
	
	public boolean openAnEmailUsingSendersNameAndEmailID(String name, String emailID) {
		By locator = By.cssSelector("div.yW span[email='"+emailID+"'][name='"+name+"']");
		WebElement email = wait.until(ExpectedConditions.elementToBeClickable(locator));
		if(email.isDisplayed()) {
			email.click();
			return true;
		}else
			return false;
	}

	/*private WebElement getSentEmailObject(String subject) {
		By locator = By.xpath("//span[@class='bog']/span[text()='"+subject+"']");
		WebElement sentEmail = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return sentEmail;
	}*/

	public boolean isEmailSentNotificationDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(emailSentNotification));
		return wait.until(ExpectedConditions.textToBePresentInElement(emailSentNotification, "Message sent."));
	}

	public void selectEmailsToDelete(int numberOfMails) {
		if(email_Checkboxes.size() >= 0) {
			for(int i=0; i<numberOfMails; i++) {
				WebElement emailCheckboxToSelect = email_Checkboxes.get(i);
				emailCheckboxToSelect.click();
			}
		}else {
			Assert.fail("No Email is selected");
		}
	}

	public boolean areEmailsMovedToTrash(String numberofSelectedEmails) {
		boolean isMessageDisplayed = wait.until(ExpectedConditions.
				textToBePresentInElement(mailsMovedNotification, numberofSelectedEmails+" conversations moved to Trash."));
		mailsMovedNotificationCloseIcon.click();
		return isMessageDisplayed;
	}

	public void deleteAllSelectedEmailsForever() {
		By locator = By.cssSelector("div[gh='tm'] div[data-tooltip='Delete']>div");
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		driver.findElement(locator).click();
		sleepTime(3000);
	}

}
