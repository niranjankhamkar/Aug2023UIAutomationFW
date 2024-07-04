package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductResultsPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getSearchData(){
		return new Object[][] {
			{"MacBook","MacBook Pro","4"},
			{"MacBook","MacBook Air","4"},	
			{"iMac","iMac","3"},
			{"Samsung","Samsung SyncMaster 941BW","1"}
		};
	}
	
	@DataProvider
	public Object[][] getSearchExelTestData() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);	
	}
	
	@Test(dataProvider = "getSearchData")
	public void productImagesTest(String searchKey, String productName, String imgCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals( String.valueOf( productInfoPage.getProductImagesCount()),imgCount);
	}
	
	
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productDetailsMap =  productInfoPage.getProductDetails();
		
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		Assert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		Assert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		Assert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		
		Assert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		Assert.assertEquals(productDetailsMap.get("exTaxPrice"), "$2,000.00");
	}
	
	
}
 