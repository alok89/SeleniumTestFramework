package com.alok.SeleniumTestFramework.Utility;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	private static final String SCREENSHOT_PATH = System.getProperty("user.dir")+"\\Screenshots\\";

	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File source = takeScreenshot.getScreenshotAs(OutputType.FILE);
		String fullScreenshotPath = SCREENSHOT_PATH+getFullScreenshotName(screenshotName);
		File destination = new File(fullScreenshotPath);
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			System.out.println("Exception occurred while copying the screenshot file (i.e. from source to destination)>> "+e.getMessage());
		}
		return fullScreenshotPath;
	}
	
	private static String getFullScreenshotName(String screenshotName) {
		LocalDateTime localDateTime = LocalDateTime.now();
		String formattedDateTime = localDateTime.format(DateTimeFormatter.ofPattern("dd_MM_YYYY_hh_mm_ss"));
		String fullScreenshotName = formattedDateTime+"_"+screenshotName;
		return fullScreenshotName;
	}

}
