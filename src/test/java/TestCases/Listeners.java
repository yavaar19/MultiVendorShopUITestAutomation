package TestCases;

import Utilities.Screenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static Utilities.ExtentReporterNG.getReportObject;

public class Listeners extends Screenshot implements ITestListener {

    private final ExtentReports extent = getReportObject();
    private ExtentTest test;
    private final ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();

    private WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = extent.createTest(result.getMethod().getMethodName());

        threadLocal.set(test);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);

        threadLocal.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);

        threadLocal.get().fail(result.getThrowable());
        String filePath;

        Object currentClass = result.getInstance();

        driver = ((Base) currentClass).driver;

        try {

            filePath = getScreenshotPath(result.getMethod().getMethodName(), driver);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

        threadLocal.get().addScreenCaptureFromPath(filePath);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);

        extent.flush();

    }

    @Override
    public boolean isEnabled() {
        return ITestListener.super.isEnabled();
    }
}
