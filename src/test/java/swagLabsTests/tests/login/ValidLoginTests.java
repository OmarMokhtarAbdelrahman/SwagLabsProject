package swagLabsTests.tests.login;

import swagLabsTests.testComponents.BaseTest;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("SwagLabs Application")
@Feature("Login Functionality")
public class ValidLoginTests extends BaseTest {

// This test validates that the login is successful with valid credentials
@Test(groups = {"LoginValidations", "Smoke"}, retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
@Description("Verify successful login with Standard User")
@Severity(SeverityLevel.BLOCKER)
@Story("Valid Login Scenarios")
public void validStandardUserLoginTest() {

    // validate that the login is successful with valid credentials
    String username = loginPage.getUsername("Standard User");
    String password = loginPage.getPassword();

    loginPage.enterUsername(username)
            .enterPassword(password)
            .clickLoginButton();

    loginPage.assertSuccessfulLogin();

}

@Test(groups = {"Login Validations", "Smoke"}, retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
@Description("Verify successful login with Problem User")
@Severity(SeverityLevel.CRITICAL)
@Story("Valid Login Scenarios")
public void validProblemUserLoginTest() {

    // validate that the login is successful with valid credentials
    String username = loginPage.getUsername("Problem User");
    String password = loginPage.getPassword();

    loginPage.enterUsername(username)
            .enterPassword(password)
            .clickLoginButton();

    loginPage.assertSuccessfulLogin();

}

@Test(groups = {"Login Validations", "Smoke"}, retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
@Description("Verify successful login with Performance User")
@Severity(SeverityLevel.CRITICAL)
@Story("Valid Login Scenarios")
public void validPerformanceUserLoginTest() {

    // validate that the login is successful with valid credentials
    String username = loginPage.getUsername("Performance User");
    String password = loginPage.getPassword();

    loginPage.enterUsername(username)
            .enterPassword(password)
            .clickLoginButton();
    loginPage.assertSuccessfulLogin();

}

@Test(groups = {"Login Validations", "Smoke"}, retryAnalyzer = swagLabsMain.utils.RetryConfig.class)
@Description("Verify login is prevented for Locked Out User")
@Severity(SeverityLevel.CRITICAL)
@Story("Valid Login Scenarios")
public void lockedOutUserLoginTest() {

    // validate that the login is unsuccessful with locked out user

    String username = loginPage.getUsername("Locked Out User");
    String password = loginPage.getPassword();

    loginPage.enterUsername(username)
            .enterPassword(password)
            .clickLoginButton();
    loginPage.assertPreventedLogin();


}

}
