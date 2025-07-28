package SwagLabs.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import SwagLabs.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

	private WebDriver driver;
	public ProductsPage productsPage;

	public LoginPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;

	}

	By usernameField = By.id("user-name");
	By passwordField = By.id("password");
	By loginButton = By.cssSelector(".btn_action");
	By usernamesList = By.id("login_credentials");
	By passwordList = By.cssSelector(".login_password");
	By errorMessage = By.cssSelector("h3");

	public String getStandardUserName() {

		String usernames = driver.findElement(usernamesList).getText();
		String[] users = usernames.split(":");
		String[] user = users[1].split("\\n");

		return user[1];
	}
	
	public String getLockedOutUserName() {

		String usernames = driver.findElement(usernamesList).getText();
		String[] users = usernames.split(":");
		String[] user = users[1].split("\\n");

		return user[2];
	}
	
	public String getProblemUserName() {

		String usernames = driver.findElement(usernamesList).getText();
		String[] users = usernames.split(":");
		String[] user = users[1].split("\\n");

		return user[3];
	}
	
	public String getPerformanceUserName() {

		String usernames = driver.findElement(usernamesList).getText();
		String[] users = usernames.split(":");
		String[] user = users[1].split("\\n");

		return user[4];
	}

	public String getPassword() {

		String passwordText = driver.findElement(passwordList).getText();
		String[] password = passwordText.split(":");

		return password[1];
	}

	public void enterUsername(String username) {
		driver.findElement(usernameField).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}

	public ProductsPage clickLoginButton() {
		driver.findElement(loginButton).click();
		productsPage = new ProductsPage(driver);
		
		return productsPage;
	}
	
	public String getErrorMessage() {
		
		waitForElementToAppear(errorMessage);
		return driver.findElement(errorMessage).getText();
	}
}
