package com.alok.SeleniumTestFramework.ManageBrowserDriver;

public class BrowserDriverManagerFactory {

	public static BrowserDriverManager getBrowserDriverManager(BrowserDriverManagerType browserDriverManagerType) {		
		BrowserDriverManager manager = null;

		switch(browserDriverManagerType) {
		case CHROME :
			manager = new ChromeBrowserDriverManager();
			break;

		case FIREFOX :
			manager = new FirefoxBrowserDriverManager();
			break;

		case IE :
			manager = new IEBrowserDriverManager();
			break;
		}
		return manager;
	}

}
