package com.alok.SeleniumTestFramework.ManageBrowserDriver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeBrowserDriverManager extends BrowserDriverManager {	

	private ChromeDriverService service;
	private static final String CHROMEDRIVER_EXECUTABLE_PATH = properties.getProperty("ChromeDriver");

	public ChromeBrowserDriverManager() {
	}

	@Override
	protected void initializeBrowserDriver() {
		if(service.isRunning()) {
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(false);
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
			System.out.println("Creating the session and Opening the Chrome Browser....");
			driver = new RemoteWebDriver(service.getUrl(), options);
			configureBrowser();
		}
	}

	@Override
	protected void startBrowserDriverServer() {
		System.out.println("Configuring the Chrome Browser Driver Server....");
		service = new ChromeDriverService.Builder().
				usingDriverExecutable(new File(CHROMEDRIVER_EXECUTABLE_PATH)).usingAnyFreePort().build();
		try {
			System.out.println("Starting the Chrome Browser Driver Server....");
			service.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void stopBrowserDriverServer() {
		if(service.isRunning()) {
			System.out.println("Shutting down the Chrome Browser Driver Server...");
			service.stop();
		}
	}

}
