package com.testNG.hybrid.automation.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	private By myAccountDM = By.xpath("//span[text()='My Account']");
	//private By loginBtn = By.xpath("//a[text()='Login']");
	private By loginBtn =By.linkText("Login");
	//private String loginBtn="%s";
	
	
	private By registerBtn = By.linkText("Register");
	private By logoutBtn = By.xpath("//a[text()='Logout']");
	private By editYourAccountLink = By.linkText("Edit your account information");

	public void clickOnMyAccountDM() {
		click(myAccountDM);
	}

	public void clickOnLoign() {
		click(loginBtn);
	}
	
	/*public void clickOnSpecificButton(String buttonName)
	{
		By locator = findElementDynamically("linkText", loginBtn, buttonName);
		driver.findElement(locator).click();
	}*/
	public void clickOnLogout()
	{
		click(logoutBtn);
	}
	public void clickOnRegiter()
	{
		click(registerBtn);
	}

	public boolean editYourAccountOptionIsDisplayed() {
		return isDisplayed(editYourAccountLink);
	}

}
