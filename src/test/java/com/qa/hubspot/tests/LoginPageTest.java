 package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utills.Contants;

public class LoginPageTest  {
	WebDriver driver;
	
	BasePage basePage;
	LoginPage loginPage;
	Properties prop;
	
	@BeforeTest
	
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
	}
	
	
	@Test
	
	public void verifyLoginpageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page title is : "+ title);
		Assert.assertEquals(title, Contants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	
	public void verifySingUpLink() {
		Assert.assertTrue(loginPage.verifySignUpLink(),  "signup is not working"   );
	}
	
	@Test
	public void LoginPageTest1() {
		loginPage.dologin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	
	@AfterTest
	
	public void tearDown() {
		driver.quit();
	}
	
	

}
