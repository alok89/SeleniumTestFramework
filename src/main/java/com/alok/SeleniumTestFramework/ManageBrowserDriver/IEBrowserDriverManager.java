package com.alok.SeleniumTestFramework.ManageBrowserDriver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IEBrowserDriverManager extends BrowserDriverManager {

	private InternetExplorerDriverService service;
	private static final String IEDRIVER_EXECUTABLE_PATH = properties.getProperty("IEDriver");

	@Override
	protected void initializeBrowserDriver() {
		if(service.isRunning()) {
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.ignoreZoomSettings();
			options.requireWindowFocus();
			options.destructivelyEnsureCleanSession();
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
			options.takeFullPageScreenshot();
			System.out.println("Creating the session and Opening the IE Browser....");
			driver = new RemoteWebDriver(service.getUrl(), options);
			configureBrowser();
		}
	}

	@Override
	protected void startBrowserDriverServer() {
		System.out.println("Configuring the IE Browser Driver Server....");
		service = new InternetExplorerDriverService.Builder().
				usingDriverExecutable(new File(IEDRIVER_EXECUTABLE_PATH)).usingAnyFreePort().build();
		try {
			System.out.println("Starting the IE Browser Driver Server....");
			service.start();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void stopBrowserDriverServer() {
		if(service.isRunning()) {
			System.out.println("Shutting down the IE Browser Driver Server...");
			service.stop();
		}
	}

}
