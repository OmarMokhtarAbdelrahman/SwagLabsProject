package SwagLabs.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import SwagLabs.AbstractComponents.AbstractComponent;

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

	private List<WebElement> getProductsList() {
		return driver.findElements(productsCard);

	}

	private WebElement getProductsName(String productName) {

		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(itemsNameList).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);

		return prod;
	}

	public String verifyItemsName(String productName) {

		WebElement product = getProductsName(productName);
		String itemName = product.findElement(itemsNameList).getText();
		return itemName;
	}

	/*private WebElement getProductsPrice(String productName) {

		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(itemsPriceBy).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		return prod;
	}*/

	/*
	 * public double verifyItemsPrice(String productName) {
	 * 
	 * WebElement product = getProductsPrice(productName); String priceText =
	 * product.findElement(itemsPriceBy).getText(); String[] formattedPrice =
	 * priceText.split("$"); double price
	 * =convertStringToDouble(formattedPrice[1].trim());
	 * 
	 * return price; }
	 */

	public double verifySubtotalPrices() {

		double subtotalValue = 0;
		List<WebElement> priceElements = driver.findElements(itemsPriceList);
		for (WebElement priceElement : priceElements) {
			String pricetext = priceElement.getText();
			String[] formattedPrice = pricetext.split("\\$");
			double itemPrice = Double.parseDouble(formattedPrice[1].trim());
			subtotalValue += itemPrice;
		}

		return subtotalValue;

	}
	
	public double getTax() {
		
		String[] tax = driver.findElement(taxLabel).getText().split("\\$");
		double taxValue = convertStringToDouble(tax[1].trim());
		
		return taxValue;
	}
	public double getTotalPrice() {
		
		String[] total = driver.findElement(totalLabel).getText().split("\\$");
		double totalValue = convertStringToDouble(total[1].trim());
		
		return totalValue;
	}
}
