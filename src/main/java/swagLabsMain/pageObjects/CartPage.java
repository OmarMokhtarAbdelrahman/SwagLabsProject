package swagLabsMain.pageObjects;

import java.util.List;

import org.testng.Assert;
import swagLabsMain.utils.Waits;
import swagLabsMain.utils.ElementsActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import swagLabsMain.utils.AbstractComponent;

public class CartPage extends AbstractComponent {

	public WebDriver driver;
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

		Waits.waitForElementVisible(driver ,productsListBy);
		return driver.findElements(productsNamesList);

	}

	public CartPage checkOnProductsName(String productName) {

		boolean result = getProductsList().stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(result);
		return this;
	}

	public CartPage removeProductFromCart(String productName) {

		List<WebElement> productsList = getProductsList();
		List<WebElement> removeButtons = driver.findElements(removeBtnList);

		for (int i = 0; i < productsList.size(); i++) {
			if (productsList.get(i).getText().equalsIgnoreCase(productName)) {
				removeButtons.get(i).click();
				break;
			}
		}
		return this;
	}

	public void checkOnBtnToBeNormal(){



	}

	public void backToProductsPage() {

		ElementsActions.clickElement(driver, continueShoppingBtn);
	}
	
	public boolean isProductInCart(String productName) {

			List<WebElement> productElements = driver.findElements(productsListBy);

			for (WebElement product : productElements) {
				// Find the product name within the cart item
				WebElement productNameElement = product.findElement(productsNamesList);
				if (productNameElement.getText().trim().equalsIgnoreCase(productName)) {
					return true;
				}
			}
			return false;
	}


	public PurchaseInfoPage goToCheckout() {

		ElementsActions.clickElement(driver, checkoutBtn);

        return new PurchaseInfoPage(driver);
	}
}
