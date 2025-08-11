package swagLabsTests.tests.productManagement;

import org.testng.Assert;
import swagLabsMain.pageObjects.CartPage;
import swagLabsMain.pageObjects.ProductDetailsPage;
import swagLabsMain.pageObjects.ProductsPage;
import swagLabsTests.testComponents.BaseTest;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("SwagLabs Application")
@Feature("Product Management")
public class AddingAndRemovingProductsTest extends BaseTest {

	@Test(groups = {"Smoke"}, retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
	@Description("Verify adding product to cart functionality")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Adding and Removing Operations")
	public void addProductFromProductPageTest() {

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

    // Validate that the products are added to the cart
    cartPage.checkOnProductsName(productNo1);
}

    @Test(groups = {"Smoke"}, retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
    @Description("Verify remove product from cart functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Adding and Removing Operations")
    public void removeProductFromProductPageTest() {

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

        // Validate that the products are added to the cart
        cartPage.checkOnProductsName(productNo1).removeProductFromCart(productNo1);

        Assert.assertFalse(cartPage.isProductInCart(productNo1));

    }

	@Test(groups = {"Smoke"}, retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
	@Description("Verify adding product from product details page")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Adding and Removing Operations")
	public void addProductFromProductDetailsPageTest() {

    String productNo1 = "Sauce Labs Fleece Jacket";
    String username = loginPage.getUsername("Standard User");
    String password = loginPage.getPassword();

    // Login to the application using fluent pattern
    ProductsPage productsPage = loginPage.enterUsername(username)
            .enterPassword(password)
            .clickLoginButton();

    ProductDetailsPage productDetailsPage = productsPage.clickOnProductLink(productNo1).addProductToCart();
    CartPage cartPage = productDetailsPage.goToCart();

    // Validate that the products are added to the cart
    cartPage.checkOnProductsName(productNo1);

}
    @Test(groups = {"Smoke"}, retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
    @Description("Verify removing product from product details page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Adding and Removing Operations")
    public void removeProductFromProductDetailsPageTest() {

        String productNo1 = "Sauce Labs Fleece Jacket";
        String username = loginPage.getUsername("Standard User");
        String password = loginPage.getPassword();

        // Login to the application using fluent pattern
        ProductsPage productsPage = loginPage.enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        ProductDetailsPage productDetailsPage = productsPage.clickOnProductLink(productNo1).addProductToCart();
        CartPage cartPage = productDetailsPage.goToCart();

        // Validate that the products are added to the cart
        cartPage.checkOnProductsName(productNo1).goBack();

        // Remove a product from the cart from the product details page
        productDetailsPage.removeProductFromCart().checkOnButtonToBeNormal();

        cartPage = productDetailsPage.goToCart();

        Assert.assertFalse(cartPage.isProductInCart(productNo1));
    }

    @Test(groups = {"Smoke"}, retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
    @Description("Verify removing product from cart page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Adding and Removing Operations")
    public void removeProductFromCartTest() {

        String productNo1 = "Sauce Labs Fleece Jacket";
        String username = loginPage.getUsername("Standard User");
        String password = loginPage.getPassword();

        // Login to the application using fluent pattern
        ProductsPage productsPage = loginPage.enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        ProductDetailsPage productDetailsPage = productsPage.clickOnProductLink(productNo1).addProductToCart();
        CartPage cartPage = productDetailsPage.goToCart();

        // Validate that the products are added to the cart
        cartPage.checkOnProductsName(productNo1).removeProductFromCart(productNo1);
        Assert.assertFalse(cartPage.isProductInCart(productNo1));

    }


}
