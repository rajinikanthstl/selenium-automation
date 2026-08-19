package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailed implements IRetryAnalyzer{
	
	int retries = 0;
	int retry_count = 2;

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(retries<retry_count) {
			retries++;
			return true;
		}
		
		return false;
	}

}
