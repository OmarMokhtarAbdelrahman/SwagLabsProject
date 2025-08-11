package swagLabsTests.tests.checkout;

import org.testng.Assert;
import org.testng.annotations.Test;

import swagLabsMain.pageObjects.CartPage;
import swagLabsMain.pageObjects.PurchaseInfoPage;
import swagLabsTests.testComponents.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("SwagLabs Application")
@Feature("Checkout Validation")
public class CheckoutErrorValidationsTests extends BaseTest {

	@Test(groups = { "Checkout Error Validations" } , retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
	@Description("Verify checkout fails with empty first name")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Checkout Error Scenarios")
	public void emptyFirstNameInCheckoutFirstLevelTest() {
		// validate that the checkout is unsuccessful with empty first name
		String username = loginPage.getUsername("Standard User");
		String password = loginPage.getPassword();
		
		loginPage.enterUsername(username)
				.enterPassword(password)
				.clickLoginButton();
		
		CartPage cartPage = loginPage.goToCart();
		PurchaseInfoPage purchaseInfoPage = cartPage.goToCheckout();
		
		purchaseInfoPage.enterLastName("Doe")
				.enterPostalCode("12345")
				.goToConfirmationPage();
		
		String errorMsg = purchaseInfoPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Error: First Name is required");
	}
	
	@Test(groups = { "Checkout Error Validations" } , retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
	@Description("Verify checkout fails with empty last name")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Checkout Error Scenarios")
	public void emptyLastNameInCheckoutFirstLevelTest() {
		// validate that the checkout is unsuccessful with empty Last name
		String username = loginPage.getUsername("Standard User");
		String password = loginPage.getPassword();
		
		loginPage.enterUsername(username)
				.enterPassword(password)
				.clickLoginButton();
		
		CartPage cartPage = loginPage.goToCart();
		PurchaseInfoPage purchaseInfoPage = cartPage.goToCheckout();
		
		purchaseInfoPage.enterFirstName("Doe")
				.enterPostalCode("12345")
				.goToConfirmationPage();
		
		String errorMsg = purchaseInfoPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Error: Last Name is required");
	}
	
	@Test(groups = { "Checkout Error Validations" } , retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
	@Description("Verify checkout fails with empty postal code")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Checkout Error Scenarios")
	public void emptyPostalCodeInCheckoutFirstLevelTest() {
		// validate that the checkout is unsuccessful with empty postal code
		String username = loginPage.getUsername("Standard User");
		String password = loginPage.getPassword();
		
		loginPage.enterUsername(username)
				.enterPassword(password)
				.clickLoginButton();
		
		CartPage cartPage = loginPage.goToCart();
		PurchaseInfoPage purchaseInfoPage = cartPage.goToCheckout();
		
		purchaseInfoPage.enterFirstName("Doe")
				.enterLastName("Smith")
				.goToConfirmationPage();
		
		String errorMsg = purchaseInfoPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Error: Postal Code is required");
	}


}
