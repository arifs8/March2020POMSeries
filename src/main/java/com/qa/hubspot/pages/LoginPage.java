package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utills.Contants;
import com.qa.hubspot.utills.ElementUtill;

import io.qameta.allure.Step;

public class LoginPage extends BasePage{
	
	private WebDriver  driver;
	
	
	
	//1. By locator
	
	By username = By.id("username");
	By password = By.id("password");
	By loginBtn = By.id("loginBtn");
	//By signUpLink= By.linkText("Sign up");
	By signUpLink = By.xpath("//*[contains(text(),'Sign up')]");
	
	//2. Create constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtill = new ElementUtill(this.driver);
	}
	
	//3. Page actions
	@Step("verify SignUp Link.......")
	public boolean verifySignUpLink(){
		return elementUtill.doDisplayed(signUpLink);
	
	}
	
	@Step("get Login Page Title......")
	public String getLoginPageTitle() {
		//return driver.getTitle();
		return elementUtill.waitForTitleToBePresent(Contants.LOGIN_PAGE_TITLE, 10);
	}
	
	@Step("Login to app with username: {0} and password: {1}")
	public HomePage dologin(String username, String password) {
//		driver.findElement(this.username).sendKeys(username);
//		driver.findElement(this.password).sendKeys(password);
//		driver.findElement(this.loginBtn).click();
		elementUtill.waitForElementToBeVisible(this.username, 15);
		elementUtill.doSendKeys(this.username, username);
		elementUtill.doSendKeys(this.password, password);
		elementUtill.doClick(this.loginBtn);
		
		return new HomePage(driver);
		
	}

}
