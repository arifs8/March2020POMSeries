package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utills.Contants;



public class HomePageTest {
	
	WebDriver driver;
	Properties prop;
	
	LoginPage loginPage;
	BasePage basePage;
	HomePage homePage;
	
	
	@BeforeTest
	
	public void setup() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.dologin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test (priority = 1)
	public void veirfyHomePageTitle() throws InterruptedException {
		Thread.sleep(7000);
		String title = homePage.getHomePageTitle();
		System.out.println("Home Page Title is : "+ title);
		Assert.assertEquals(title, Contants.HOME_PAGE_TITLE,"Home page title is not matched......");
	}
	
	
	@Test(priority = 2)
	public void verifyHomePageHeader() throws InterruptedException {
		Thread.sleep(5000);
		String header = homePage.getHomePageHeaderText();
		System.out.println("Home page header i : "+ header);
		Assert.assertEquals(header, Contants.HOME_PAGE_HEADDER,"Home page header is not matched......");
	}
	
	@Test(priority = 3)
	public void verifyLoggedUserTest() {
		String loggedUser = homePage.getLoggedInUser();
		System.out.println("Logged user is: "+ loggedUser);
		Assert.assertEquals(loggedUser, prop.getProperty("accountname"), "Logged in user name is:");
	}
	
	
	
	@AfterTest
	public void tearDown() {
			driver.close();		
	}

}
