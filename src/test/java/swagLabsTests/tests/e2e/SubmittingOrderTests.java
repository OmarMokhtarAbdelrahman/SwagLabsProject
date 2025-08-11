package swagLabsTests.tests.e2e;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import swagLabsMain.pageObjects.*;
import swagLabsMain.utils.JsonDataUtility;
import swagLabsTests.testComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Epic("SwagLabs Application")
@Feature("Order Management")
public class SubmittingOrderTests extends BaseTest {

@DataProvider
public Object[][] getData() throws IOException {
    List<HashMap<String, String>> data = JsonDataUtility.getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\swagLabsTests\\testData\\testData.json");
    return new Object[][]{{data.get(0)}, {data.get(1)}, {data.get(2)}};
}

@Test(dataProvider = "getData", groups = {"Smoke" , "E2E"}, retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
@Description("Verify complete purchase flow with multiple products")
@Severity(SeverityLevel.BLOCKER)
@Story("End-to-End Purchase Flow")
public void validE2eTest(HashMap<String, String> inputData) {

    String username = loginPage.getUsername("Standard User");
    String password = loginPage.getPassword();

    // Login using fluent pattern
    ProductsPage productsPage = loginPage.enterUsername(username).enterPassword(password).clickLoginButton();

    // Add products to cart using fluent pattern
    productsPage.addProductToCart(inputData.get("productName1"))
            .addProductToCart(inputData.get("productName2"));

    CartPage cartPage = productsPage.goToCart();

    cartPage.checkOnProductsName(inputData.get("productName1"))
            .checkOnProductsName(inputData.get("productName2"));

    PurchaseInfoPage purchaseInfoPage = cartPage.goToCheckout();

    // Enter purchase info using fluent pattern
    purchaseInfoPage.enterFirstName(inputData.get("firstName"))
            .enterLastName(inputData.get("lastName"))
            .enterPostalCode(inputData.get("postalCode"));

    CheckoutPage checkoutPage = purchaseInfoPage.goToConfirmationPage();

    FinalCheckoutPage finalCheckOut = checkoutPage.verifyItemsName(inputData.get("productName1"))
            .verifyItemsName(inputData.get("productName2"))
            .verifyTotalPrice()
            .goToCheckoutLastPhase();

    finalCheckOut.checkOnTitle("Checkout: Complete!").goBackToProductsPage();
}

@Test(dataProvider = "getData" , groups = {"E2E"}, retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
@Description("Verify invalid purchase flow with multiple products")
@Severity(SeverityLevel.BLOCKER)
@Story("End-to-End Purchase Flow")
public void invalidE2eTest(HashMap<String, String> inputData) {

    String username = loginPage.getUsername("Standard User");
    String password = loginPage.getPassword();

    ProductsPage productPage = loginPage.enterUsername(username)
            .enterPassword(password).clickLoginButton();

    CartPage cartPage = productPage.addProductToCart(inputData.get("productName1"))
            .addProductToCart(inputData.get("productName2"))
            .goToCart();

    cartPage.checkOnProductsName(inputData.get("productName1"))
            .checkOnProductsName(inputData.get("productName2"));

    PurchaseInfoPage purchaseInfoPage = cartPage.goToCheckout();

    purchaseInfoPage.enterFirstName("")
           .enterLastName(inputData.get("lastName"))
            .enterPostalCode(inputData.get("postalCode")).goToConfirmationPage();

    String errorMsg = purchaseInfoPage.getErrorMessage();

    Assert.assertEquals(errorMsg , "Error: First Name is required");

}

}
