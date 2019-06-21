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

public class TestScenario3 extends BaseTest {
	
	private EmailIDPage emailIDPage;
	private PasswordPage passwordPage;
	private HomePage homePage;

	@BeforeClass
	public void scenarioPrerequisites(XmlTest testClass) {
		testClassLogger = testLogger.createNode(testClass.getXmlClasses().get(0).getSupportClass().getSimpleName());
	}
	
	@Test(priority = 1, description = "Launching Gmail application and then login")
	public void launchGmailAndLogin() {
		emailIDPage = new EmailIDPage(driver, testCaseLogger);
		emailIDPage.hitContextUrlAndGoToEmailIdPage(gmailContext);
		passwordPage = emailIDPage.enterEmailIdAndGoToPasswordPage(username);
		homePage = passwordPage.enterPasswordAndGoToHomePage(password);
		Assert.assertTrue(homePage.isAt());
	}
	
	@Test(priority = 2, description = "Composing an email without saving it", dependsOnMethods = "launchGmailAndLogin")
	@Parameters({"RecepientEmailID", "CCEmailID", "Subject", "Body"})
	public void composeAnEmailWithoutSavingIt(String rEmailId, String ccEmailId, String subject, String body) {
		homePage.composeEmailSection = homePage.goToComposeEmailSectionUsingComposeButton();
		if(homePage.composeEmailSection.isComposeMessageSectionOpen()) {
			homePage.composeEmailSection.enterTheUserEmailID(rEmailId);
			homePage.composeEmailSection.enterTheCCEmailId(ccEmailId);
			homePage.composeEmailSection.enterTheSubject(subject);
			homePage.composeEmailSection.enterTheMessageBody(body);
			homePage.composeEmailSection.closeTheComposeMessageSection();
		}
		Assert.assertFalse(homePage.composeEmailSection.isComposeMessageSectionOpen());

	}
	
	@Test(priority = 3, description = "Searching the composed email in Drafts section and then opening it", dependsOnMethods = "composeAnEmailWithoutSavingIt")
	@Parameters("Subject")
	public void searchInDraftsAndOpentheComposedEmail(String subject) {
		homePage.goToDraftsSection();
		homePage.searchEmailSection = homePage.goToAdvanceSearchOptions();
		homePage.searchEmailSection.enterTheSubject(subject);
		homePage.searchEmailSection.clickSearchButton();
		boolean isEmailFound = homePage.openAnEmailUsingSubject(subject);
		Assert.assertTrue(isEmailFound);
	}
	
	@Test(priority = 4, description = "Modifyng the email by uploading a file and then sending it", 
			dependsOnMethods = "searchInDraftsAndOpentheComposedEmail")
	@Parameters({"Subject", "FilePath"})
	public void modifyTheEmailByUploadingFileAndSend(String subject, String filePath) {
		if(homePage.composeEmailSection.isComposeMessageSectionOpen()) {
			homePage.composeEmailSection.uploadFile(filePath);
			homePage.composeEmailSection.clickSendButton();
			Assert.assertTrue(homePage.isEmailSentNotificationDisplayed());
		}else
			Assert.fail("No Compose Message Section found");	
	}
	
	@Test(priority = 5, description = "Logging out from Gamil", dependsOnMethods = "launchGmailAndLogin")
	public void logout() {
		homePage.goToUserProfileOptions();
		homePage.signOut();
		Assert.assertTrue(homePage.verifyLogOutPageTitle());
	}
	
	@AfterClass
	public void closeScenarioExecution() {		
		browserDriverManager.closeBrowser();
	}
}
