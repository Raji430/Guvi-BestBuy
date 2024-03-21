package com.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.*;
import com.aventstack.extentreports.*;

public class Listner extends Utility implements ITestListener
{
	ExtentReports extent= ExtentReportBestBuy.getReport();
	ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result) {
		
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test passed");
		//captureScreenShot(result, "pass");
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		test.fail(result.getThrowable());
		
		//captureScreenShot(result, "fail");
		
		String screenshotPath = System.getProperty("user.dir")+ "\\Screenshot.png";
		
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 try 
		 {
	        FileUtils.copyFile(screenshotFile, new File(screenshotPath));
	     } 
		 catch (IOException e) 
		 {
	        System.out.println(e.getMessage());
	     }
		
		test.addScreenCaptureFromPath(screenshotPath,result.getMethod().getMethodName() ); 
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//<TBD>
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
	}
	
	public void captureScreenShot(ITestResult result, String status)
	{		  
		  String destDir = "";
		  String passfailMethod = result.getMethod().getMethodName();
		  
		  // To capture screenshot.
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		  
		  // If status = fail then set folder name "screenshots/Failures"
		  if (status.equalsIgnoreCase("fail")) {
		   destDir = "screenshots/Failures";
		  }
		  // If status = pass then set folder name "screenshots/Success"
		  else if (status.equalsIgnoreCase("pass")) {
		   destDir = "screenshots/Success";
		  }

		  // To create folder to store screenshots
		  new File(destDir).mkdirs();
		  // Set file name with combination of test class name + date time.
		  String destFile = passfailMethod + " - " + dateFormat.format(new Date()) + ".png";

		  try {
		   // Store file at destination folder location
			  FileHandler.copy(scrFile, new File(destDir + "/" + destFile));
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		 }

}
