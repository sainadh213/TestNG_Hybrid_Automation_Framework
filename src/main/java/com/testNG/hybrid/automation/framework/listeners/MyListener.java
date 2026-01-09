package com.testNG.hybrid.automation.framework.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.testNG.hybrid.automation.framework.base.BaseTest;
import com.testNG.hybrid.automation.framework.reporting.GenerateExtentReport;



public class MyListener implements ITestListener {
	ExtentReports extent;
	ExtentTest test;
	

	@Override
	public void onStart(ITestContext context) {
		
		
			try {
				extent = GenerateExtentReport.generateExtentReport();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}

	@Override
	public void onTestStart(ITestResult result) {
	
		test = extent.createTest(result.getName());
		//To print in extent reports
		test.log(Status.INFO, result.getName() + " Started execution");
		//To print in console
		System.out.println(result.getName() + " Started execution");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getName() + " got passed");
		System.out.println(result.getName() + " got passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable());
		System.out.println(result.getName() + " got failed");
		String path=null;
		try {
			   path = BaseTest.captureScreenshot(result.getName());
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		if (path != null) {
	        test.addScreenCaptureFromPath(path);
	    } else {
	        test.log(Status.INFO, "Screenshot not captured (driver was null)");
	    }
		}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable());
		System.out.println(result.getName() + " got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		String extentReportPath=System.getProperty("user.dir")+"\\ExtentReports\\ExtentReport.html";
		File fi=new File(extentReportPath);
		try {
			Desktop.getDesktop().browse(fi.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
