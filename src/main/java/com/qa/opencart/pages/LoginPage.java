package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By locator
	private By userName  = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart11']");
	private By newCustomerLink = By.linkText("Continue");
	private By registerLink = By.linkText("Register");
	
	
	//constructor to assign the driver
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//page actions/ method
	@Step("getting login page title")	//(coming from allure )
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("login page title : "+title);
		return title; 	
	}
	
	@Step("getting login page url")	//(coming from allure )
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("login page url : "+url);
		return url;
	}
	
	@Step("checking forgot pwd link exist")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	@Step("getting logo exist ")	//(coming from allure )
	public boolean isLogoExist () {
		return eleUtil.waitForPresenceOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		
	}
	
	@Step("username is : {0} and password {1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("login creds are : "+username+" : "+pwd);
		driver.findElement(userName).sendKeys(username);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
		return new AccountsPage(driver);
	}
	
	@Step("cheking new customer link exist")	//(coming from allure )
	public boolean newCustomerLinkExist() {
		return eleUtil.waitForVisibilityOfElement(newCustomerLink, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}
	
	@Step("navigating to register page")	//(coming from allure )
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new RegisterPage(driver);
		
	}

}


