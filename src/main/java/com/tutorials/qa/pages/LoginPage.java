package com.tutorials.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
@FindBy(xpath="//input[@name='email']")
private WebElement emailAddressField;

@FindBy(xpath="//input[@name='password']")
private WebElement passwordField;

@FindBy(xpath="//input[@type='submit']")
private WebElement loginButton;

@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
private WebElement  emailPasswordNotMatchingWarning;

public LoginPage(WebDriver driver) {
	this.driver =driver;
	PageFactory.initElements(driver, this);
	
}

public void enterEmailAddress(String emailText) {
	emailAddressField.sendKeys(emailText);
}

public void enterPassword(String passwordText) {
	passwordField.sendKeys(passwordText);
}

public AccountPage clickOnLoginButton() {
	loginButton.click();
	return new AccountPage(driver);
}

public String retriveemailPasswordNotMatchingWarningMessageText() {
String warningText = emailPasswordNotMatchingWarning.getText();
return warningText;
}
}
