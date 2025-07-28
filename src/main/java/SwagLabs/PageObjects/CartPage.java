package SwagLabs.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import SwagLabs.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	public WebDriver driver;
	protected double price = 0;
	protected double total = 0;

	public CartPage(WebDriver driver) {

		super(driver);
		this.driver = driver;

	}

	By productsNamesList = By.cssSelector(".inventory_item_name");

	By productsPricesList = By.cssSelector(".inventory_item_price");

	By continueShoppingBtn = By.cssSelector(".cart_footer .btn_secondary");

	By checkoutBtn = By.cssSelector(".cart_footer .btn_action");

	By removeBtnList = By.cssSelector(".cart_button");

	By productsListBy = By.cssSelector(".cart_item");

	public List<WebElement> getProductsList() {

		waitForElementToAppear(productsListBy);
		return driver.findElements(productsNamesList);

	}

	public boolean checkOnProductsName(String productName) {

		boolean prod = getProductsList().stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		return prod;
	}

	public void removeProductFromCart(String productName) {

		List<WebElement> productsList = getProductsList();
		List<WebElement> removeButtons = driver.findElements(removeBtnList);

		for (int i = 0; i < productsList.size(); i++) {
			if (productsList.get(i).getText().equalsIgnoreCase(productName)) {
				removeButtons.get(i).click();
				break;
			}
		}

	}

	public void backToProductsPage() {
		driver.findElement(continueShoppingBtn).click();
	}
	
	public boolean isProductInCart(String productName) {
		
	    try {
	        return driver.findElement(productsListBy).isDisplayed();
	    } catch (NoSuchElementException e) {
	        return false;
	}
	}

	public PurchaseInfoPage goToCheckout() {

		driver.findElement(checkoutBtn).click();
		PurchaseInfoPage purchaseInfoPage = new PurchaseInfoPage(driver);

		return purchaseInfoPage;
	}
}
