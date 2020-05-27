
package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class HomePage extends BasePage {
	
	private WebDriver driver;

	By header =By.xpath("//h1[text()='arru']");
	By accountname = By.cssSelector("span.account-name");
	
	
	
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public String getHomePageHeaderText() {
		
		if(driver.findElement(header).isDisplayed()) 
		return driver.findElement(header).getText();
		return null;
	}
	
	public String getHomePageTitle() {
		return driver.getTitle();
	}
	
	public String getLoggedInUser() {
		if(driver.findElement(accountname).isDisplayed())
		return driver.findElement(accountname).getText();
		return null;
	}
	
	
	
	
	
}
