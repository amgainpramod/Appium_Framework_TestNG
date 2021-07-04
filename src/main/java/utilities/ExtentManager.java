package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extentReports;

    public static ExtentReports getInstance(){
        if(extentReports == null){
            createInstance();
        }
        return  extentReports;
    }

    private static synchronized ExtentReports createInstance() {
        String fileName = ScreenshotUtility.getReportName();
        System.out.println(fileName);
        String reportsDirectory = System.getProperty("user.dir") + "//Reports//";
        new File(reportsDirectory).mkdirs();
        String path = reportsDirectory + fileName;

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Mobile Automation");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName(fileName);

        extentReports = new ExtentReports();
        extentReports.setSystemInfo("Organization", "Pramod Amgain");
        extentReports.setSystemInfo("Automation Framework", "Appium");
        extentReports.attachReporter(sparkReporter);

        return extentReports;
    }
}
