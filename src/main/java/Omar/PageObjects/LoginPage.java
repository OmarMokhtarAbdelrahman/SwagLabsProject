package Omar.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Omar.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

	private WebDriver driver;
	public ProductsPage productsPage;

	public LoginPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "user-name")
	WebElement usernameField;

	@FindBy(id = "password")
	WebElement passwordField;

	@FindBy(css = ".btn_action")
	WebElement loginButton;

	@FindBy(id = "login_credentials")
	WebElement usernamesList;

	@FindBy(css = ".login_password")
	WebElement passwordList;
	
	@FindBy(css = "h3")
	WebElement errorMessage;

	public String getStandardUserName() {

		String usernames = usernamesList.getText();
		String[] users = usernames.split(":");
		String[] user = users[1].split("\\n");

		return user[1];
	}
	
	public String getLockedOutUserName() {

		String usernames = usernamesList.getText();
		String[] users = usernames.split(":");
		String[] user = users[1].split("\\n");

		return user[2];
	}
	
	public String getProblemUserName() {

		String usernames = usernamesList.getText();
		String[] users = usernames.split(":");
		String[] user = users[1].split("\\n");

		return user[3];
	}
	
	public String getPerformanceUserName() {

		String usernames = usernamesList.getText();
		String[] users = usernames.split(":");
		String[] user = users[1].split("\\n");

		return user[4];
	}

	public String getPassword() {

		String passwordText = passwordList.getText();
		String[] password = passwordText.split(":");

		return password[1];
	}

	public void enterUsername(String username) {
		usernameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public ProductsPage clickLoginButton() {
		loginButton.click();
		productsPage = new ProductsPage(driver);
		
		return productsPage;
	}
	
	public String getErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
