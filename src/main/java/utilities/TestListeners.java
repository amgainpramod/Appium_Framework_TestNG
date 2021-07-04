package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListeners implements ITestListener {
    private static ExtentReports extentReports = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        ExtentTest tests = extentReports.createTest(result.getInstanceName() + " :: "
                + result.getMethod().getMethodName());
        extentTest.set(tests);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Method " + methodName + " Successful" + "</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS, markup);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);

        String methodName = result.getName();
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.get().fail("<details>" + "<summary>" + "<b>" + "<font color=red>" +
                "Exception Occurred: Click to see details: " + "</font>" + "</b>" + "</summary>" +
                exceptionMessage.replaceAll("," , "<br>") + "</details>" + " \n");
        String appName = Base.appName;
        String path = ScreenshotUtility.takeScreenshot(methodName, appName);
        try{
            extentTest.get().fail("<b>" + "<font color=red>" +
                            "Screenshot of failure" + "</font>" + "</b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception e){
            extentTest.get().fail("Test Method Failed, cannot attach screenshot");
        }
        String logText = "<b>" + "Test Method " + methodName + " Failed" + "</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTest.get().log(Status.FAIL, markup);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Method " + methodName + " Skipped" + "</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extentTest.get().log(Status.SKIP, markup);
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
        extentReports.flush();
    }
}
