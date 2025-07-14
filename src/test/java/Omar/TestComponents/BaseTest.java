package Omar.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Omar.PageObjects.LoginPage;

public class BaseTest {

	public WebDriver driver;
	private Properties prop;
	public LoginPage loginPage;
	protected ChromeOptions options;

	private WebDriver intializeDriver() throws IOException {

		prop = new Properties();
		options = new ChromeOptions();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Omar\\Resources\\data.properties");

		prop.load(fis);
		
		
		final Map<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("profile.credentials_enable_service", false);
		chromePrefs.put("profile.password_manager_enabled", false);
		chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

		final ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("prefs", chromePrefs);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver(chromeOptions);

		} else if (browserName.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();
		}

		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApp() throws IOException {

		driver = intializeDriver();
		driver.get("https://www.saucedemo.com/v1/");
		loginPage = new LoginPage(driver);

		return loginPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		driver.quit();
	}
	
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		//System.getProperty("user.dir") + "\\src\\test\\java\\TestData\\eCommerceTestData.json"
		
		// Read the JSON file content
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);

		// Parse the JSON content into a List of HashMaps
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}
	
	public String getScreenshotPath(String testCaseName, WebDriver driver) throws IOException {
		// This method captures a screenshot and saves it with the test case name
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}

}