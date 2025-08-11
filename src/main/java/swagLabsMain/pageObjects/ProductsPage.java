package swagLabsMain.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import swagLabsMain.utils.AbstractComponent;

public class ProductsPage extends AbstractComponent {

	public WebDriver driver;

	//Constructor
	public ProductsPage(WebDriver driver) {

		super(driver);
		this.driver = driver;

	}

	//Locators
	By productsList = By.cssSelector(".inventory_item");
	By addToCartBtn = By.cssSelector(".btn_primary");
	By removeBtn = By.cssSelector(".btn_secondary");
	By productsPricesListBy = By.cssSelector(".inventory_item_price");
	By productsLink = By.cssSelector(".inventory_item_name");

	public List<WebElement> getProductsList() {
		return driver.findElements(productsList);
	}

	public WebElement getProductsName(String productName) {

        return getProductsList().stream().filter(product -> product
                .findElement(By.cssSelector(".inventory_item_name"))
                .getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
	}

	public ProductsPage addProductToCart(String productName) {

		WebElement product = getProductsName(productName);
		product.findElement(addToCartBtn).click();
		return this;
	}
	
	public ProductsPage removeProductFromCart(String productName) {

		WebElement product = getProductsName(productName);
		product.findElement(removeBtn).click();
		return this;
	}
	
	public ProductDetailsPage clickOnProductLink(String productName) {

		WebElement product = getProductsName(productName);
		product.findElement(productsLink).click();

        return new ProductDetailsPage(driver);
	}
	
	public double getProductsPrice(String productName) {

		WebElement product = getProductsName(productName);
		String price =product.findElement(productsPricesListBy).getText();
		
		String[] formattedPrice = price.split("\\$");
		
		String productPrice =formattedPrice[1].trim();

        return Double.parseDouble(productPrice);

		}
	
	
}

	

