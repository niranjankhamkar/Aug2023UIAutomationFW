package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName  = By.id("input-firstname");
	private By lastName  = By.id("input-lastname");
	private By email  = By.id("input-email");
	private By telephone  = By.id("input-telephone");
	private By password  = By.id("input-password");
	private By confirmpassword  = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']");
	
	private By agreeCheckBox = By.xpath("//input[@type='checkbox']");
	private By continueButton = By.xpath("//input[@type='submit']");
	
	private By successMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By  registerLink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public boolean userRegistration(String firstName, String lastName, String email, String telephone,
		String password, String subscribe) {
		
		eleUtil.doSendKeysWithWait(this.firstName, firstName,AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);
		
		if(subscribe.equalsIgnoreCase("Yes")) {
			eleUtil.doClick(subscribeYes);
		}else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		String successMesg = eleUtil.waitForVisibilityOfElement(successMessg, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
		System.out.println(successMesg);
		
		if(successMesg.contains(AppConstants.USER_REGISTER_SUCCES_MESSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			
			return true;
		}else{  
			System.out.println("Something wrong in userRegistration page method..");
			return false;
		}
		
	}
	
}
