package pageobjects.apidemos;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class POMBase {
    public static AndroidDriver<AndroidElement> getCapabilities() throws MalformedURLException{

        //Prerequisites
        //Make sure Appium Server is started
        //Make sure emulator is running
        //Bring the app into the project directory

        //first talk to your appium server
        //connection step to server
        //Desired Capabilities
        //I want to open emulator Pixel_3a
        //Install app in the emulator
        //Invoke that app and do testing

        File f = new File("src");
        File file = new File(f, "ApiDemos-debug.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.VERSION, "11");
        capabilities.setCapability("noReset", "true");
        capabilities.setCapability("fullReset", "false");
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("autoAcceptAlerts", "true");
        if(true) {
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_3a");
            capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        }
        else {
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
            capabilities.setCapability(MobileCapabilityType.UDID, "R58M23FC1WE");
        }
        capabilities.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }
}
