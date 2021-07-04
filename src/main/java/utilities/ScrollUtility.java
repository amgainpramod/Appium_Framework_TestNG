package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

public class ScrollUtility {
    private static AndroidDriver<AndroidElement> driver;

    public static void setDriver(AndroidDriver<AndroidElement> driver){
        ScrollUtility.driver = driver;
    }

    public static AndroidElement scrollToElement(String element) {
        AndroidElement scrollToElement = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector())" +
                ".scrollIntoView(text(\""+element+"\"));");

        new WebDriverWait(driver, 2*60000).until(ExpectedConditions.visibilityOf(scrollToElement));
        return scrollToElement;

    }

    public static AndroidElement scrollTo(String element){
        HashMap<String,Object> scrollObject=new HashMap<String,Object>();

        scrollObject.put("direction", "down");

        //scrollObject.put("text", element);
        scrollObject.put("strategy", "-android uiautomator" );
        scrollObject.put("selector", "new UiScrollable(new UiSelector())" +
                ".scrollIntoView(text(\""+element+"\"));");

        driver.executeScript("mobile:scroll", scrollObject);
        return driver.findElementByXPath("//android.widget.TextView[@text='"+element+"']");
    }
}
