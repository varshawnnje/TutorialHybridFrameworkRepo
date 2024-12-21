package com.tutorials.qa.testcases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorials.qa.base.Base;
import com.tutorials.qa.pages.AccountPage;
import com.tutorials.qa.pages.HomePage;
import com.tutorials.qa.pages.LoginPage;

import Utilities.Utils;

import java.util.Date;


public class LoginTest extends Base {
	
	LoginPage loginPage;

	public WebDriver driver;

	public LoginTest() {
		super();
	}
	@AfterMethod
	public void teardown(){
		driver.quit();

	}

	@BeforeMethod
	public void Setup() {

		driver = inializeBrowserAndOpenApplication(prop.getProperty("Browsername"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
	    loginPage = homePage.clickLoginOption();
	}

	@Test(priority=1,dataProvider="loginData")
	public void VerifyLoginWithValidCredentials(String email, String password) {

		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		
		AccountPage accountPage = loginPage.clickOnLoginButton();
	
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your account information is displayed after login");

	}
	
	
	@DataProvider(name = "loginData")
	public Object[][] getLoginData() {
	    return Utils.getTestDataFromExcel();
	}


	@Test(priority=2)
	public void VerifyLoginWithInValidCredentials() {

		loginPage.enterEmailAddress(Utils.GenerateEmailWithTimeStamp());
		loginPage.enterPassword(testdata.getProperty("invalidpassword"));
		loginPage.clickOnLoginButton();
	
		String Actual_msg = loginPage.retriveemailPasswordNotMatchingWarningMessageText();
		String Expected_msg = testdata.getProperty("invalidEmailPasswordWarningMsg");
		Assert.assertTrue(Actual_msg.contains(Expected_msg), "Expected Warning message");


	}
	@Test(priority=3)
	public void VerifyLoginWithValidEmailInvalidPassword() {

		loginPage.enterEmailAddress(prop.getProperty("ValidEmail"));
		loginPage.enterPassword(testdata.getProperty("invalidpassword"));
	    loginPage.clickOnLoginButton();
		
		String Actual_msg = loginPage.retriveemailPasswordNotMatchingWarningMessageText();
		String Expected_msg = testdata.getProperty("invalidEmailPasswordWarningMsg");
		Assert.assertTrue(Actual_msg.contains(Expected_msg), "Expected Warning message");


	}
	@Test(priority=4)
	public void VerifyLoginWithInValidEmailandValidPassword() {

		loginPage.enterEmailAddress(Utils.GenerateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("ValidPassword"));
		loginPage.clickOnLoginButton();

		String Actual_msg = loginPage.retriveemailPasswordNotMatchingWarningMessageText();
		String Expected_msg = testdata.getProperty("invalidEmailPasswordWarningMsg");
		Assert.assertTrue(Actual_msg.contains(Expected_msg), "Expected Warning message");


	} 
	@Test(priority=5)
	public void VerifyLoginWithoutCredentials() {

		loginPage.clickOnLoginButton();
	
		String Actual_msg = loginPage.retriveemailPasswordNotMatchingWarningMessageText();
		String Expected_msg = testdata.getProperty("invalidEmailPasswordWarningMsg");
		Assert.assertTrue(Actual_msg.contains(Expected_msg), "Expected Warning message");

	}

}
