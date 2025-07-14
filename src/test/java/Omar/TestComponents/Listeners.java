package Omar.TestComponents;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.qameta.allure.Attachment;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports report = ExtentReportsConfig.reportConfig();
	ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();
	
//	@Attachment(value = "Failure Screenshot", type = "image/png")
//	public byte[] takeScreenshot() {
//	    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//	}
	
	@Override
	public void onTestStart(ITestResult result) {
		// Code to execute when a test starts
		test = report.createTest(result.getMethod().getMethodName());
		threadLocal.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Code to execute when a test passes
		threadLocal.get().log(Status.PASS, "Test passed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Code to execute when a test fails
		
	//	takeScreenshot();
		
		threadLocal.get().fail(result.getThrowable());
		String screenshotPath = null ;
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			screenshotPath = getScreenshotPath(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		threadLocal.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
	}

	

	@Override
	public void onFinish(ITestContext context) {
		// Code to execute after all tests are finished
		report.flush();
	}

}
