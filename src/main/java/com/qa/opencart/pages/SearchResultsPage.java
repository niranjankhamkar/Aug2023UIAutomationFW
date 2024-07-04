package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage  {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//constructor to assign the driver
		public SearchResultsPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(this.driver);
		}
	
		public ProductInfoPage selectProduct(String productName) {
			eleUtil.waitForVisibilityOfElement(By.linkText(productName), AppConstants.LONG_DEFAULT_WAIT).click();
			return new ProductInfoPage(driver);	//TDD
			
		}
	
	
}
