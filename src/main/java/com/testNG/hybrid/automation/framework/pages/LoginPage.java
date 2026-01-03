package com.testNG.hybrid.automation.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	private By emailField = By.id("input-email");
	private By passwordField = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By invaildCredetialsWarning = By.className("alert-dismissible");

	public void enterEmail(String email) {
		type(emailField, email);
	}

	public void enterPassword(String password) {
		type(passwordField, password);
	}

	public void clickOnLogin() {
		click(loginBtn);
	}

	public boolean invalidCredentailsWarningMessageIsDisplayed() {
		return isDisplayed(invaildCredetialsWarning);
	}

}
