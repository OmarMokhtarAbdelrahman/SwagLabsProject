package swagLabsMain.pageObjects;

import swagLabsMain.utils.Waits;
import swagLabsMain.utils.ElementsActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import swagLabsMain.utils.AbstractComponent;

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

	public PurchaseInfoPage enterFirstName(String firstName) {
		ElementsActions.sendData(driver, fnameField, firstName);
		return this;
	}

	public PurchaseInfoPage enterLastName(String lastName) {
		ElementsActions.sendData(driver, lnameField, lastName);
		return this;
	}

	public PurchaseInfoPage enterPostalCode(String postalCode) {
		ElementsActions.sendData(driver, postalCodeField, postalCode);
		return this;
	}

	public CheckoutPage goToConfirmationPage() {
		ElementsActions.clickElement(driver, continueBtn);
		checkoutPage = new CheckoutPage(driver);

		return checkoutPage;
	}

	public String getErrorMessage() {

		Waits.waitForElementVisible(driver ,errorMessage);
		return ElementsActions.findElement(driver, errorMessage).getText();
	}

}
