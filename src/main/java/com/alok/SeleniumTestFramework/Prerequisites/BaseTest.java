package com.alok.SeleniumTestFramework.Prerequisites;

import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.alok.SeleniumTestFramework.ManageBrowserDriver.BrowserDriverManager;
import com.alok.SeleniumTestFramework.ManageBrowserDriver.BrowserDriverManagerFactory;
import com.alok.SeleniumTestFramework.ManageBrowserDriver.BrowserDriverManagerType;
import com.alok.SeleniumTestFramework.Reporting.ExtentReportBuilderManager;
import com.alok.SeleniumTestFramework.Utility.LoadConfigProperties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseTest {

	protected String browserName;
	protected String gmailContext;
	protected String username;
	protected String password;
	protected BrowserDriverManager browserDriverManager;
	protected WebDriver driver;
	
	public static Properties properties;
	protected static ExtentReports reports;
	protected static ExtentTest testLogger;
	protected static ExtentTest testClassLogger;
	protected static ExtentTest testCaseLogger;
	protected static WebDriver staticDriver;

	private static final String REPORT_PATH = System.getProperty("user.dir")+"//Reports//";
	private static final String REPORT_NAME = "TestSummaryReport.html";

	@BeforeSuite
	public void projectPreRequisites() {
		System.out.println("Setting up project pre-requisites");
		properties = LoadConfigProperties.getPropertiesInstance();
		reports = ExtentReportBuilderManager.getExtentReportsInstance(REPORT_PATH+REPORT_NAME);
	}

	@BeforeTest
	public void testScenarioSetUp(ITestContext testContext) {
		System.out.println("Setting up the scenario pre-requisites");
		browserName = properties.getProperty("Browser");
		gmailContext = properties.getProperty("GmailContext");
		username = properties.getProperty("UserName");
		password = properties.getProperty("Password");
		testLogger = reports.createTest(testContext.getName());
		browserDriverManager = BrowserDriverManagerFactory.getBrowserDriverManager(BrowserDriverManagerType.valueOf(browserName));
		driver = browserDriverManager.startTheBrowserDriverServerAndGetTheBrowserDriver();
		staticDriver = driver;
	}

	@BeforeMethod
	public void testCaseSetup(Method testMethod) {
		testCaseLogger = testClassLogger.createNode(testMethod.getName());
	}

	@AfterTest
	public void tearDown() {
		browserDriverManager.terminateBrowserDriverSessionAndShutDownTheServer();
	}

	@AfterSuite
	public void closeTheProjectExecution() {
		reports.flush();
	}
}
