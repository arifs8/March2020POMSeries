package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.ContactPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utills.Contants;
import com.qa.hubspot.utills.ExcelUtill;

public class ContactPageTest extends BaseTest {
	
	HomePage homePage;
	ContactPage contactPage;
	
	@BeforeClass
	public void contactSetup() {
	homePage = loginPage.dologin(prop.getProperty("username"), prop.getProperty("password"));
	contactPage =homePage.gotoContactPage();
	}

	@Test(priority = 1)
	public void verifyContactsPageTitletest() {
		String title = contactPage.getContactspAgeTille();
		System.out.println("contacts page title is : " + title);
		Assert.assertEquals(title, Contants.CONTACTS_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void verifyConatctsPageHeader() {
		String header = contactPage.getContactsPageHeader();
		System.out.println("contacts page header is : "+ header); 
		Assert.assertEquals(header, Contants.CONTACTS_PAGE_HEADER);
	}
	
	@DataProvider
	public Object[][] getContactTestData() {
		Object data[][] = ExcelUtill.getTestData(Contants.CONTACT_SHET_NAME);
		return data;
	}
	
	
	@Test(priority = 3, dataProvider = "getContactTestData")
	public void createContactTest(String email, String firstname , String lastname , String jobtitle , String phonenumber){
		contactPage.createContact(email, firstname, lastname, jobtitle, phonenumber);
	}
	
	//just to push
	
}
