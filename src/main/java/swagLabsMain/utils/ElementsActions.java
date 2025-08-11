package swagLabsMain.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementsActions {

public static WebElement findElement(WebDriver driver , By locator){

    return driver.findElement(locator);
}

public static void sendData(WebDriver driver, By locator, String data) {

    Waits.waitForElementVisible(driver, locator);
    Scrolling.scrollToElement(driver, locator);
    findElement(driver ,locator).sendKeys(data);
}

public static void clickElement(WebDriver driver, By locator) {

    Waits.waitForElementVisible(driver, locator);
    Scrolling.scrollToElement(driver, locator);
    findElement(driver ,locator).click();
}

public static String getText(WebDriver driver, By locator) {

    Waits.waitForElementVisible(driver, locator);
    Scrolling.scrollToElement(driver, locator);
    return findElement(driver,locator).getText();
}


}
