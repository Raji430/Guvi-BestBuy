package com.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportBestBuy	
{	
	public static ExtentReports getReport()
	{		
		//Create Report
		String path= System.getProperty("user.dir")+ "\\BestBuyTestReport.html";			
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("BestBuyTest Report");
		
		//Add automation data to physical report
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);		
		return extent;
	}
	
}
