package swagLabsTests.tests.cart;

import org.testng.Assert;
import org.testng.annotations.Test;

import swagLabsMain.pageObjects.CartPage;
import swagLabsMain.pageObjects.ProductsPage;
import swagLabsTests.testComponents.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("SwagLabs Application")
@Feature("Cart Persistence")
public class CheckingCartAfterLogoutTests extends BaseTest {

	@Test(groups = { "Smoke" }, retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
	@Description("Verify cart items persist after logout and re-login")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Cart Persistence Scenarios")
	public void checkingOnProductsAfterLogoutTest() {

		String productNo1 = "Sauce Labs Onesie";
		String username = loginPage.getUsername("Standard User");
		String password = loginPage.getPassword();

		// Login to the application using fluent pattern
		ProductsPage productsPage = loginPage.enterUsername(username)
				.enterPassword(password)
				.clickLoginButton();

		// Add a product to the cart
		productsPage.addProductToCart(productNo1);
		CartPage cartPage = productsPage.goToCart();

		// Logout from the application
		cartPage.Logout();

		// Login again using fluent pattern
		productsPage = loginPage.enterUsername(username)
				.enterPassword(password)
				.clickLoginButton();

		// Validate that the products are still in the cart after logout
		cartPage = productsPage.goToCart();
		Assert.assertTrue(cartPage.isProductInCart(productNo1));
	}
}
