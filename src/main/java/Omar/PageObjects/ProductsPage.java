package Omar.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Omar.AbstractComponents.AbstractComponent;

public class ProductsPage extends AbstractComponent {

	public WebDriver driver;

	public ProductsPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".inventory_item")
	List<WebElement> productsList;
		
	By addToCartBtn = By.cssSelector(".btn_primary");
	By removeBtn = By.cssSelector(".btn_secondary");
	By productsPricesListBy = By.cssSelector(".inventory_item_price");
	By productsLink = By.cssSelector(".inventory_item_name");

	public List<WebElement> getProductsList() {
		return productsList;
	}

	public WebElement getProductsName(String productName) {

		WebElement prod = getProductsList().stream().filter(product -> product
				.findElement(By.cssSelector(".inventory_item_name")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);

		return prod;
	}

	public void addProductToCart(String productName) {

		WebElement product = getProductsName(productName);
		product.findElement(addToCartBtn).click();
	}
	
	public void removeProductFromCart(String productName) {

		WebElement product = getProductsName(productName);
		product.findElement(removeBtn).click();
	}
	
	public ProductDetailsPage clickOnProductLink(String productName) {

		WebElement product = getProductsName(productName);
		product.findElement(productsLink).click();
		
		ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
		return productDetailsPage;
	}
	
	public double getProductsPrice(String productName) {

		WebElement product = getProductsName(productName);
		String price =product.findElement(productsPricesListBy).getText();
		
		String[] formattedPrice = price.split("$");
		
		String productPrice =formattedPrice[1].trim();
		
		double finalPrice = Double.parseDouble(productPrice);
		
		return finalPrice;

		}
	
	
}

	

