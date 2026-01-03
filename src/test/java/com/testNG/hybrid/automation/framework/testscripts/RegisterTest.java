package com.testNG.hybrid.automation.framework.testscripts;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.testNG.hybrid.automation.framework.base.BaseTest;
import com.testNG.hybrid.automation.framework.pages.HomePage;
import com.testNG.hybrid.automation.framework.pages.RegisterPage;
import com.testNG.hybrid.automation.framework.utils.FileUtil;



@Listeners(com.testNG.hybrid.automation.framework.listeners.MyListener.class)
public class RegisterTest extends BaseTest {
	HomePage hp;
	FileUtil fu;
	RegisterPage rp;

	@Test(dataProvider = "getDataFromExcel")
	public void registration(String firstname,String lastname,String emial,String telephone,String password,String ConfirmPassword) {
		hp = new HomePage(driver);
		rp=new RegisterPage(driver);
		hp.clickOnMyAccountDM();
		hp.clickOnRegiter();
		rp.enterFirstname(firstname);
		rp.enterLastname(lastname);
		rp.enterEmail(emial);
		rp.enterPhonenumber(telephone);
		rp.enterPassoword(password);
		rp.enterConfirmPassword(password);
		rp.clickOnPrivacyCheckbox();
		rp.clickOnContinueBtn();
		Assert.assertTrue(rp.accountCreationConfirmationIsDisplayed());
		hp.clickOnMyAccountDM();
		hp.clickOnLogout();
	}
	@DataProvider(name="getDataFromExcel")
	public Object[][] registrationData() throws Exception
	{
		fu=new FileUtil();
		Object[][] data = fu.getDataFromExcelToDataProvider("Registration");
		return data;
	}

}
