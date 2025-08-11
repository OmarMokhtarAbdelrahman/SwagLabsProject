package swagLabsMain.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;

public class AllureListener implements ITestListener {

    private static final Logger logger = LoggingUtility.getLogger(AllureListener.class);
    private static final String SCREENSHOT_DIR = "reports/screenshots";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    
    // Flag to ensure cleanup happens only once per suite run
    private static boolean suiteStarted = false;

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting test: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String errorMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown error";
        logger.error("Test failed: {} - {}", testName, errorMessage);
        
        // Get WebDriver instance and take screenshot
        WebDriver driver = getWebDriverInstance(result);
        if (driver != null) {
            logger.info("Taking screenshot for failed test: {}", testName);
            takeScreenshotAndAttachToAllure(driver, testName);
        } else {
            logger.error("Could not get WebDriver instance for failed test: {}", testName);
            // Try alternative approach
            try {
                Object testInstance = result.getInstance();
                if (testInstance != null) {
                    logger.info("Test instance class: {}", testInstance.getClass().getName());
                    Field[] fields = testInstance.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        logger.info("Field found: {} - Type: {}", field.getName(), field.getType().getName());
                    }
                }
            } catch (Exception e) {
                logger.error("Error inspecting test instance", e);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Test skipped: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test suite started: {}", context.getName());
        
        // Only create environment file if it doesn't exist
        if (!suiteStarted) {
            createEnvironmentFile();
            suiteStarted = true;
            logger.info("Initialized environment for suite: {}", context.getName());
        }
        
        // Clean up old screenshots at the start of test suite
        cleanupOldScreenshots(7); // Keep screenshots for 7 days
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test suite finished: {}", context.getName());
        // Reset flag for next suite run
        suiteStarted = false;
    }

    /**
     * Get WebDriver instance from test class
     */
    private WebDriver getWebDriverInstance(ITestResult result) {
        try {
            Object testInstance = result.getInstance();
            if (testInstance == null) {
                logger.error("Test instance is null for test: {}", result.getMethod().getMethodName());
                return null;
            }

            logger.info("Test instance class: {}", testInstance.getClass().getName());
            
            // Look for driver field
            Field driverField = testInstance.getClass().getDeclaredField("driver");
            driverField.setAccessible(true);
            Object driver = driverField.get(testInstance);
            
            if (driver instanceof WebDriver) {
                logger.info("Successfully retrieved WebDriver instance for test: {}", result.getMethod().getMethodName());
                return (WebDriver) driver;
            } else {
                logger.error("Driver field is not a WebDriver instance for test: {} - Type: {}", 
                           result.getMethod().getMethodName(), 
                           driver != null ? driver.getClass().getName() : "null");
            }
        } catch (NoSuchFieldException e) {
            logger.error("Driver field not found in test class: {}", result.getMethod().getMethodName(), e);
            // List all available fields
            try {
                Object testInstance = result.getInstance();
                if (testInstance != null) {
                    Field[] fields = testInstance.getClass().getDeclaredFields();
                    logger.info("Available fields in test class:");
                    for (Field field : fields) {
                        logger.info("  - {} : {}", field.getName(), field.getType().getName());
                    }
                }
            } catch (Exception ex) {
                logger.error("Error listing fields", ex);
            }
        } catch (Exception e) {
            logger.error("Error getting WebDriver instance for test: {}", result.getMethod().getMethodName(), e);
        }
        return null;
    }

    /**
     * Take screenshot and add to Allure report with comprehensive logging
     */
    private void takeScreenshotAndAttachToAllure(WebDriver driver, String testName) {
        if (driver == null) {
            logger.error("WebDriver is null, cannot take screenshot for test: {}", testName);
            return;
        }
        
        if (!(driver instanceof TakesScreenshot)) {
            logger.error("WebDriver does not support screenshots for test: {}", testName);
            return;
        }
        
        try {
            logger.info("Starting screenshot capture for test: {} - Reason: {}", testName, "Test Failure");
            
            // Take screenshot
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            logger.info("Screenshot captured successfully for test: {} - Size: {} bytes", testName, screenshotBytes.length);
            
            // Save to file system for backup
            String fileName = saveScreenshotToFile(screenshotBytes, testName);
            logger.info("Screenshot saved to file: {}", fileName);
            
            // Attach to Allure report
            attachScreenshotToAllure(screenshotBytes, testName, "Test Failure");
            logger.info("Screenshot successfully attached to Allure report for test: {}", testName);
            
        } catch (Exception e) {
            logger.error("Failed to take screenshot for test: {}", testName, e);
            // Try to take a fallback screenshot
            takeFallbackScreenshot(driver, testName, "Test Failure");
        }
    }
    
    /**
     * Save screenshot to file system
     */
    private String saveScreenshotToFile(byte[] screenshotBytes, String testName) throws IOException {
        // Create screenshots directory if it doesn't exist
        Path screenshotDir = Paths.get(SCREENSHOT_DIR);
        if (!Files.exists(screenshotDir)) {
            Files.createDirectories(screenshotDir);
            logger.info("Created screenshots directory: {}", screenshotDir.toAbsolutePath());
        }
        
        // Generate filename with timestamp
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String fileName = String.format("%s_%s.png", testName.replaceAll("[^a-zA-Z0-9]", "_"), timestamp);
        Path filePath = screenshotDir.resolve(fileName);
        
        // Write screenshot to file
        Files.write(filePath, screenshotBytes);
        
        return filePath.toString();
    }
    
    /**
     * Attach screenshot to Allure report
     */
    private void attachScreenshotToAllure(byte[] screenshotBytes, String testName, String reason) {
        try {
            String attachmentName = String.format("Screenshot - %s (%s)", testName, reason);
            Allure.addAttachment(attachmentName, "image/png", 
                    new ByteArrayInputStream(screenshotBytes), "png");
            logger.info("Screenshot attached to Allure report: {}", attachmentName);
        } catch (Exception e) {
            logger.error("Failed to attach screenshot to Allure report for test: {}", testName, e);
        }
    }
    
    /**
     * Fallback screenshot method
     */
    private void takeFallbackScreenshot(WebDriver driver, String testName, String reason) {
        try {
            logger.warn("Attempting fallback screenshot capture for test: {}", testName);
            
            // Try with different approach
            if (driver instanceof TakesScreenshot) {
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                String attachmentName = String.format("Fallback Screenshot - %s (%s)", testName, reason);
                Allure.addAttachment(attachmentName, "image/png", 
                        new ByteArrayInputStream(screenshotBytes), "png");
                logger.info("Fallback screenshot captured and attached for test: {}", testName);
            }
        } catch (Exception e) {
            logger.error("Fallback screenshot also failed for test: {}", testName, e);
        }
    }
    
    /**
     * Clean up old screenshots (optional)
     */
    private void cleanupOldScreenshots(int daysToKeep) {
        try {
            Path screenshotDir = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(screenshotDir)) {
                return;
            }
            
            long cutoffTime = System.currentTimeMillis() - (daysToKeep * 24 * 60 * 60 * 1000L);
            final int[] deletedCount = {0};
            
            Files.walk(screenshotDir)
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".png"))
                .filter(path -> {
                    try {
                        return Files.getLastModifiedTime(path).toMillis() < cutoffTime;
                    } catch (IOException e) {
                        return false;
                    }
                })
                .forEach(path -> {
                    try {
                        Files.delete(path);
                        deletedCount[0]++;
                    } catch (IOException e) {
                        logger.warn("Failed to delete old screenshot: {}", path, e);
                    }
                });
            
            if (deletedCount[0] > 0) {
                logger.info("Cleaned up {} old screenshot files", deletedCount[0]);
            }
        } catch (Exception e) {
            logger.error("Error during screenshot cleanup", e);
        }
    }
    
    /**
     * Create environment file for Allure reports
     */
    private void createEnvironmentFile() {
        try {
            Path allureResultsDir = Paths.get("allure-results");
            if (!Files.exists(allureResultsDir)) {
                Files.createDirectories(allureResultsDir);
            }
            
            Path envFile = allureResultsDir.resolve("environment.properties");
            String envContent =
                    """
                            Browser=Chrome
                            Platform=Windows 11
                            Tester= Omar Mokhtar
                            Test.Environment=SwagLabs Demo
                            Application.URL=https://www.saucedemo.com/
                            Framework=TestNG
                            """;
            
            Files.write(envFile, envContent.getBytes());
            logger.info("Created environment.properties file for Allure reports");
        } catch (Exception e) {
            logger.error("Error creating environment file", e);
        }
    }
} 