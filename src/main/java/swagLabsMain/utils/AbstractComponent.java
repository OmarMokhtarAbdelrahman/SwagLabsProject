package swagLabsMain.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import swagLabsMain.pageObjects.CartPage;

public class AbstractComponent {

	private final WebDriver driver;

	By cartIcon = By.cssSelector(".shopping_cart_link");
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


	public CartPage goToCart() {
		ElementsActions.clickElement(driver, cartIcon);

        return new CartPage(driver);
	}

	public void clickBurgerButton() {
		ElementsActions.clickElement(driver, burgerBtn);
		Waits.waitForElementVisible(driver ,sideBar);
	}

	public void clickAllItemsLink() {
		ElementsActions.clickElement(driver, allItemsLink);

	}

	public void clickAboutLink() {
		ElementsActions.clickElement(driver, aboutLink);

	}

	public void clickLogoutLink() {
		ElementsActions.clickElement(driver, logoutLink);

	}

	public void clickResetLink() {
		ElementsActions.clickElement(driver, resetLink);

	}

	public void orderByAlphabeticalAscending() {
		ElementsActions.clickElement(driver, orderByDropdown);
		ElementsActions.clickElement(driver, By.cssSelector("option[value='az']"));
	}

	public void orderByAlphabeticalDescending() {
		ElementsActions.clickElement(driver, orderByDropdown);
		ElementsActions.clickElement(driver, By.cssSelector("option[value='za']"));
	}

	public void orderByPriceLowToHigh() {
		ElementsActions.clickElement(driver, orderByDropdown);
		ElementsActions.clickElement(driver, By.cssSelector("option[value='lohi']"));
	}

	public void orderByPriceHighToLow() {
		ElementsActions.clickElement(driver, orderByDropdown);
		ElementsActions.clickElement(driver, By.cssSelector("option[value='hilo']"));
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

        return Double.parseDouble(priceText.trim());
	}

	public double roundUp(double value) {

        return Math.round(value * 100.0) / 100.0;
	}

}
