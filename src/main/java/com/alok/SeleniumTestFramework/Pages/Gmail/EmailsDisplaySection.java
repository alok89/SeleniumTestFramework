package com.alok.SeleniumTestFramework.Pages.Gmail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.alok.SeleniumTestFramework.Utility.WaitsForWebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class EmailsDisplaySection {
	
	private WebDriver driver;
	private ExtentTest testCaseLogger;
	private List<WebElement> numberOfEmailsFound_EmaiID;
	private List<WebElement> numberOfEmailsFound_Subject;
	
	public EmailsDisplaySection(WebDriver driver, ExtentTest testCaseLogger) {
		this.driver = driver;
		this.testCaseLogger = testCaseLogger;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(how = How.CSS, using = "div.BltHke.nH.oy8Mbf[role='main']>div.UI table tr")
	private List<WebElement> emailsList;
	
	private List<WebElement> findEmailByCss(String sendersEmailId) {
		By emailIdLocator = By.cssSelector("div.BltHke.nH.oy8Mbf[role='main']>div.UI table tr div.yW span[email='"+sendersEmailId+"']");
		List<WebElement> emails = WaitsForWebElement.findElementUsingWebDriverWait(driver, 10, ExpectedConditions.presenceOfAllElementsLocatedBy(emailIdLocator));
		return emails;
	}
	
	private List<WebElement> findEmailByXpath(String subject) {
		By subjectLocator = By.xpath("//div[contains(@class,'BltHke nH oy8Mbf') and @role='main']/div[contains(@class,'UI')]//table//tr//span[@class='bog']//span[contains(text(),'"+subject+"')]");
		List<WebElement> emails = WaitsForWebElement.findElementUsingWebDriverWait(driver, 10, ExpectedConditions.presenceOfAllElementsLocatedBy(subjectLocator));
		return emails;
	}
	
	public int numberOfEmailsPresentOnThePage() {
		testCaseLogger.log(Status.INFO, "Getting the total number of emails present on the page");
		return emailsList.size();
	}
	
	public boolean isEmailPresentUsingSendersEmailID(String sendersEmailId) {
		numberOfEmailsFound_EmaiID = findEmailByCss(sendersEmailId);
		if(numberOfEmailsFound_EmaiID.isEmpty())
			return false;
		else
			return true;
	}
	
	public boolean isEmailPresentUsingSubject(String subject) {
		numberOfEmailsFound_Subject = findEmailByXpath(subject);
		if(numberOfEmailsFound_Subject.isEmpty())
			return false;
		else
			return true;
	}
	
	public boolean whetherSameEmailIsPresentMoreThanOnce(String sendersEmailId, String subject) {
		boolean moreThanOneTime = false;
		boolean emailPresent_EmailId = isEmailPresentUsingSendersEmailID(sendersEmailId);
		boolean emailPresent_Subject = isEmailPresentUsingSubject(subject);
		if(emailPresent_EmailId & emailPresent_Subject) {
			if(numberOfEmailsFound_EmaiID.size() == numberOfEmailsFound_Subject.size()) {
				if(numberOfEmailsFound_EmaiID.size() > 1)
					moreThanOneTime = true;
			}
		}
		return moreThanOneTime;
	}
	
	public void openAnEmail(String sendersEmailId, String subject) {
		boolean presentMoreThanOnce = whetherSameEmailIsPresentMoreThanOnce(sendersEmailId, subject);
			if(presentMoreThanOnce == false) {
				List<WebElement> emailList = findEmailByCss(sendersEmailId);
				WebElement email = emailList.get(0);
				email.click();
				WaitsForWebElement.waitFor(3000);
			}else
				testCaseLogger.log(Status.WARNING, "Based on the details, More than one email is present. Hence cannot open the email.");
	}

}
