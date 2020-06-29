package com.qa.hubspot.utills;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtill {
	
WebDriver driver;
JavaScriptUtil jsUtil;
	
	
	public ElementUtill(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(this.driver);
		
		}
	
	public List<WebElement> getElements(By locator){
		List<WebElement> elementList = driver.findElements(locator);
		return elementList;
	}
	
	
	public WebElement getElement(By locator) {
		
		WebElement element = null;
		
		try {
			System.out.println("Locator is....."+locator);
			element  = driver.findElement(locator);
			jsUtil.flash(element);
			System.out.println("WebElement is created successfully:..." + locator);
		}
		catch(Exception e) {
		  System.out.println("Got some exception ..." + locator);
		}
		return element;
	}
	
	
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
		
	}
	
	
	public void doClick(By locator) {
		 getElement(locator).click();
	}
	
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	
	public boolean doDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	//**********************DropDown Utills
	//*******************
	
	public  void doSelectByVisibleText (By locator , String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
		
	}
	
	public  void doSelectByValue(By locator , String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
		
		
	}
	
	public  void doSelectByIndex(By locator , int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
		
	}
	
	public  int doDropDown( By locaotr){
		return doGetDropDown(locaotr).size(); 
	}

	
	public  ArrayList<String> doGetDropDown( By locaotr) {
		
		ArrayList<String> ar = new ArrayList<String>();
		Select select = new Select(getElement(locaotr));
    	List<WebElement> OptionsList = select.getOptions();
		
		
		for(int i=0; i<OptionsList.size();i++) {
			String text = OptionsList.get(i).getText();
			ar.add(text);
			
		
	}
		return ar;
	
}
	
	public void doSelectDropDownValue( By locator, String value) {
		
		Select selectday = new Select(getElement(locator));
    	List<WebElement> OptionsList = selectday.getOptions();
		//System.out.println(OptionsList.size());
		
		for(int i=0; i<OptionsList.size();i++) {
			String text = OptionsList.get(i).getText();
			///System.out.println(text);
			
				if(text.equals("value")) {
					OptionsList.get(i).click();
					break;
				}
		}
		
		
		
		
	}
	public void doSelectdropDrownValueWithoutSlect( By locator , String value) {
		
		List<WebElement> optionsList = driver.findElements(locator);
		for(int i=0; i<optionsList.size(); i++) {
			String text = optionsList.get(i).getText();
			
			if(text.equals("value")) {
				optionsList.get(i).click();
				break;
			}
			
		}
		
	}
	
	public void selectChoiceValue(By locator , String... value) {
		
		//List<WebElement> choiceList = driver.findElements(By.cssSelector("span.comboTreeItemTitle"));
		List<WebElement> choiceList =  getElements(locator);
		
		if(!value[0].equalsIgnoreCase("All")) {
		
		for(int i=0; i<choiceList.size(); i++) {
			String text= choiceList.get(i).getText();
			System.out.println(text);
			
			
			for(int j=0; j<value.length; j++) {
				if(text.equals(value[j])) {
					choiceList.get(i).click();
					break;
			}
			
			
			}
			
		}
		
	
		
		}
		else {
			try {
			
			for (int all=0; all<choiceList.size();all++) {
				choiceList.get(all).click();
			}
			}
			catch(Exception e) {
				
			}
		}
		
		}
	
	//*************************Actions Class Utills
	//***************************************
	
	public void doDragandDrop(By source , By target) {
		
		Actions action = new Actions(driver);
		WebElement sourceEle = getElement(source);
		WebElement targetEle = getElement(target);
		action.dragAndDrop(sourceEle, targetEle).build().perform();
		
		
	}
	
	public void doActionsSendKeys(By locator, String value) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(locator), value).build().perform();
	}

	public void doActionsClick(By locator) {
		Actions action = new Actions(driver);
		action.click(getElement(locator)).build().perform();
	}
	
	
	
	//********************** Wait Utills
	//************************************************************
	
	
	public List<WebElement> visibilityoffAllElements(By locator , int timeout){
		List<WebElement> elements = getElements(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		
	}
	
	
	
	
	
	public   WebElement waitForElementPresent( By locator,  int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}
	
	
	public   WebElement waitForElementToBeVisible( By locator,  int timeout) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	
	public   WebElement waitForElementToBeClickable( By locator,  int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}
	
	public   boolean waitForUrl( String url,  int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlContains(url));	
	}
	
	public   Alert waitForAlertToBePresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		return alert;
	}
	
	
	public   void clickWhenReady( By locator,  int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	
	public  String waitForTitleToBePresent( String title,  int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
	
	
	
	
	
}
