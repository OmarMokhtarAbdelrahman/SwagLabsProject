package SwagLabs.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import SwagLabs.AbstractComponents.AbstractComponent;

public class PurchaseInfoPage extends AbstractComponent {

	public WebDriver driver;
	protected CheckoutPage checkoutPage;
	protected double price = 0;
	protected double total = 0;

	public PurchaseInfoPage(WebDriver driver) {

		super(driver);
		this.driver = driver;

	}

	By fnameField = By.id("first-name");
	By lnameField = By.id("last-name");
	By postalCodeField = By.id("postal-code");
	By errorMessage = By.cssSelector("h3");
	By cancelBtn = By.cssSelector(".cart_cancel_link");
	By continueBtn = By.cssSelector(".cart_button");

	public void enterFirstName(String firstName) {
		driver.findElement(fnameField).sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		driver.findElement(lnameField).sendKeys(lastName);
	}

	public void enterPostalCode(String postalCode) {
		driver.findElement(postalCodeField).sendKeys(postalCode);
	}

	public CheckoutPage goToConfirmationPage() {
		driver.findElement(continueBtn).click();
		checkoutPage = new CheckoutPage(driver);

		return checkoutPage;
	}

	public String getErrorMessage() {

		waitForElementToAppear(errorMessage);
		return driver.findElement(errorMessage).getText();
	}

}
