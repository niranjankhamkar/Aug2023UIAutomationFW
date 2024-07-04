package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void regSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmailID() {
		return "testautomation"+System.currentTimeMillis()+"@opencart.com";
		//return "testautomation"+UUID.randomUUID()+"@gmail.com";
	}
	
	@DataProvider
	public Object[][] getUserRegData() {
		return new Object[][]{
			{"vitthal","desao","9879879","niranjan@543","yes"},
			{"jaysung","kadu","998989888","ansh@0987","no"},
			{"magadev","nikam","0985767522","may@2024","yes"}
		};
	}
	
//	@DataProvider
//	public Object[][] getUserRegTestExcelData() {
//		
//		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
//		return regData;
//		
//	}
	
	//run = total row
	//param = total cols
	@Test(dataProvider = "getUserRegData")
	public void userRegTest(String firstName,String lastName,
					String telephone, String password, String subscribe) {
		
		boolean isRegDone = registerPage.userRegistration(firstName, lastName, getRandomEmailID(),
				 telephone, password, subscribe);
	
		Assert.assertTrue(isRegDone); 
	}
	
}
	