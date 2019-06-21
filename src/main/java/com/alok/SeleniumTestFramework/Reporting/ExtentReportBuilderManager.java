package com.alok.SeleniumTestFramework.Reporting;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.ExtentHtmlReporterConfiguration;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportBuilderManager {

	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports reports = null;
	private static ExtentHtmlReporterConfiguration htmlReporterConfiguration;
	private static final String DOCUMENT_NAME = "Test Summary Report";
	private static final String REPORT_NAME = "UI AUTOMATION TEST RESULTS";

	private ExtentReportBuilderManager() { }

	public static ExtentReports getExtentReportsInstance(String filePath) {
		if(configureHTMLReport(filePath) & reports == null) {
			System.out.println("Initializing Extent Reports by attaching the HTML reporter");
			reports = new ExtentReports();
			reports.attachReporter(htmlReporter);
		}
		return reports;
	}

	private static boolean configureHTMLReport(String filePath) {
		boolean isReportConfigured = false;
		htmlReporterConfiguration = initializeHTMLReporter(filePath);
		if(htmlReporterConfiguration != null) {
			System.out.println("Configuring HTML Report");
			htmlReporterConfiguration.setChartVisibilityOnOpen(true);
			htmlReporterConfiguration.setDocumentTitle(DOCUMENT_NAME);
			htmlReporterConfiguration.setReportName(REPORT_NAME);
			htmlReporterConfiguration.setTestViewChartLocation(ChartLocation.TOP);
			htmlReporterConfiguration.setTheme(Theme.DARK);
			htmlReporterConfiguration.setEncoding("UTF-8");
			htmlReporterConfiguration.setLevel(Status.INFO);
			isReportConfigured = true;
		}
		return isReportConfigured;
	}

	private static ExtentHtmlReporterConfiguration initializeHTMLReporter(String filePath) {
		System.out.println("Initializing HTML Reporter");
		File file = new File(filePath);
		if(file.exists()) {
			file.delete();
		}
		htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporterConfiguration = htmlReporter.config();
		return htmlReporterConfiguration;
	}
}
