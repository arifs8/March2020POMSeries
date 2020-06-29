 package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.TestListner.ExtentReportListener;
import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utills.Contants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


//@Listeners(ExtentReportListener.class)
@Epic("Epic - 1145 : Design login page with different features....... ")
@Story ("US - 101 Design Login Page......")
public class LoginPageTest extends BaseTest {

	@Test(priority = 2)
	@Description("verify Login page Tite Test.......")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginpageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page title is : "+ title);
		Assert.assertEquals(title, Contants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 1)
	@Description("verify SingUp Link.....")
	@Severity(SeverityLevel.CRITICAL)
	public void verifySignUpLink() {
		Assert.assertTrue(loginPage.verifySignUpLink(),"signup is not working");
	}
	
	@Test(priority = 3)
	@Description("Login Page Test....")
	@Severity(SeverityLevel.BLOCKER)
	public void LoginPageTest1() {
		loginPage.dologin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
}
