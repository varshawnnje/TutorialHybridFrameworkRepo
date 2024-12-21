package com.tutorials.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	// objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOption; 
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(xpath= "//input[@name='search']")
	private WebElement searchField;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Actions
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
}
	public LoginPage clickLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
		
	}
	public RegisterPage selectRegisterOption() {

		registerOption.click();
		return new RegisterPage(driver);
}
	public void clickOnSearchField(String productNameText) {
		searchField.sendKeys(productNameText);
	}
	public SearchPage clickOnSearchButton() {
		searchButton.click();
		return new SearchPage(driver);
	}
}