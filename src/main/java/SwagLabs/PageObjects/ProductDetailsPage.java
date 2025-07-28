package SwagLabs.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import SwagLabs.AbstractComponents.AbstractComponent;

public class ProductDetailsPage extends AbstractComponent {

	public WebDriver driver;

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By productCard = By.cssSelector(".inventory_details_desc_container");
	By productImg = By.cssSelector(".inventory_details_img");
	By addToCartBtn = By.cssSelector(".btn_primary");
	By removeBtn = By.cssSelector(".btn_secondary");
	By backBtn = By.cssSelector(".inventory_details_back_button");
	By basicBtn = By.cssSelector(".inventory_details_desc_container button");
	
	public void waitForProductDetailsPageToLoad() {
		waitForElementToAppear(productCard);
		waitForElementToAppear(productImg);
	}
	
	public void addProductToCart() {
		driver.findElement(addToCartBtn).click();
	}
	
	public void removeProductFromCart() {
		driver.findElement(removeBtn).click();
	}
	
	public void clickOnBackButton() {
		driver.findElement(backBtn).click();
	}
	
	public String getBtnText() {
		String btnText = driver.findElement(basicBtn).getText();
		return btnText;
	}
	

}
