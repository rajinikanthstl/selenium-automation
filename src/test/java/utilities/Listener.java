package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;

public class Listener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        // Report is already created by ExtentManager
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getInstance()
                .createTest(result.getMethod().getMethodName());
        ExtentTestManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
        // You can also attach screenshot here
        Reusables utility = new Reusables(DriverManager.getDriver());
        utility.Screenshot(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flushReport();
        ExtentTestManager.removeTest();   // cleanup
    }

}