package swagLabsTests.testComponents;

import java.io.IOException;
import java.sql.Driver;

import swagLabsMain.utils.BrowserActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.ITestResult;

import swagLabsMain.pageObjects.LoginPage;
import swagLabsMain.utils.LoggingUtility;
import swagLabsMain.driver.DriverFactory;
import swagLabsMain.utils.AllureListener;
import org.slf4j.Logger;

@Listeners(AllureListener.class)
public class BaseTest {

	public WebDriver driver;
	public LoginPage loginPage;
	private static final Logger logger = LoggingUtility.getLogger(BaseTest.class);

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApp() throws IOException {
        logger.info("Starting test setup - launching application");
        
        DriverFactory.initializeDriver();
        
        if (DriverFactory.getDriver() == null) {
            throw new RuntimeException("Failed to initialize WebDriver");
        }
        
        BrowserActions.navigateToLoginPage(DriverFactory.getDriver(), "https://www.saucedemo.com/");
        loginPage = new LoginPage(DriverFactory.getDriver());
        logger.info("Application launched successfully");
        return loginPage;
    }

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {
		logger.info("Starting test cleanup - tearing down driver");
		
		// Screenshot is already handled by AllureListener, no need to duplicate here
		
		try {
			if (driver != null) {
				DriverFactory.quitDriver();
				logger.info("Driver quit successfully");
			}
		} catch (Exception e) {
			logger.error("Error during driver cleanup", e);
		}
	}
}