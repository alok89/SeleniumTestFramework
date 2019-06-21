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

public class TestScenario2 extends BaseTest {
	
	private HomePage homePage;
	private EmailIDPage emailIDPage;
	private PasswordPage passwordPage;
 
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
	
	@Test(priority = 2, description = "To test search email functionality", dependsOnMethods = "launchGmailAndLogin")
	@Parameters({"SearchFromEmailId", "SearchFromSubject"})
	public void searchEmailsBasedOnCriteria(String searchFromEmailId, String searchFromSubject) {
		homePage.searchEmailSection = homePage.goToAdvanceSearchOptions();
		homePage.searchEmailSection.enterTheFromUserEmail(searchFromEmailId);
		homePage.searchEmailSection.enterTheSubject(searchFromSubject);
		homePage.searchEmailSection.clickTheDateWithinAreaAndSelectDateValue();
		homePage.searchEmailSection.clickSearchButton();
	}
	
	@Test(priority = 3, description = "To test emails are getting selected and then moving them to trash", 
			dependsOnMethods = "searchEmailsBasedOnCriteria")
	@Parameters("NoOfEmailsToBeDeleted")
	public void selectEmailsAndMoveThemToTrash(String noOfEmailsToBeDeleted) {
		homePage.selectEmailsToDelete(Integer.parseInt(noOfEmailsToBeDeleted));
		homePage.deleteAllSelectedEmailsAndMoveToTrash();
		Assert.assertTrue(homePage.areEmailsMovedToTrash(noOfEmailsToBeDeleted));
	}
	
	@Test(priority = 4, description = "Navigate to trash section emails and then remove selected emails forever", 
			dependsOnMethods = "selectEmailsAndMoveThemToTrash")
	@Parameters({"SearchFromSubject","EmailSelectionOption"})
	public void navigateToTrashSectionAndRemoveEmails(String searchFromSubject, String emailSelectionOption) {
		homePage.goToTrashSection();
		homePage.searchEmailSection = homePage.goToAdvanceSearchOptions();
		homePage.searchEmailSection.enterTheSubject(searchFromSubject);
		homePage.searchEmailSection.clickSearchButton();
		homePage.selectOnlyThoseEmailsMatchingTheOption(emailSelectionOption);
		homePage.deleteAllSelectedEmailsForever();
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
