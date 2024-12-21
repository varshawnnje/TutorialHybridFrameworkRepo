package com.tutorials.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorials.qa.base.Base;
import com.tutorials.qa.pages.AccountSuccessPage;
import com.tutorials.qa.pages.HomePage;
import com.tutorials.qa.pages.RegisterPage;

import Utilities.Utils;

public class RegistrationTest extends Base {
	
	RegisterPage registerpage;
	AccountSuccessPage accountsSuccessPage;
	
	public WebDriver driver;
	
	public RegistrationTest() {
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
	    registerpage = homePage.selectRegisterOption();
		
	}
		
	
	@Test(priority=1)
	public void  RegisterwithMandatoryfields() {
		
		registerpage.enterFirstName(testdata.getProperty("Firstname"));
		registerpage.enterLastName(testdata.getProperty("Lastname"));
		registerpage.enterEmail(Utils.GenerateEmailWithTimeStamp());
		registerpage.enterTelephone(testdata.getProperty("TelephoneNumber"));
		registerpage.enterpassword(testdata.getProperty("PasswordRegistration"));
		registerpage.enterConfirmPassword(testdata.getProperty("PasswordRegistration"));
		registerpage.selectPrivacyPolicy();
		accountsSuccessPage = registerpage.clickContinueButton();
		
		String Actual_Heading = accountsSuccessPage.retriveAccountSuccessPage();
		Assert.assertEquals(Actual_Heading, testdata.getProperty("AccountSuccessfullyCreatedMsg"),"Account success page is not displayed");
	  
	    
	}
	   @Test(priority=2)
       public void  RegisterwithAlreadyRegisterAccount() {
		
			registerpage.enterFirstName(testdata.getProperty("Firstname"));
			registerpage.enterLastName(testdata.getProperty("Lastname"));
			registerpage.enterEmail(testdata.getProperty("RegisteredEmail"));
			registerpage.enterTelephone(testdata.getProperty("TelephoneNumber"));
			registerpage.enterpassword(testdata.getProperty("PasswordRegistration"));
			registerpage.enterConfirmPassword(testdata.getProperty("PasswordRegistration"));
			registerpage.selectPrivacyPolicy();
			accountsSuccessPage = registerpage.clickContinueButton();
		
		String ActualWarning = registerpage.retriveDuplicateEmailAddressWarning();
		
		Assert.assertTrue(ActualWarning.contains(testdata.getProperty("DuplicateEmailWarning")));
		
	}
	   @Test(priority=3)
	   public void  VerifyRegisterAccountwithoutFillinggAnyDetails() {
			
		   registerpage.clickContinueButton();
			 
		   
			String ActualPricyWarning = registerpage.retrivePrivacyPolicyWarning();
			Assert.assertTrue(ActualPricyWarning.contains(testdata.getProperty("PrivacyWarningMsg")),"Actual warning msg is not displayed");
			
			String Firstnamewarning= registerpage.retriveFirstnameWarning();
			Assert.assertEquals(Firstnamewarning, testdata.getProperty("FirstNameWarningMsg"),"FirstName Warning msg is not displayed");
			
			String Lastnamewarning= registerpage.retriveLastNameWarning();
			Assert.assertEquals(Lastnamewarning, testdata.getProperty("LastNameWarningMsg"),"LastName Warning msg is not displayed");
			
			String Emailwarning= registerpage.retriveEmailWarning();
			Assert.assertEquals(Emailwarning, testdata.getProperty("EmailWarningMsg"),"Email Warning msg is not displayed");
			
			String Telephonewarning= registerpage.retriveTelephoneWarning();
			Assert.assertEquals(Telephonewarning, testdata.getProperty("TelephoneWarningMsg"),"Telephone Warning msg is not displayed");
			
			String Passwordwarning= registerpage.retrivePasswordWarning();
			Assert.assertEquals(Passwordwarning, testdata.getProperty("PasswordWarningMsg"),"Password Warning msg is not displayed");
						
	   }	   
}
