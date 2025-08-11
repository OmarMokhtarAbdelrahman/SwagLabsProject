package swagLabsMain.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scrolling {

    private Scrolling() {
        // Private constructor to prevent instantiation
    }
    public static void scrollToElement(WebDriver driver, By locator) {
        // Scroll to the element using JavaScript
        WebElement element =  ElementsActions.findElement(driver ,locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
