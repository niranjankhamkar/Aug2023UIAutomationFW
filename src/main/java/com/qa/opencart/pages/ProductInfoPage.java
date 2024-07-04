package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHearder = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails li");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPricData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	
	//private Map<String, String> productMap = new HashMap<String, String>();
	//private Map<String, String> productMap = new LinkedHashMap<String, String>();
	private Map<String, String> productMap = new TreeMap<String, String>();
	
	//constructor to assign the driver
		public ProductInfoPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(this.driver);	
		}
		
		public String getProductHeaderName() {
			String productHeaderVal = eleUtil.doElementGetText(productHearder);
			System.out.println("product header is : "+productHeaderVal);
			return productHeaderVal;
		}
		
		public int getProductImagesCount() {
			int imagesCount =  eleUtil.waitForVisibilityOfElements(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();
			System.out.println("Product "+getProductHeaderName()+" images count is "+imagesCount );
			return imagesCount;
		}
	
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: In Stock
		
		private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.waitForVisibilityOfElements(productMetaData, AppConstants.MEDIUM_DEFAULT_WAIT);	
			for(WebElement e:metaDataList) {
				String metaData = e.getText();
				String key = metaData.split(":")[0].trim();
				String val = metaData.split(":")[1].trim();
				productMap.put(key, val);
			}
		}
		
		private void getProductPriceData() {
			List<WebElement> metaListPrice = eleUtil.waitForVisibilityOfElements(productPricData, AppConstants.SHORT_DEFAULT_WAIT);
			
			String productPrice = metaListPrice.get(0).getText();
			String productExTaxPrice = metaListPrice.get(1).getText().split(":")[1].trim();
			
			productMap.put("price", productPrice);
			productMap.put("exTaxPrice", productExTaxPrice);
		}
		
		public Map<String, String> getProductDetails() {
			
			productMap.put("productname", getProductHeaderName());
			getProductMetaData();
			getProductPriceData();
			
			System.out.println(productMap);
			
//			Map<String,String> productList = new TreeMap<String,String>(productMap);
//			System.out.println(productList);
			return productMap;
		}
}
