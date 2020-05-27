package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class LoginPage extends BasePage{
	
	private WebDriver  driver;
	
	
	//1. By locator
	
	By username = By.id("username");
	By password = By.id("password");
	By loginBtn = By.id("loginBtn");
	By signUpLink= By.linkText("Sign up");
	
	//2. Create constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//3. Page actions
	public boolean verifySignUpLink() {
		return driver.findElement(signUpLink).isDisplayed();
	}
	
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	public HomePage dologin(String username, String password) {
		driver.findElement(this.username).sendKeys(username);
		driver.findElement(this.password).sendKeys(password);
		driver.findElement(this.loginBtn).click();
		
		
		return new HomePage(driver);
		
	}

}
