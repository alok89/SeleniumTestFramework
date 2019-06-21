package com.alok.SeleniumTestFramework.Listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.alok.SeleniumTestFramework.Prerequisites.BaseTest;
import com.alok.SeleniumTestFramework.Utility.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TestListener extends BaseTest implements ITestListener {

	@Override
	public void onStart(ITestContext context) { 
		System.out.println("Started execution of "+context.getName());
	}

	@Override
	public void onTestStart(ITestResult result) {
		testCaseLogger.log(Status.INFO, result.getMethod().getMethodName()+" execution starts...");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		testCaseLogger.log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName()+" Passed.", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String screenshotPath = null;
		try {
			screenshotPath = Screenshot.captureScreenshot(staticDriver, result.getMethod().getMethodName()+".jpg");
			testCaseLogger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} catch(IOException ex) {
			System.out.println("Exception occurred while creating screenshot >> "+ex.getMessage());
		}
		testCaseLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName()+" Failed.", ExtentColor.RED));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		testCaseLogger.skip(result.getThrowable());
		testCaseLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getMethodName()+" Skipped.", ExtentColor.ORANGE));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

	@Override
	public void onFinish(ITestContext context) { 
		System.out.println("Ended execution of "+context.getName());
	}

}
