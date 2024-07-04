package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By locator
	private By userName  = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	private By newCustomerLink = By.linkText("Continue");
	
	private By registerLink = By.linkText("Register");
	
	//constructor to assign the driver
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//page actions/ method
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("login page title : "+title);
		return title; 	
	}
	
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("login page url : "+url);
		return url;
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public boolean isLogoExist () {
		return eleUtil.waitForPresenceOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		
	}
	
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("login creds are : "+username+" : "+pwd);
		driver.findElement(userName).sendKeys(username);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
		return new AccountsPage(driver);
	}
	
	public boolean newCustomerLinkExist() {
		return eleUtil.waitForVisibilityOfElement(newCustomerLink, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new RegisterPage(driver);
		
	}

}


