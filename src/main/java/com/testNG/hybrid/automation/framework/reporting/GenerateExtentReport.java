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
		//File extentFilePath=new File(System.getProperty("user.dir")+"\\ExtentReports\\ExtentReport.html");
		new File(System.getProperty("user.dir")
	            + File.separator + "ExtentReports").mkdirs();
		String extentFilePath = System.getProperty("user.dir")
                + File.separator + "ExtentReports"
                + File.separator + "ExtentReport.html";
		
		ExtentSparkReporter extentSpark=new ExtentSparkReporter(extentFilePath);
		
		extentSpark.config().setEncoding("utf-8");
		extentSpark.config().setTheme(Theme.STANDARD);
		extentSpark.config().setDocumentTitle("Test Report");
		extentSpark.config().setReportName("Extent Automation Repor");
		extentSpark.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");
		
		extentSpark.config().setOfflineMode(true);
		
		extent.attachReporter(extentSpark);
		
	
		FileUtil fu=new FileUtil();
		extent.setSystemInfo("URL", fu.readDataFromPropertiesFile("url"));
		extent.setSystemInfo("Browser",fu.readDataFromPropertiesFile("browser"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		return extent;
		
		
	}

}

