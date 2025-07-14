package Omar.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Omar.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	public WebDriver driver;
	protected double price = 0;
	protected double total = 0;

	public CartPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".inventory_item_name")
	List<WebElement> productsNamesList;

	@FindBy(css = ".inventory_item_price")
	List<WebElement> productsPricesList;

	@FindBy(css = ".cart_footer .btn_secondary")
	WebElement continueShoppingBtn;

	@FindBy(css = ".cart_footer .btn_action")
	WebElement checkoutBtn;

	@FindBy(css = ".cart_button")
	List<WebElement> removeBtnList;

	By productsListBy = By.cssSelector(".cart_item");

	public List<WebElement> getProductsList() {

		waitForElementToAppear(productsListBy);
		return productsNamesList;

	}

	public boolean checkOnProductsName(String productName) {

		boolean prod = getProductsList().stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		return prod;
	}

	public void removeProductFromCart(String productName) {

		List<WebElement> productsList = getProductsList();

		for (int i = 0; i < productsList.size(); i++) {
			if (productsList.get(i).getText().equalsIgnoreCase(productName)) {
				removeBtnList.get(i).click();
				break;
			}
		}

	}

	public void backToProductsPage() {
		continueShoppingBtn.click();
	}
	
	public boolean isProductInCart(String productName) {
		
	    try {
	        return driver.findElement(productsListBy).isDisplayed();
	    } catch (NoSuchElementException e) {
	        return false;
	}
	}

	public PurchaseInfoPage goToCheckout() {

		checkoutBtn.click();
		PurchaseInfoPage purchaseInfoPage = new PurchaseInfoPage(driver);

		return purchaseInfoPage;
	}
}
