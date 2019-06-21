package com.alok.SeleniumTestFramework.Utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyboardActions {

	private static Robot robot;

	private static void initializeRobot() {
		try {
			robot = new Robot();
		}catch(AWTException ex) {
			ex.printStackTrace();
		}
	}

	public static void pasteValuesOnWindowPopups(String valuesToBePasted) {
		if(valuesToBePasted != null) {
			StringSelection text = new StringSelection(valuesToBePasted);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(text, null);
			initializeRobot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}else
			System.out.println("Cant accept null. Please provide some values to be copied and pasted to appropriate location.");
	}

	public static void openBrowserTabs(int numberOfTabs) {
		initializeRobot();
		while(numberOfTabs>0) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			numberOfTabs--;
		}
	}
}
