package com.tutorials.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utilities.Utils;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties testdata;
	
	public Base(){
		
		testdata = new Properties();
		File testfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorial\\qa\\testdata\\testdata.properties");
		
		try {
		FileInputStream tdatafile = new FileInputStream(testfile);
		testdata.load(tdatafile);
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		 prop = new Properties();
		 File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorial\\qa\\config\\config.properties");
	
		 try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
			
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		 
	}
	
	public WebDriver inializeBrowserAndOpenApplication(String BrowserName) {
		
		if(BrowserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			}
		else if(BrowserName.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
		}
		else if(BrowserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

}
