package com.tutorials.qa.Listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utilities.ExtentReporter;
import Utilities.Utils;

public class MyListeners implements ITestListener {
	
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
     extentReport = ExtentReporter.generateExtentReport();

	}

	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getName();
		extentTest = extentReport.createTest(testname);
		extentTest.log(Status.INFO, testname+"Started Executing");	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getName();
		extentTest=extentReport.createTest(testname);
		extentTest.log(Status.PASS, testname+"got successfully Executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getName();
		
		// code to get webdriver
		WebDriver driver = null;
		try {
		     driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		     
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			e.printStackTrace();
		}
		
		String destinationScreenshotPath = Utils.captureScreenshots(driver, result.getName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testname+"Test Got Failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		String testname = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testname+"Test got Skipped");
		
	}


	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
