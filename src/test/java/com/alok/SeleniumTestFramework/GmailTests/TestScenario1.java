package com.alok.SeleniumTestFramework.GmailTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.alok.SeleniumTestFramework.Pages.Gmail.EmailIDPage;
import com.alok.SeleniumTestFramework.Pages.Gmail.HomePage;
import com.alok.SeleniumTestFramework.Pages.Gmail.PasswordPage;
import com.alok.SeleniumTestFramework.Prerequisites.BaseTest;

public class TestScenario1 extends BaseTest {
	
	private EmailIDPage emailIDPage;
	private PasswordPage passwordPage;
	private HomePage homePage;
	
	@BeforeClass
	public void scenarioPrerequisites(XmlTest testClass) {
		testClassLogger = testLogger.createNode(testClass.getXmlClasses().get(0).getSupportClass().getSimpleName());
	}

	@Test(priority = 1, description = "To Test The Gmail Login")
	public void login() {
		emailIDPage = new EmailIDPage(driver, testCaseLogger);
		emailIDPage.hitContextUrlAndGoToEmailIdPage(gmailContext);
		passwordPage = emailIDPage.enterEmailIdAndGoToPasswordPage(username);
		homePage = passwordPage.enterPasswordAndGoToHomePage(password);
		Assert.assertTrue(homePage.isAt());
		System.out.println("1st TestCase executed");
	}
	
	@Test(priority = 2, description = "To Test The Compose Email Functionality", dependsOnMethods = "login")
	@Parameters({"RecepientEmailID", "Subject", "Body"})
	public void composeAndSendAnEmail(String emailId, String subject, String body) {
		homePage.composeEmailSection = homePage.goToComposeEmailSectionUsingComposeButton();
		if(homePage.composeEmailSection.isComposeMessageSectionOpen()) {
			homePage.composeEmailSection.enterTheUserEmailID(emailId);
			homePage.composeEmailSection.enterTheSubject(subject);
			homePage.composeEmailSection.enterTheMessageBody(body);
			homePage.composeEmailSection.clickSendButton();
			Assert.assertTrue(homePage.isEmailSentNotificationDisplayed());
		}else
			Assert.fail("No Compose Message Section found");
	}
	
	@Test(priority = 3, description = "To test that the Email has been sent", dependsOnMethods = "composeAndSendAnEmail")
	@Parameters("Subject")
	public void goToSentEmailsSectionAndVerifyTheEmail(String subject) {
		homePage.goToSentEmailsSection();
		homePage.searchEmailSection = homePage.goToAdvanceSearchOptions();
		String text = homePage.searchEmailSection.getTextOfSearchTextArea();
		if("Sent".equals(text))
			Assert.assertTrue(homePage.openAnEmailUsingSubject(subject));
		else
			Assert.fail("No Sent Email Section found");
	}
	
	@Test(priority = 4, description = "To test the Gmail Logout", dependsOnMethods = "login")
	public void logOut() {
		homePage.goToUserProfileOptions();
		homePage.signOut();
		Assert.assertTrue(homePage.verifyLogOutPageTitle());
	}
	
	@AfterClass
	public void closeScenarioExecution() {
		browserDriverManager.closeBrowser();
	}

}
