package swagLabsMain.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import swagLabsMain.utils.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	public WebDriver driver;
	private double subtotalValue = 0;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By productsCard = By.cssSelector(".cart_item");
	By itemsNameList = By.cssSelector(".inventory_item_name");
	By itemsPriceList = By.cssSelector(".inventory_item_price");
	By itemTotalLabel = By.cssSelector(".summary_subtotal_label");
	By taxLabel = By.cssSelector(".summary_tax_label");
	By totalLabel = By.cssSelector(".summary_total_label");
	By finishBtn = By.cssSelector(".btn_action");

	private List<WebElement> getProductsList() {
		return driver.findElements(productsCard);
	}

	private WebElement getProductsName(String productName) {
        return getProductsList().stream()
                .filter(product -> product.findElement(itemsNameList).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
	}

	public CheckoutPage verifyItemsName(String productName) {
		WebElement product = getProductsName(productName);
		String itemName = product.findElement(itemsNameList).getText();
		Assert.assertEquals(itemName , productName);
		return this;
	}

	private double verifySubtotalPrices() {
		double subtotalValue = 0;
		List<WebElement> priceElements = driver.findElements(itemsPriceList);
		for (WebElement priceElement : priceElements) {
			String priceText = priceElement.getText();
			String[] formattedPrice = priceText.split("\\$");
			double itemPrice = Double.parseDouble(formattedPrice[1].trim());
			subtotalValue += itemPrice;
		}
		return subtotalValue;
	}
	
	private double getTax() {
		String[] tax = driver.findElement(taxLabel).getText().split("\\$");
        return convertStringToDouble(tax[1].trim());
	}
	
	private double getTotalPrice() {
		String[] total = driver.findElement(totalLabel).getText().split("\\$");
        return convertStringToDouble(total[1].trim());
	}

	private void verifyTotalItemsPriceBeforeTax(){

		Assert.assertEquals(verifySubtotalPrices(),
				convertStringToDouble(driver.findElement(itemTotalLabel).getText().split("\\$")[1].trim()));
	}

	public CheckoutPage verifyTotalPrice(){

		double itemSubtotal = verifySubtotalPrices();

		double tax = getTax();

		double itemsTotal = getTotalPrice();

		double actualTotal = itemSubtotal + tax;
		double adjustedTotal = roundUp(actualTotal);

		verifyTotalItemsPriceBeforeTax();
		Assert.assertEquals(adjustedTotal, itemsTotal);

		return this;
	}
	public FinalCheckoutPage goToCheckoutLastPhase(){
		driver.findElement(finishBtn).click();
		return new FinalCheckoutPage(driver);
	}
}
