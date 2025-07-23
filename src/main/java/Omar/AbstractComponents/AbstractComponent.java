package Omar.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Omar.PageObjects.CartPage;

public class AbstractComponent {

	private WebDriver driver;
	private WebDriverWait wait;

	By cartIcon = By.cssSelector(".fa-shopping-cart");

	By burgerBtn = By.cssSelector(".bm-burger-button");

	By sideBar = By.cssSelector(".bm-menu-wrap");

	By allItemsLink = By.cssSelector("#inventory_sidebar_link");
	By aboutLink = By.cssSelector("#about_sidebar_link");
	By logoutLink = By.cssSelector("#logout_sidebar_link");
	By resetLink = By.cssSelector("#reset_sidebar_link");

	By orderByDropdown = By.cssSelector(".product_sort_container");

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForWebElementToAppear(WebElement element) {
		// Implement wait logic for the WebElement to appear

		wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToAppear(By findBy) {
		// Implement wait logic for the element to appear

		wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public CartPage goToCart() {
		driver.findElement(cartIcon).click();
		CartPage cartPage = new CartPage(driver);

		return cartPage;
	}

	public void clickBurgerButton() {
		driver.findElement(burgerBtn).click();
		waitForElementToAppear(sideBar);
	}

	public void clickAllItemsLink() {
		driver.findElement(allItemsLink).click();

	}

	public void clickAboutLink() {
		driver.findElement(aboutLink).click();

	}

	public void clickLogoutLink() {
		driver.findElement(logoutLink).click();

	}

	public void clickResetLink() {
		driver.findElement(resetLink).click();

	}

	public void orderByAlphabeticalAscending() {
		driver.findElement(orderByDropdown).click();
		driver.findElement(By.cssSelector("option[value='az']")).click();
	}

	public void orderByAlphabeticalDescending() {
		driver.findElement(orderByDropdown).click();
		driver.findElement(By.cssSelector("option[value='za']")).click();
	}

	public void orderByPriceLowToHigh() {
		driver.findElement(orderByDropdown).click();
		driver.findElement(By.cssSelector("option[value='lohi']")).click();
	}

	public void orderByPriceHighToLow() {
		driver.findElement(orderByDropdown).click();
		driver.findElement(By.cssSelector("option[value='hilo']")).click();
	}

	public void Logout() {
		clickBurgerButton();
		clickLogoutLink();
	}

	public void goToAllItemsPage() {
		clickBurgerButton();
		clickAllItemsLink();
	}

	public void goBack() {
		driver.navigate().back();
	}

	public double convertStringToDouble(String priceText) {

		double price = Double.parseDouble(priceText.trim());

		return price;
	}

	public double roundUp(double value) {

		double result = Math.round(value * 100.0) / 100.0;
		return result;
	}

}
