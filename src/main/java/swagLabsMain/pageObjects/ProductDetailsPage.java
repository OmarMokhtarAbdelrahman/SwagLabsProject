package swagLabsMain.pageObjects;

import org.testng.Assert;
import swagLabsMain.utils.Waits;
import swagLabsMain.utils.ElementsActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import swagLabsMain.utils.AbstractComponent;

public class ProductDetailsPage extends AbstractComponent {

	public WebDriver driver;

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	By productCard = By.cssSelector(".inventory_details_desc_container");
	By productImg = By.cssSelector(".inventory_details_img");
	By addToCartBtn = By.cssSelector(".btn_primary");
	By removeBtn = By.cssSelector(".btn_inventory");
	By backBtn = By.cssSelector(".inventory_details_back_button");
	By basicBtn = By.cssSelector(".inventory_details_desc_container button");
	
	public void waitForProductDetailsPageToLoad() {
		Waits.waitForElementVisible(driver ,productCard);
		Waits.waitForElementVisible(driver ,productImg);
	}
	
	public ProductDetailsPage addProductToCart() {
		waitForProductDetailsPageToLoad();
		ElementsActions.clickElement(driver, addToCartBtn);
		return this;
	}
	
	public ProductDetailsPage removeProductFromCart() {
		waitForProductDetailsPageToLoad();
		ElementsActions.clickElement(driver, removeBtn);
		return this;
	}
	
	public ProductDetailsPage clickOnBackButton() {
		waitForProductDetailsPageToLoad();
		ElementsActions.clickElement(driver, backBtn);
		return this;
	}
	
	public String getBtnText() {
		waitForProductDetailsPageToLoad();
        return ElementsActions.findElement(driver, basicBtn).getText();
	}

	public ProductDetailsPage checkOnButtonToBeNormal(){

		Waits.waitForWebElementPresent(driver , basicBtn);
		String text = getBtnText();
		Assert.assertEquals(text, "Add to cart");
		return this;
	}
	

}
