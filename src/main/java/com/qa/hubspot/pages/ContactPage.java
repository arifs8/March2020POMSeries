package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utills.Contants;
import com.qa.hubspot.utills.ElementUtill;

public class ContactPage extends BasePage {
	private WebDriver driver;
	
	By header = By.xpath("//i18n-string[@data-key='genericTypes.capitalized.CONTACT']");
	By createContactPrimary = By.xpath("//span[text()=\'Create contact\']"); 
	By email = By.xpath("//input[@data-field='email']");
	By firstname = By.xpath("//input[@data-field='firstname']");
	By lastname = By.xpath("//input[@data-field='lastname']");
	By jobtitle = By.xpath("//input[@data-field='jobtitle']");
	By phonenumber = By.xpath("//input[@data-field='phone']");
	By createContactSecondary = By.xpath("//li//button//span[text()='Create contact']");
	By contactsBackLink = By.xpath("(//*[text()='Contacts'])[position()=1]");
	
	
	public ContactPage(WebDriver driver) {
		this.driver = driver;
		elementUtill= new ElementUtill(this.driver);
	}
	
	public String getContactspAgeTille() {
		return elementUtill.waitForTitleToBePresent(Contants.CONTACTS_PAGE_TITLE, 10);
	}
	
	public String getContactsPageHeader() {
		elementUtill.waitForElementToBeVisible(header, 15);
		return elementUtill.doGetText(header);
	}
	
	public void createContact(String email, String firstname , String lastname , String jobtitle , String phonenumber){
		
		elementUtill.waitForElementToBeVisible(createContactPrimary, 15);
		elementUtill.doClick(createContactPrimary);
		elementUtill.waitForElementToBeVisible(this.email, 5);
		elementUtill.doSendKeys(this.email, email);
		elementUtill.doSendKeys(this.firstname, firstname);
		elementUtill.doSendKeys(this.lastname, lastname);
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementUtill.waitForElementToBeVisible(this.jobtitle, 10);
		elementUtill.doSendKeys(this.jobtitle, jobtitle);
		
		//elementUtill.doSendKeys(this.phonenumber, phonenumber);
		
//		elementUtill.waitForElementToBeVisible(createContactSecondary, 10);
//		elementUtill.doClick(createContactSecondary);
		
		elementUtill.waitForElementToBeVisible(createContactSecondary, 5);
		elementUtill.doActionsClick(createContactSecondary);
		
		elementUtill.doActionsClick(contactsBackLink);
		//elementUtill.clickWhenReady(contactsBackLink, 5);
		
	}
	
}
