package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenshotUtility {
    public static String getCurrentDateTime() {
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = formatter.format(currentDate.getTime()).replace("/", "_");
        date = date.replace(":", "_");
        return date;
    }

    public static String getReportName(){
        String localDateTime = getCurrentDateTime();
        StringBuilder name = new StringBuilder()
                .append("AutomationReport_")
                .append(localDateTime)
                .append(".html");
        return name.toString();
    }

    public static String getScreenshotName(String methodName, String appName) {
        String localDateTime = getCurrentDateTime();
        StringBuilder name = new StringBuilder().append(appName)
                .append("_")
                .append(methodName)
                .append("_")
                .append(localDateTime)
                .append(".png");
        return name.toString();
    }

    public static String takeScreenshot(String methodName, String appName){
        String fileName = getScreenshotName(methodName, appName);
        String screenshotDir = System.getProperty("user.dir") + "//Reports//Screenshots";
        new File(screenshotDir).mkdirs();
        String path = screenshotDir + "//" + fileName;

        try{
            File screenshot = ((TakesScreenshot)Base.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));
        }catch (Exception e){
            e.printStackTrace();
        }
        path = ".//Screenshots//" + fileName;
        return path;
    }

    public static void getScreenshot(String methodName, String appName) throws IOException {
        String fileName = getScreenshotName(methodName, appName);
        File screenshotFile = ((TakesScreenshot) Base.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(System.getProperty("user.dir") + "//Screenshots//" + fileName + ".png"));
    }
}
