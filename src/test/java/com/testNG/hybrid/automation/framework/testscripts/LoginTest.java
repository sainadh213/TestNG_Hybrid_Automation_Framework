package com.testNG.hybrid.automation.framework.testscripts;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.testNG.hybrid.automation.framework.base.BaseTest;
import com.testNG.hybrid.automation.framework.pages.HomePage;
import com.testNG.hybrid.automation.framework.pages.LoginPage;
import com.testNG.hybrid.automation.framework.utils.FileUtil;



//@Listeners(com.testNG.hybrid.automation.framework.listeners.MyListener.class)
public class LoginTest extends BaseTest {
	
	FileUtil fu;
	HomePage hp;
	LoginPage lp;

	@Test(priority = 0,groups= {"smoke","regression"})
	public void loginWithValidCredentials() {
		hp = new HomePage(getDriver());
		lp = new LoginPage(getDriver());
		hp.clickOnMyAccountDM();
	hp.clickOnLoign();
		//hp.clickOnSpecificButton("Login");
		lp.enterEmail("sainadh123@gmail.com");
		lp.enterPassword("sainadh@123");
		lp.clickOnLogin();
		Assert.assertTrue(hp.editYourAccountOptionIsDisplayed());
		hp.clickOnMyAccountDM();
		hp.clickOnLogout();
	}
	@Test(priority = 1,groups = {"smoke"})
	public void loginWithValidCredentialsUsingExcel() throws Throwable {
		hp = new HomePage(getDriver());
		lp = new LoginPage(getDriver());
		hp.clickOnMyAccountDM();
		hp.clickOnLoign();
		//hp.clickOnSpecificButton("Login");
		fu = new FileUtil();
		String username = fu.readDataFromExcel("ValidLoginCredentials", 1, 0);
		String password = fu.readDataFromExcel("ValidLoginCredentials", 1, 1);
		lp.enterEmail(username);
		lp.enterPassword(password);
		lp.clickOnLogin();
		Assert.assertTrue(hp.editYourAccountOptionIsDisplayed());
		hp.clickOnMyAccountDM();
		hp.clickOnLogout();
	}
	@Test(dataProvider = "validCredentialsData", priority = 2,groups= {"regression"})
	public void validLoginWIthDataProvider(String username, String password) {
		hp = new HomePage(getDriver());
		lp = new LoginPage(getDriver());
		hp.clickOnMyAccountDM();
		hp.clickOnLoign();
		//hp.clickOnSpecificButton("Login");
		lp.enterEmail(username);
		lp.enterPassword(password);
		lp.clickOnLogin();
		Assert.assertTrue(hp.editYourAccountOptionIsDisplayed());
		hp.clickOnMyAccountDM();
		hp.clickOnLogout();
	}

	@DataProvider(name = "validCredentialsData")
	public Object[][] supplyData() throws Throwable {
		fu = new FileUtil();
		Object[][] data = fu.getDataFromExcelToDataProvider("ValidLoginCredentials");
		return data;
	}
	@Test(dataProvider = "invalidCredentialsData",priority = 3,groups= {"smoke"})
	public void invalidCredentials(String username,String password)
	{
		hp = new HomePage(getDriver());
		lp = new LoginPage(getDriver());
		hp.clickOnMyAccountDM();
		hp.clickOnLoign();
		//hp.clickOnSpecificButton("Login");
		lp.enterEmail(username);
		lp.enterPassword(password);
		lp.clickOnLogin();
		Assert.assertTrue(lp.invalidCredentailsWarningMessageIsDisplayed());
	}
	@DataProvider(name="invalidCredentialsData")
	public Object[][] gerDataFromExcel() throws Exception {
		fu=new FileUtil();
		Object[][] data = fu.getDataFromExcelToDataProvider("InValidLoginCredentials");
		return data;
		
	}

	

}
