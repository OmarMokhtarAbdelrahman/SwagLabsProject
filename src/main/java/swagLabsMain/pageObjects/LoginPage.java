package swagLabsMain.pageObjects;

import swagLabsMain.utils.BrowserActions;
import swagLabsMain.utils.ElementsActions;
import swagLabsMain.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import swagLabsMain.utils.AbstractComponent;
import org.testng.Assert;

public class LoginPage extends AbstractComponent {

private final WebDriver driver;
public ProductsPage productsPage;

//Locators
private final By usernameField = By.id("user-name");
private final By passwordField = By.id("password");
private final By loginButton = By.cssSelector(".btn_action");
private final By usernamesList = By.id("login_credentials");
private final By passwordList = By.cssSelector(".login_password");
private final By errorMessage = By.cssSelector("h3");

//Constructor
public LoginPage(WebDriver driver) {

    super(driver);
    this.driver = driver;
}

//Actions
public String getUsername(String userType) {

    int i = switch (userType) {
        case "Standard User" -> 1;
        case "Locked Out User" -> 2;
        case "Problem User" -> 3;
        case "Performance User" -> 4;
        default -> 0;
    };


    String usernames = ElementsActions.findElement(driver, usernamesList).getText();
    String[] users = usernames.split(":");
    String[] user = users[1].split("\\n");

    return user[i];
}

public String getPassword() {

    String passwordText = ElementsActions.findElement(driver, passwordList).getText();
    String[] password = passwordText.split(":");

    return password[1];
}

public LoginPage enterUsername(String username) {
    ElementsActions.sendData(driver, usernameField, username);
    return this;
}

public LoginPage enterPassword(String password) {
    ElementsActions.sendData(driver, passwordField, password);
    return this;
}


public String getErrorMessage() {

    Waits.waitForElementVisible(driver, errorMessage);
    return ElementsActions.getText(driver ,errorMessage);

}

public ProductsPage clickLoginButton() {
    ElementsActions.clickElement(driver, loginButton);
    productsPage = new ProductsPage(driver);

    return productsPage;
}

//Validations
public void assertSuccessfulLogin() {
    Assert.assertEquals(BrowserActions.getCurrentUrl(driver), "https://www.saucedemo.com/inventory.html");
}

public void assertUnsuccessfulLogin(){

    String message = ElementsActions.getText(driver, errorMessage);
    Assert.assertEquals(message , "Epic sadface: Username and password do not match any user in this service");
}

public void assertPreventedLogin(){

    String message = ElementsActions.getText(driver, errorMessage);
    Assert.assertEquals(message , "Epic sadface: Sorry, this user has been locked out.");
}
}
