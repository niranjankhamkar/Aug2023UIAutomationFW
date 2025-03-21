package com.qa.opencart.factory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static String highlight = null;

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");	//read it from properties (eg qa, dev.properties)
		// String browserName = System.getProperty("browser");	//read it from environment variable (CMD -Denv="qa")
																//from jenkins also (env,browser)
		System.out.println("In DriverFactory browser name is: " + browserName);

		highlight = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
			break;

		case "firefox":
			
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			
			break;

		case "edge":
			
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			
			break;

		case "safari":
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("please pass the right browser name...." + browserName);
			throw new FrameworkException("No Browser Found...");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {

		// mvn clean install -Denv="qa"
		// mvn clean install
		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		System.out.println("In Driver Factory env name is: " + envName);

		try {
			if (envName == null) {
				System.out.println("your env is null...hence running tests on QA env...");
				ip = new FileInputStream(".\\src\\test\\resources\\config\\config.qa.properties");
			}

			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\config.qa.properties");
					break;
				case "dev":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\config.dev.properties");
					break;
				case "stage":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\config.stage.properties");
					break;
				case "uat":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\config.uat.properties");
					break;
				case "prod":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\config.prod.properties");
					break;

				default:
					System.out.println("please pass the right env name..." + envName);
					throw new FrameworkException("Wrong Env Name: " + envName);
				}
			}
		} catch (FileNotFoundException e) {

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		

	}



	/**
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}