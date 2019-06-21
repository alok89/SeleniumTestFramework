package com.alok.SeleniumTestFramework.ManageBrowserDriver;

public enum BrowserDriverManagerType {
	
	CHROME("Google Chrome"),
	FIREFOX("Mozilla Firefox"),
	IE("Microsoft Internet Explorer");
	//EDGE("Microsoft Edge");
	
	private String name;
	
	BrowserDriverManagerType(String name) {
		this.name = name;
	}
	
	public String getBrowserName() {
		return name;
	}

}
