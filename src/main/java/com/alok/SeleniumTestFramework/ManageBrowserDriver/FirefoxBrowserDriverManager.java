package com.alok.SeleniumTestFramework.ManageBrowserDriver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxBrowserDriverManager extends BrowserDriverManager {

	private GeckoDriverService service;
	private static final String GECKODRIVER_EXECUTABLE_PATH = properties.getProperty("FirefoxDriver");

	@Override
	protected void initializeBrowserDriver() {
		if(service.isRunning()) {
			FirefoxOptions options = new FirefoxOptions();
			options.setAcceptInsecureCerts(false);
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
			System.out.println("Creating the session and Opening the Firefox Browser....");
			driver = new RemoteWebDriver(service.getUrl(), options);
			configureBrowser();
		}
	}

	@Override
	protected void startBrowserDriverServer() {
		System.out.println("Configuring the Firefox Browser Driver Server....");
		service = new GeckoDriverService.Builder().
				usingDriverExecutable(new File(GECKODRIVER_EXECUTABLE_PATH)).usingAnyFreePort().build();
		try {
			System.out.println("Starting the Firefox Browser Driver Server....");
			service.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void stopBrowserDriverServer() {
		if(service.isRunning()) {
			System.out.println("Shutting down the Firefox Browser Driver Server...");
			service.stop();
		}
	}

}
