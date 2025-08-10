package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject() {

        ExtentSparkReporter reporter = new ExtentSparkReporter("src/test/Reports");

        reporter.config().setDocumentTitle("Test Report");
        reporter.config().setReportName("MultiVendorShop Test Results");


        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        extent.setSystemInfo("Tester", "Yavaar Nosimohomed");

        return extent;

    }

}
