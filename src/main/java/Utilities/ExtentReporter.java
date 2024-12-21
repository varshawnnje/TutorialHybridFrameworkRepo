package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
public static ExtentReports generateExtentReport() {
	
	ExtentReports extentReport = new ExtentReports();
	
	File extentReportFile =  new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
	ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
	
	sparkReporter.config().setTheme(Theme.DARK);
	sparkReporter.config().setReportName("Tutorials Test Automation Result");
	sparkReporter.config().setDocumentTitle("Automation Result");
	sparkReporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");
	
	extentReport.attachReporter(sparkReporter);
	
	Properties configProp = new Properties();
	File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorial\\qa\\config\\config.properties");
	
	try {
		FileInputStream fisConfigProp = new FileInputStream(configPropFile);
		configProp.load(fisConfigProp);
	} catch (Throwable e) {
		
		e.printStackTrace();
	}
	
	extentReport.setSystemInfo("Application URL",configProp.getProperty("url"));
	extentReport.setSystemInfo("Browser Name",configProp.getProperty("Browsername"));
	extentReport.setSystemInfo("Email", configProp.getProperty("ValidEmail"));
	extentReport.setSystemInfo("Password", configProp.getProperty("ValidPassword"));
	extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
	extentReport.setSystemInfo("UserName", System.getProperty("user.name"));
	extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
	
	return extentReport;
	
}
}
