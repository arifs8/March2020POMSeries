
package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utills.Contants;
import com.qa.hubspot.utills.ElementUtill;

public class HomePage extends BasePage {
	
	private WebDriver driver;
	

	By header =By.xpath("//h1[text()='Arru']");
	By accountname = By.cssSelector("span.account-name");
	By primaryContactLink = By.id("nav-primary-contacts-branch");
	By secondaryContactLink = By.id("nav-secondary-contacts");
	
	
	
	
	
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elementUtill= new ElementUtill(this.driver);
		
	}
	
	public String getHomePageHeaderText() {
		
//		if(driver.findElement(header).isDisplayed()) 
//		return driver.findElement(header).getText();
//		return null;
		if(elementUtill.doDisplayed(header)) {
			return elementUtill.doGetText(header);
		}
		return null;
		
	}
	
	public String getHomePageTitle() {
		//return driver.getTitle();
		return elementUtill.waitForTitleToBePresent(Contants.HOME_PAGE_TITLE, 10);
	}
	
	public String getLoggedInUser() {
//		if(driver.findElement(accountname).isDisplayed())
//		return driver.findElement(accountname).getText();
//		return null;
		
		if(elementUtill.doDisplayed(accountname)) {
			return elementUtill.doGetText(accountname);
		}
		return null;
		
	}
	
	public ContactPage gotoContactPage() {
		clickOnContacts();
		return new ContactPage(driver);
	}
	
	private void clickOnContacts() {
		elementUtill.waitForElementToBeVisible(primaryContactLink, 15);
		elementUtill.doClick(primaryContactLink);
		elementUtill.waitForElementToBeVisible(secondaryContactLink, 10);
		elementUtill.doClick(secondaryContactLink);
	}
	
	
	
}
