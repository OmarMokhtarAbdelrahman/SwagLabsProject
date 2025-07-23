package Omar.SwagLabsProject;

import org.testng.Assert;
import org.testng.annotations.Test;

import Omar.PageObjects.CartPage;
import Omar.PageObjects.ProductsPage;
import Omar.TestComponents.BaseTest;

public class CheckingCartAfterLogoutTests extends BaseTest {

	@Test(groups = { "Smoke" }, retryAnalyzer = Omar.TestComponents.RetryConfig.class)
	public void CheckingOnProductsAfterLogoutTest() {

		String productNo1 = "Sauce Labs Onesie";

		String username = loginPage.getStandardUserName();
		String password = loginPage.getPassword();

		// Login to the application
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);

		ProductsPage productsPage = loginPage.clickLoginButton();

		// Add a product to the cart
		productsPage.addProductToCart(productNo1);
		CartPage cartPage = productsPage.goToCart();

		// Logout from the application
		cartPage.Logout();

		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		productsPage = loginPage.clickLoginButton();

		// Validate that the products are still in the cart after logout
		productsPage.goToCart();
		Assert.assertTrue(cartPage.isProductInCart(productNo1));

	}

}
