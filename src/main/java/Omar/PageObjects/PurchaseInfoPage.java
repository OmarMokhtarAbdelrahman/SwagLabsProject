package Omar.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Omar.AbstractComponents.AbstractComponent;

public class PurchaseInfoPage extends AbstractComponent {

	public WebDriver driver;
	protected CheckoutPage checkoutPage;
	protected double price = 0;
	protected double total = 0;

	public PurchaseInfoPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "first-name")
	WebElement fnameField;

	@FindBy(id = "last-name")
	WebElement lnameField;

	@FindBy(id = "postal-code")
	WebElement postalCodeField;
	
	@FindBy(css = "h3")
	WebElement errorMessage;

	@FindBy(css = ".cart_cancel_link")
	WebElement cancelBtn;

	@FindBy(css = ".cart_button")
	WebElement continueBtn;

	public void enterFirstName(String firstName) {
		fnameField.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		lnameField.sendKeys(lastName);
	}

	public void enterPostalCode(String postalCode) {
		postalCodeField.sendKeys(postalCode);
	}

	public CheckoutPage goToConfirmationPage() {
		continueBtn.click();
		checkoutPage = new CheckoutPage(driver);

		return checkoutPage;
	}

	public String getErrorMessage() {

		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
