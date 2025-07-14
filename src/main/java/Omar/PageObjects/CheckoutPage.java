package Omar.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Omar.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	public WebDriver driver;
	private double subtotalValue = 0;

	public CheckoutPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".cart_item")
	List<WebElement> productsCard;

	@FindBy(css = ".inventory_item_name")
	List<WebElement> itemsNameList;

	@FindBy(css = ".inventory_item_price")
	List<WebElement> itemsPriceList;

	By itemsPriceBy = By.cssSelector(".inventory_item_price");

	By itemsNameBy = By.cssSelector(".inventory_item_name");

	@FindBy(css = ".summary_subtotal_label")
	WebElement itemTotalLabel;

	@FindBy(css = ".summary_tax_label")
	WebElement taxLabel;

	@FindBy(css = ".summary_total_label")
	WebElement totalLabel;

	private List<WebElement> getProductsList() {
		return productsCard;

	}

	private WebElement getProductsName(String productName) {

		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(itemsNameBy).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);

		return prod;
	}

	public String verifyItemsName(String productName) {

		WebElement product = getProductsName(productName);
		String itemName = product.findElement(itemsNameBy).getText();
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

		for (int i = 0; i < itemsPriceList.size(); i++) {

			String pricetext = itemsPriceList.get(i).getText();
			String[] formattedPrice = pricetext.split("\\$");
			double itemPrice = Double.parseDouble(formattedPrice[1].trim());

			subtotalValue += itemPrice;

		}

		return subtotalValue;

	}
	
	public double getTax() {
		
		String[] tax = taxLabel.getText().split("\\$");
		double taxValue = convertStringToDouble(tax[1].trim());
		
		return taxValue;
	}
	public double getTotalPrice() {
		
		String[] total = totalLabel.getText().split("\\$");
		double totalValue = convertStringToDouble(total[1].trim());
		
		return totalValue;
	}
}
