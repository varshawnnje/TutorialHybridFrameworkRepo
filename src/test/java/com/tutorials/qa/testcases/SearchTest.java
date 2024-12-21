package com.tutorials.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorials.qa.base.Base;
import com.tutorials.qa.pages.HomePage;
import com.tutorials.qa.pages.SearchPage;

   public class SearchTest extends Base {
	  
	   SearchPage searchPage;
	   
	   public WebDriver driver;
	    
    public SearchTest() {
    	super();
    }
	
	@AfterMethod
	public void teardown(){
		driver.quit();
		
	}
	
	@BeforeMethod
	public void Setup() {

		driver = inializeBrowserAndOpenApplication(prop.getProperty("Browsername"));
		
	}
	@Test(priority=1)
	public void searchWithValidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.clickOnSearchField(testdata.getProperty("ProductName"));
		searchPage = homePage.clickOnSearchButton();
		Assert.assertTrue(searchPage.displayStatusOfValidHpProduct());
	
	}
	@Test(priority=2)
	public void searchWithINvalidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.clickOnSearchField(testdata.getProperty("InvalidProductName"));
		searchPage  = homePage.clickOnSearchButton();
		
		String ActualMsg = searchPage.retriveInvalidProductWarning();
		//Assert.assertEquals(ActualMsg, testdata.getProperty("InvalidProductWarning"),"warning msg is not displayed");
		Assert.assertEquals(ActualMsg, "abc","warning msg is not displayed");
	
	}
	@Test(priority=3, dependsOnMethods= {"searchWithINvalidProduct"})
	public void searchWithoutAddingAnyproductProduct() {
		
		HomePage homePage = new HomePage(driver);
		searchPage = homePage.clickOnSearchButton();
		
		String ActualMsg = searchPage.retriveInvalidProductWarning();
		Assert.assertEquals(ActualMsg, testdata.getProperty("InvalidProductWarning"),"warning msg is not displayed");
	}

}
