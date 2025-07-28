package SwagLabs.Tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import SwagLabs.PageObjects.CartPage;
import SwagLabs.PageObjects.ProductDetailsPage;
import SwagLabs.PageObjects.ProductsPage;
import SwagLabs.TestComponents.BaseTest;

public class AddingAndRemovingProductsTest extends BaseTest {

	@Test(groups = {"Smoke"} ,retryAnalyzer = SwagLabs.TestComponents.RetryConfig.class)
	public void addAndRemoveProductFromCartTest() {

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

		// Validate that the products are added to the cart
		cartPage.checkOnProductsName(productNo1);

		// Remove a product from the cart
		cartPage.removeProductFromCart(productNo1);

		// Validate that the product is removed from the cart
		Assert.assertFalse(cartPage.isProductInCart(productNo1));

	}

	@Test (groups = {"Smoke"} , retryAnalyzer = SwagLabs.TestComponents.RetryConfig.class)
	public void removeProductFromProductPageTest() {

		String productNo1 = "Sauce Labs Fleece Jacket";

		String username = loginPage.getStandardUserName();
		String password = loginPage.getPassword();

		// Login to the application
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);

		ProductsPage productsPage = loginPage.clickLoginButton();

		// Add a product to the cart
		productsPage.addProductToCart(productNo1);

		CartPage cartPage = productsPage.goToCart();

		cartPage.checkOnProductsName(productNo1);

		// Remove a product from cart from the product page
		cartPage.backToProductsPage();
		productsPage.removeProductFromCart(productNo1);

		productsPage.goToCart();

		// Validate that the product is removed from the cart
		Assert.assertFalse(cartPage.isProductInCart(productNo1));
	}

	@Test(groups = {"Smoke"} , retryAnalyzer = SwagLabs.TestComponents.RetryConfig.class)
	public void addAndRemoveProductFromProductDetailsPageTest() {

		String productNo1 = "Sauce Labs Fleece Jacket";

		String username = loginPage.getStandardUserName();
		String password = loginPage.getPassword();

		// Login to the application
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);

		ProductsPage productsPage = loginPage.clickLoginButton();
		
		ProductDetailsPage productDetailsPage = productsPage.clickOnProductLink(productNo1);
		productDetailsPage.addProductToCart();
		CartPage cartPage = productDetailsPage.goToCart();
		
		// Validate that the products are added to the cart
		cartPage.checkOnProductsName(productNo1);
		
		cartPage.goBack();
		
		// Remove a product from the cart from the product details page
		productDetailsPage.removeProductFromCart();
		String text = productDetailsPage.getBtnText();
		Assert.assertEquals(text, "ADD TO CART");
		
		cartPage = productDetailsPage.goToCart();
		
		Assert.assertFalse(cartPage.isProductInCart(productNo1));
		
	}
}
