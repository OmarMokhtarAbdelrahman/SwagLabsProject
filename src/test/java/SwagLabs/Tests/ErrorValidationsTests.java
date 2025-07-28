package SwagLabs.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import SwagLabs.PageObjects.CartPage;
import SwagLabs.PageObjects.PurchaseInfoPage;
import SwagLabs.TestComponents.BaseTest;

public class ErrorValidationsTests extends BaseTest {

	@Test(groups = { "Login Error Validations" } , retryAnalyzer = SwagLabs.TestComponents.RetryConfig.class)
	public void invalidUsernameLoginTest() {

		// validate that the login is unsuccessful with invalid username

		String username = "invalid_user";
		String password = loginPage.getPassword();

		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		String errorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Epic sadface: Username and password do not match any user in this service");

	}

	@Test(groups = { "Login Error Validations" } , retryAnalyzer = SwagLabs.TestComponents.RetryConfig.class)
	public void invalidPasswordLoginTest() {

		// validate that the login is unsuccessful with invalid password

		String username = loginPage.getStandardUserName();
		String password = "invalid_password";

		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		String errorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Epic sadface: Username and password do not match any user in this service");

	}

	@Test(groups = { "Login Error Validations" } , retryAnalyzer = SwagLabs.TestComponents.RetryConfig.class)
	public void blankUsernameLoginTest() {

		// validate that the login is unsuccessful with blank username

		String password = loginPage.getPassword();

		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		String errorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Epic sadface: Username is required");

	}

	@Test(groups = { "Login Error Validations" } , retryAnalyzer = SwagLabs.TestComponents.RetryConfig.class)
	public void blankPasswordLoginTest() {

		// validate that the login is unsuccessful with blank password

		String username = loginPage.getStandardUserName();

		loginPage.enterUsername(username);
		loginPage.clickLoginButton();
		String errorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Epic sadface: Password is required");

	}

	@Test(groups = { "Login Error Validations" } , retryAnalyzer = SwagLabs.TestComponents.RetryConfig.class)
	public void lockedOutUserLoginTest() {

		// validate that the login is unsuccessful with locked out user

		String username = loginPage.getLockedOutUserName();
		String password = loginPage.getPassword();

		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();

		String errorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Epic sadface: Sorry, this user has been locked."); 
		//actual error message is "Epic sadface: Sorry, this user has been locked out."
		//out was removed to prove retry functionality


	}
	
	@Test(groups = { "Checkout Error Validations" } , retryAnalyzer = SwagLabs.TestComponents.RetryConfig.class)
	public void emptyFirstNameInCheckoutTest() {
		
		// validate that the checkout is unsuccessful with empty first name
		
		String username = loginPage.getStandardUserName();
		String password = loginPage.getPassword();
		
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		
		CartPage cartPage = loginPage.goToCart();
		
		PurchaseInfoPage purchaseInfoPage = cartPage.goToCheckout();
		
		purchaseInfoPage.enterLastName("Doe");
		purchaseInfoPage.enterPostalCode("12345");
		
		purchaseInfoPage.goToConfirmationPage();
		
		String errorMsg = purchaseInfoPage.getErrorMessage();
		
		Assert.assertEquals(errorMsg, "Error: First Name is required");
		
	}
	
	@Test(groups = { "Checkout Error Validations" } , retryAnalyzer = SwagLabs.TestComponents.RetryConfig.class)
	public void emptyLastNameInCheckoutTest() {
		
		// validate that the checkout is unsuccessful with empty Last name
		
		String username = loginPage.getStandardUserName();
		String password = loginPage.getPassword();
		
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		
		CartPage cartPage = loginPage.goToCart();
		
		PurchaseInfoPage purchaseInfoPage = cartPage.goToCheckout();
		
		purchaseInfoPage.enterFirstName("Doe");
		purchaseInfoPage.enterPostalCode("12345");
		
		purchaseInfoPage.goToConfirmationPage();
		
		String errorMsg = purchaseInfoPage.getErrorMessage();
		
		Assert.assertEquals(errorMsg, "Error: Last Name is required");
		
	}

	
	@Test(groups = { "Checkout Error Validations" } , retryAnalyzer = SwagLabs.TestComponents.RetryConfig.class)
	public void emptyPostalCodeInCheckoutTest() {
		
		// validate that the checkout is unsuccessful with empty postal code
		
		String username = loginPage.getStandardUserName();
		String password = loginPage.getPassword();
		
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		
		CartPage cartPage = loginPage.goToCart();
		
		PurchaseInfoPage purchaseInfoPage = cartPage.goToCheckout();
		
		purchaseInfoPage.enterFirstName("Doe");
		purchaseInfoPage.enterLastName("Smith");
		
		purchaseInfoPage.goToConfirmationPage();
		
		String errorMsg = purchaseInfoPage.getErrorMessage();
		
		Assert.assertEquals(errorMsg, "Error: Postal Code is required");
		
	}

}
