package com.testNG.hybrid.automation.framework.reporting;

import java.io.File;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.testNG.hybrid.automation.framework.utils.FileUtil;


public class GenerateExtentReport {
	
	public static ExtentReports generateExtentReport() throws Throwable
	{
		ExtentReports extent=new ExtentReports();
		File extentFile=new File(System.getProperty("user.dir")+"\\ExtentReports\\ExtentReport.html");
		ExtentSparkReporter extentSpark=new ExtentSparkReporter(extentFile);
		
		extentSpark.config().setTheme(Theme.STANDARD);
		extentSpark.config().setDocumentTitle("Test Report");
		extentSpark.config().setReportName("ExtentReport");
		extentSpark.config().setTimeStampFormat("dd-MM-yyyy hh:mm:ss");
		
		extent.attachReporter(extentSpark);
		
	
		FileUtil fu=new FileUtil();
		extent.setSystemInfo("URL", fu.readDataFromPropertiesFile("url"));
		extent.setSystemInfo("Browser",fu.readDataFromPropertiesFile("browser"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		return extent;
		
		
	}

}

