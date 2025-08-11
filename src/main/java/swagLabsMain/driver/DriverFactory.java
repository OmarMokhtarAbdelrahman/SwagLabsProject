package swagLabsMain.driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import swagLabsMain.utils.LoggingUtility;
import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DriverFactory {

    private static final Logger logger = LoggingUtility.getLogger(DriverFactory.class);

    public static WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();

        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\main\\java\\swagLabsMain\\resources\\data.properties");

        prop.load(fis);
        
        logger.info("Loading browser configuration from properties file");
        
        final Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        
        logger.info("Initializing browser: {}", browserName);

        //Browser initialization
        if (browserName.equalsIgnoreCase("chrome")) {
            final ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

            WebDriver driver = new ChromeDriver(chromeOptions);
            logger.info("Chrome browser initialized successfully");
            return driver;

        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("start-maximized");
            edgeOptions.addArguments("--disable-notifications");
            edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

            WebDriver driver = new EdgeDriver(edgeOptions);
            logger.info("Edge browser initialized successfully");
            return driver;

        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions foxOptions = new FirefoxOptions();
            foxOptions.addArguments("--start-maximized");
            foxOptions.addArguments("--disable-notifications");
            foxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

            WebDriver driver = new FirefoxDriver(foxOptions);
            logger.info("Firefox browser initialized successfully");
            return driver;
        }

        logger.error("Unsupported browser: {}", browserName);
        return null;
    }
} 