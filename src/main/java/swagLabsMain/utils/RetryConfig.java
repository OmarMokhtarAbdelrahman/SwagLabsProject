package swagLabsMain.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import java.util.concurrent.ConcurrentHashMap;

public class RetryConfig implements IRetryAnalyzer {

	private static final int MAX_RETRY_COUNT = 2;
	private static final ConcurrentHashMap<String, Integer> retryCounts = new ConcurrentHashMap<>();
	
	@Override
	public boolean retry(ITestResult result) {
		// Create a unique key that includes test instance and thread information
		String uniqueKey = createUniqueKey(result);
		int currentCount = retryCounts.getOrDefault(uniqueKey, 0);
		
		if(currentCount < MAX_RETRY_COUNT) {
			retryCounts.put(uniqueKey, currentCount + 1);
			return true;
		}
		
		// Clean up after max retries reached
		retryCounts.remove(uniqueKey);
		return false;
	}
	
	/**
	 * Creates a unique key for the test to handle parallel execution properly
	 */
	private String createUniqueKey(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String className = result.getTestClass().getName();
		String instanceId = String.valueOf(System.identityHashCode(result.getInstance()));
		String threadId = String.valueOf(Thread.currentThread().getId());
		
		return className + "." + methodName + "." + instanceId + "." + threadId;
	}
	
	// Method to reset retry count for a specific test
	public static void resetRetryCount(String testName) {
		retryCounts.remove(testName);
	}
	
	// Method to get current retry count for a test
	public static int getRetryCount(String testName) {
		return retryCounts.getOrDefault(testName, 0);
	}
} 