package utilities;

import base.TestBase;
import com.aventstack.extentreports.Status;
import core.drivers.DriverManager;
import core.exceptions.WrongDriverException;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import sun.rmi.runtime.Log;
import utilities.extentreports.ExtentManager;
import utilities.extentreports.ExtentTestManager;

import java.io.ByteArrayInputStream;

public class ReportListener implements ITestListener  {
//    WebDriver driver;
//
//    public String getTestName(ITestResult result) {
//        return result.getTestName() != null ? result.getTestName()
//                : result.getMethod().getConstructorOrMethod().getName();
//    }
//
//    public String getTestDescription(ITestResult result) {
//        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
//    }
//
//    //Text attachments for Allure
//    @Attachment(value = "{0}", type = "text/plain")
//    public static String saveTextLog(String message) {
//        return message;
//    }
//
//    //HTML attachments for Allure
//    @Attachment(value = "{0}", type = "text/html")
//    public static String attachHtml(String html) {
//        return html;
//    }
//
//    //Text attachments for Allure
//    @Attachment(value = "Page screenshot", type = "image/png")
//    public byte[] saveScreenshotPNG(WebDriver driver) {
//        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//    }
//
//    @Override
//    public void onStart(ITestContext iTestContext) {
////        System.out.println("I am in onStart method" + iTestContext.getName());
////        iTestContext.setAttribute("WebDriver", BaseClass.getDriver());
//    }
//
//    @Override
//    public void onFinish(ITestContext iTestContext) {
//    }
//
//    @Override
//    public void onTestStart(ITestResult iTestResult) {
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult iTestResult) {
////        saveScreenshotPNG(TestBase.getDriver());
////        saveTextLog(getTestName(iTestResult) + " success!");
//    }
//
//    @Override
//    public void onTestFailure(ITestResult iTestResult) {
////        try {
////            driver = DriverManager.getDriver();
////        ITestContext context = iTestResult.getTestContext();
//////        driver = (WebDriver) context.getAttribute("driver");
////
////        System.out.println("Test failed:"+ iTestResult.getName());
////            //Allure Screenshot custom
////            saveScreenshotPNG(driver);
////            //Save a log on Allure report.
////            saveTextLog(getTestName(iTestResult) + " failed and screenshot taken!");
////            saveFailureScreenShot(driver);
////        } catch (WrongDriverException ex) {
////            System.out.println("The name of the testcase failed is :"+ iTestResult.getName());
////        }
////        saveScreenshotPNG(TestBase.getDriver());
//        Allure.addAttachment("report", new ByteArrayInputStream(((TakesScreenshot) TestBase.getDriver()).getScreenshotAs(OutputType.BYTES)));
//        saveTextLog(getTestName(iTestResult) + " failed and screenshot taken!");
//    }
//    @Attachment
//    public byte[] saveFailureScreenShot(WebDriver driver) {
//        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//    }





//@Override
//public void onTestStart(ITestResult result) {
//}
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        System.out.println("Test success:"+ result.getName());
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//
//        try {
//            driver = DriverManager.getDriver();
//            makeScreenshotOnFailure(driver);
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//        appendLogToAllure(driver);
//        System.out.println("Test FAILED: " + result.getName());
//        if (result.getThrowable() != null) {
//            result.getThrowable().printStackTrace();
//        }
//    }
//
//    @Attachment(value = "Page screenshot", type = "image/png")
//    public byte[] makeScreenshotOnFailure(WebDriver driver) {
//        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//    }
//
//    @Attachment(value = "page source", type = "text/plain")
//    private String appendLogToAllure(WebDriver driver) {
//        return driver.getPageSource();
//    }


    WebDriver driver;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        driver = TestBase.getDriver();
//        Log.info("Start testing " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", driver);
        //Gọi hàm startRecord video trong CaptureHelpers class
        try {
//            CaptureHelpers.startRecord(iTestContext.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
//        Log.info("End testing " + iTestContext.getName());
        //Kết thúc và thực thi Extents Report
        ExtentManager.getExtentReports().flush();
//        getExtentReports().flush();
        //Gọi hàm stopRecord video trong CaptureHelpers class
//        CaptureHelpers.stopRecord();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
//        Log.info(getTestName(iTestResult) + " test is starting...");
        ExtentTestManager.saveToReport(iTestResult.getName(), iTestResult.getTestName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
//        Log.info(getTestName(iTestResult) + " test is passed.");
        //ExtentReports log operation for passed tests.
        ExtentTestManager.logMessage(Status.PASS, getTestDescription(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
//        Log.error(getTestName(iTestResult) + " test is failed.");
        ExtentTestManager.addScreenShot(Status.FAIL, getTestName(iTestResult));
        ExtentTestManager.logMessage(Status.FAIL, getTestDescription(iTestResult));
        //Allure Screenshot custom
        saveScreenshotPNG(TestBase.getDriver());
        //Save a log on Allure report.
        saveTextLog(getTestName(iTestResult) + " failed and screenshot taken!");

    }
    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    //Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    @Override
    public void onTestSkipped(ITestResult iTestResult) {
//        Log.warn(getTestName(iTestResult) + " test is skipped.");
        ExtentTestManager.logMessage(Status.SKIP, getTestName(iTestResult) + " test is skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
//        Log.error("Test failed but it is in defined success ratio " + getTestName(iTestResult));
        ExtentTestManager.logMessage("Test failed but it is in defined success ratio " + getTestName(iTestResult));
    }
}
