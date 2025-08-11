package swagLabsTests.tests.login;

import swagLabsTests.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("SwagLabs Application")
@Feature("Login Functionality")
public class LoginErrorValidationsTests extends BaseTest {

	@Test(groups = { "Login Error Validations" } , retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
	@Description("Verify login fails with invalid username")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Login Error Scenarios")
	public void invalidUsernameLoginTest() {
		// validate that the login is unsuccessful with invalid username
		String username = "invalid_user";
		String password = loginPage.getPassword();

		loginPage.enterUsername(username)
				.enterPassword(password)
				.clickLoginButton();
		String errorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Epic sadface: Username and password do not match any user in this service");
	}

	@Test(groups = { "Login Error Validations" } , retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
	@Description("Verify login fails with invalid password")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Login Error Scenarios")
	public void invalidPasswordLoginTest() {
		// validate that the login is unsuccessful with invalid password
		String username = loginPage.getUsername("Standard User");
		String password = "invalid_password";

		loginPage.enterUsername(username)
				.enterPassword(password)
				.clickLoginButton();
		String errorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Epic sadface: Username and password do not match any user in this service");
	}

	@Test(groups = { "Login Error Validations" } , retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
	@Description("Verify login fails with blank username")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Login Error Scenarios")
	public void blankUsernameLoginTest() {
		// validate that the login is unsuccessful with blank username
		String password = loginPage.getPassword();

		loginPage.enterPassword(password)
				.clickLoginButton();
		String errorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Epic sadface: Username is required");
	}

	@Test(groups = { "Login Error Validations" } , retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
	@Description("Verify login fails with blank password")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Login Error Scenarios")
	public void blankPasswordLoginTest() {
		// validate that the login is unsuccessful with blank password
		String username = loginPage.getUsername("Standard User");

		loginPage.enterUsername(username)
				.clickLoginButton();
		String errorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Epic sadface: Password is required");
	}

	@Test(groups = { "Login Error Validations" } , retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
	@Description("Verify login fails with blank username and password")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Login Error Scenarios")
	public void blankAllLoginTest() {
		// validate that the login is unsuccessful with blank fields

		loginPage.clickLoginButton();
		String errorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Epic sadface: Username is required");
	}

	@Test(groups = { "Login Error Validations" } , retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
	@Description("Verify login fails with locked out user")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Login Error Scenarios")
	public void lockedOutUserLoginTest() {
		// validate that the login is unsuccessful with locked out user
		String username = loginPage.getUsername("Locked Out User");
		String password = loginPage.getPassword();

		loginPage.enterUsername(username)
				.enterPassword(password)
				.clickLoginButton();

		String errorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(errorMsg, "Epic sadface: Sorry, this user has been locked out.");
		//actual error message is "Epic sadface: Sorry, this user has been locked out."
		//out was removed to prove retry functionality
	}

}
