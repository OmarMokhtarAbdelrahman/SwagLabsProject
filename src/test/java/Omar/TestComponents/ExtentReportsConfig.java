package Omar.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsConfig {

	static ExtentReports ep;
	
	public static ExtentReports reportConfig() {
		// TODO Auto-generated method stub
	
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		ep = new ExtentReports();
		
		ep.attachReporter(reporter);
		ep.setSystemInfo("Tester", "Omar Mokhtar");
		
		return ep;
		
		
		}
	
}
