package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static utilities.Emulator.startEmulator;
import static utilities.ServerRun.checkServerRunning;

public class Base {

    public static AndroidDriver<AndroidElement> driver;
    public static AppiumDriverLocalService service;
    public static String appName;

    @BeforeSuite
    public void stopExistingServer() throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        try {
            String os = System.getProperty("os.name").toLowerCase().substring(0,3);
            //System.out.println("Hello");
            if(os.equalsIgnoreCase("mac")){
                runtime.exec(System.getProperty("user.dir") + "//src//main//resources//RuntimeExecutables//stop-server-mac.sh");
                System.out.println("Stopping existing appium server on MAC");
            }
            if(os.equalsIgnoreCase("win")){
                runtime.exec(System.getProperty("user.dir") + "//src//main//resources//RuntimeExecutables//stop-server-win.bat");
                System.out.println("Stopping existing appium server on WIN");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.sleep(2000);
    }

    @BeforeTest
    public AppiumDriverLocalService startTestServer() {
        service = startServer();
        return service;
    }

    @AfterTest
    public void stopServer() throws InterruptedException {
        Thread.sleep(3000);
        startTestServer().stop();
    }

    public AppiumDriverLocalService startServer() {
        boolean flag = checkServerRunning(4723);
        if (!flag) {
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
        return service;
    }

    public static AndroidDriver<AndroidElement> getCapabilities(String appName) throws IOException, InterruptedException {
        //AndroidDriver<AndroidElement> driver;
        Base.appName = appName;
        String path = System.getProperty("user.dir");
        FileInputStream fis = new FileInputStream(path + "//global.properties");

        Properties properties = new Properties();
        properties.load(fis);

        //File appDir = new File(path);
        File app = new File(path, (String) properties.get(appName));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //String device = (String) properties.get("device");
        String device = System.getProperty("deviceName");
        if (device.contains("emulator")) {
//            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_3a");
//            capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            startEmulator();
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.VERSION, "11");
        capabilities.setCapability("noReset", "true");
        capabilities.setCapability("fullReset", "false");
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("autoAcceptAlerts", "true");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }
}
