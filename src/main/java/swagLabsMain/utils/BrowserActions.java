package swagLabsMain.utils;

import org.openqa.selenium.WebDriver;

public class BrowserActions {

private BrowserActions() {
    // Private constructor to prevent instantiation
}

public static void navigateToLoginPage(WebDriver driver, String url) {
    driver.get(url);
}

public static String getCurrentUrl(WebDriver driver) {
    return driver.getCurrentUrl();

}

public void goBack(WebDriver driver) {
    driver.navigate().back();
}

}