package swagLabsMain.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    private Waits() {
        // Private constructor to prevent instantiation
    }

    public static WebElement waitForWebElementPresent(WebDriver driver, By locator) {

        // Implement wait logic for the WebElement to appear
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver1 -> driver1.findElement(locator));

    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator) {

        // Implement wait logic for the WebElement to be visible
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver1 -> {
                    WebElement element = waitForWebElementPresent(driver, locator);
                    return element.isDisplayed() ? element : null;
                });
    }

    public WebElement waitForElementClickable(WebDriver driver, By locator) {

        // Implement wait logic for the WebElement to be clickable
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver1 -> {
                    WebElement element = waitForElementVisible(driver, locator);
                    return element.isEnabled() ? element : null;
                });
    }

}