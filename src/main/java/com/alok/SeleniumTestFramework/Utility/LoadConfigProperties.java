package com.alok.SeleniumTestFramework.Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class LoadConfigProperties {

	private static final String CONFIG_FILE_PATH = System.getProperty("user.dir")+"\\configuration.properties";
	private static Properties properties;

	private LoadConfigProperties() {	
	}

	public static Properties getPropertiesInstance() {
		if(properties == null) {
			properties = new Properties();
			try(Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(CONFIG_FILE_PATH))))) {
				System.out.println("Loading the config file from the path : "+CONFIG_FILE_PATH+"\n");
				properties.load(reader);
			} catch (FileNotFoundException e) {
				System.out.println("Check the configuration file path.\n"+e.getMessage());
			} catch (IOException e) {
				System.out.println("Exception occurred while reading the file.\n");
				e.printStackTrace();
			}
		}
		return properties;
	}

}
