package Omar.SwagLabsProject;


import org.testng.annotations.Test;


import Omar.TestComponents.BaseTest;

public class ValidLoginTests extends BaseTest {

	
	// This test validates that the login is successful with valid credentials
	@Test(groups = {"LoginValidations" ,"Smoke"} , retryAnalyzer = Omar.TestComponents.RetryConfig.class)
	public void validStandardUserLoginTest() {

		// validate that the login is successful with valid credentials
		String username = loginPage.getStandardUserName();
		String password = loginPage.getPassword();

		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();

	}
	
	@Test(groups = { "Login Validations","Smoke" } , retryAnalyzer = Omar.TestComponents.RetryConfig.class)
	public void validProblemUserLoginTest() {

		// validate that the login is successful with valid credentials
		String username = loginPage.getProblemUserName();
		String password = loginPage.getPassword();

		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();

	}
	
	@Test(groups = { "Login Validations" ,"Smoke"} , retryAnalyzer = Omar.TestComponents.RetryConfig.class)
	public void validPerformanceUserLoginTest() {

		// validate that the login is successful with valid credentials
		String username = loginPage.getPerformanceUserName();
		String password = loginPage.getPassword();

		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();

	}
	
	

	
	
	
	
	
}
