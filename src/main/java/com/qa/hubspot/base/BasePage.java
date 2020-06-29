package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utills.ElementUtill;
import com.qa.hubspot.utills.OptionsManger;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	 public WebDriver driver;
	 public Properties prop;
	 public ElementUtill elementUtill;
	 public OptionsManger optionsManger;
	 
	 public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	 
	 public static synchronized WebDriver getDriver() {
		 return tlDriver.get();
	 }
	 
	 
	/**
	 * 
	 * this method is used to initialize the {@link WebDriver} on the basis of browser.
	 * @param browserName
	 * @return driver
	 */
	
	public WebDriver init_driver(Properties prop) {
		
		optionsManger = new OptionsManger(prop);
 		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver =new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManger.getChromeOptions()));
			
		}
		
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
		}
		
		else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	public Properties init_prop() {
		
		/*
		 * prop = new Properties(); try { FileInputStream ip = new FileInputStream(
		 * "C:\\Users\\arifs3\\eclipse-workspace\\March2020POMSeries\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties"
		 * ); prop.load(ip); } catch (FileNotFoundException e) {
		 * System.out.println("Error in reading fiel"); // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		prop = new Properties();
		String path = null;
		String env = null;

		try {
			env = System.getProperty("env");
			System.out.println("env value is--->" + env);
			if (env == null) {
				path = "C:\\Users\\arifs3\\eclipse-workspace\\March2020POMSeries\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
			} else {
				switch (env) {
				case "qa":
					path = "C:\\Users\\arifs3\\eclipse-workspace\\March2020POMSeries\\src\\main\\java\\com\\qa\\hubspot\\config\\qa.config.properties";
					break;
				case "dev":
					path = "C:\\Users\\arifs3\\eclipse-workspace\\March2020POMSeries\\src\\main\\java\\com\\qa\\hubspot\\config\\dev.config.properties";
					break;
				case "stage":
					path = "C:\\Users\\arifs3\\eclipse-workspace\\March2020POMSeries\\src\\main\\java\\com\\qa\\hubspot\\config\\stage.config.properties";
					break;
				default:
					System.out.println("Please pass the correct env value----> " + env);
					break;
				}
			}

			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return prop;
		
	}
	
	
	/*
	 * This method will take the screenshot
	 */
	
	public String getScreenshot() {

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

	
	return path;
	}

	


}