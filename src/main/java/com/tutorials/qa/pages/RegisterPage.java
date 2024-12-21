package com.tutorials.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	
	@FindBy(xpath="//input[@name='firstname']")
	private WebElement firstNameField;
	
	@FindBy(xpath="//input[@name='lastname']")
	private WebElement lastNameField;
	
	@FindBy(xpath="//input[@name='email']")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@name='telephone']")
	private WebElement telePhoneField;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement passwordField;
	
	
	@FindBy(xpath="//input[@name='confirm']")
	private WebElement confirmpasswordField;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continuButton;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String LastNameText) {
		lastNameField.sendKeys(LastNameText);
	}
	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);	
	}
	public void enterTelephone(String telephoneText) {
		telePhoneField.sendKeys(telephoneText);	
	}
	public void enterpassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	public void enterConfirmPassword(String confirmPasswordText) {
		confirmpasswordField.sendKeys(confirmPasswordText);
	}
	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}
	public AccountSuccessPage clickContinueButton() {
		continuButton.click();
		return new AccountSuccessPage(driver);
}
	public String retriveDuplicateEmailAddressWarning() {
		String duplicateEmailWarningText = duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}
	public String retrivePrivacyPolicyWarning() {
	String	privacyPolicyWarningText = privacyPolicyWarning.getText();
	return privacyPolicyWarningText;
	}
	public String retriveFirstnameWarning() {
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}
	public String retriveLastNameWarning() {
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}
	public String retriveEmailWarning() {
	String 	emailWarningText = emailWarning.getText();
	return emailWarningText;
	}
	public String retriveTelephoneWarning() {
	String 	telephoneWarningText = telephoneWarning.getText();
	return telephoneWarningText;
	}
	public String retrivePasswordWarning() {
		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
		}
}