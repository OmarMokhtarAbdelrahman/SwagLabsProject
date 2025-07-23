package Omar.SwagLabsProject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Omar.PageObjects.CartPage;
import Omar.PageObjects.CheckoutPage;
import Omar.PageObjects.ProductsPage;
import Omar.PageObjects.PurchaseInfoPage;
import Omar.TestComponents.BaseTest;

public class SubmittingOrderTests extends BaseTest {

	@Test(dataProvider = "getData" , groups = {"Smoke"},  retryAnalyzer = Omar.TestComponents.RetryConfig.class)
	public void fullPurchaseTest(HashMap<String , String> inputData){
		
		//System.out.println("Chrome options: " + options.toString());

		
		String username = loginPage.getStandardUserName();
		String password = loginPage.getPassword();

		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		ProductsPage productsPage = loginPage.clickLoginButton();
		
		productsPage.addProductToCart(inputData.get("productName1"));
		//double price1 =productsPage.getProductsPrice(productName1);
		
		productsPage.addProductToCart(inputData.get("productName2"));
		//double price2 =productsPage.getProductsPrice(productName1);
		
		CartPage cartPage = productsPage.goToCart();
		
		Assert.assertTrue(cartPage.checkOnProductsName(inputData.get("productName1")));
		Assert.assertTrue(cartPage.checkOnProductsName(inputData.get("productName2")));
		
		PurchaseInfoPage purchaseInfoPage = cartPage.goToCheckout();
		
		purchaseInfoPage.enterFirstName(inputData.get("firstName"));
		purchaseInfoPage.enterLastName(inputData.get("lastName"));
		purchaseInfoPage.enterPostalCode(inputData.get("postalCode"));
		
		CheckoutPage checkoutPage = purchaseInfoPage.goToConfirmationPage();
		
		String product1 = checkoutPage.verifyItemsName(inputData.get("productName1"));
		Assert.assertEquals(product1, inputData.get("productName1"));
		
		String product2 = checkoutPage.verifyItemsName(inputData.get("productName2"));
		Assert.assertEquals(product2, inputData.get("productName2"));
		double itemSubtotal =checkoutPage.verifySubtotalPrices();
		
		double tax = checkoutPage.getTax();
		
		double itemsTotal = checkoutPage.getTotalPrice();
		
		double actualTotal = itemSubtotal + tax;
		double adjustedTotal = checkoutPage.roundUp(actualTotal);
		
		
		Assert.assertEquals(adjustedTotal, itemsTotal);

	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		// This data provider can be used to provide test data for the test methods
		
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\Omar\\TestData\\testData.json");
		return new Object[][] {{data.get(0)} , {data.get(1)} , {data.get(2)}};
			
	
	
}
}
