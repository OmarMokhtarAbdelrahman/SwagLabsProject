package swagLabsMain.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import swagLabsMain.utils.AbstractComponent;

public class FinalCheckoutPage extends AbstractComponent {

	public WebDriver driver;

	public FinalCheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By title = By.cssSelector(".title");
	By backToProductsBtn = By.id("back-to-products");

	public FinalCheckoutPage checkOnTitle(String expectedTitle) {
		WebElement titleElement = driver.findElement(title);
		Assert.assertEquals(titleElement.getText(), expectedTitle);

		return this;
	}

	public void goBackToProductsPage(){
		driver.findElement(backToProductsBtn).click();
	}
}
