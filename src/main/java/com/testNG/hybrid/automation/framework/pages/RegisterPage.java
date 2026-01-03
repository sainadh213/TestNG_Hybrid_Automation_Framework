package com.testNG.hybrid.automation.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

	public RegisterPage(WebDriver driver) {
		super(driver);

	}

	private By firstNameFiled = By.id("input-firstname");
	private By lastNameFiled = By.id("input-lastname");
	private By emailFiled = By.id("input-email");
	private By telephoneFiled = By.id("input-telephone");
	private By passwordFiled = By.id("input-password");
	private By confirmPasswordFiled = By.id("input-confirm");
	private By privacyCheckbox = By.xpath("//input[@type='checkbox']");
	private By continueBtn = By.xpath("//input[@type='submit']");
	private By alertMessage = By.className("alert-dismissible");
	private By passwordMissmatchAlert = By.className("text-danger");
	private By accountCreationMsg = By.xpath("//h1[text()='Your Account Has Been Created!']");

	public void enterFirstname(String text) {
		type(firstNameFiled, text);

	}

	public void enterLastname(String text) {
		type(lastNameFiled, text);

	}

	public void enterEmail(String text) {
		type(emailFiled, text);

	}

	public void enterPhonenumber(String text) {
		type(telephoneFiled, text);
	}

	public void enterPassoword(String text) {
		type(passwordFiled, text);

	}

	public void enterConfirmPassword(String text) {
		type(confirmPasswordFiled, text);

	}

	public void clickOnPrivacyCheckbox() {
		click(privacyCheckbox);

	}

	public void clickOnContinueBtn() {
		click(continueBtn);

	}

	public String WarningMessageText() {
		return getText(alertMessage);

	}

	public Boolean passwordMismatchMessageIsDisplayed() {
		return isDisplayed(passwordMissmatchAlert);
	}

	public boolean accountCreationConfirmationIsDisplayed() {
		return isDisplayed(accountCreationMsg);
	}
}
