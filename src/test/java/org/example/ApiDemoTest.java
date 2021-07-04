package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.Test;
import pageobjects.apidemos.AppHomePage;
import pageobjects.apidemos.PreferencesPage;
import utilities.Base;

import java.io.IOException;

public class ApiDemoTest extends Base {

//    @BeforeTest
//    public AppiumDriverLocalService startTestServer(){
//        service = startServer();
//        return service;
//    }

    @Test
    public void apiDemoTest() throws IOException, InterruptedException {

        //service = startServer();

        AndroidDriver<AndroidElement> driver = getCapabilities("ApiDemo");

        AppHomePage homePage = new AppHomePage(driver);
        homePage.preferences.click();

        PreferencesPage preferencesPage = new PreferencesPage(driver);
        preferencesPage.dependencies.click();

        driver.findElementById("android:id/checkbox").click();
        driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
        driver.findElementByClassName("android.widget.EditText").sendKeys("hello");
        driver.findElementsByClassName("android.widget.Button").get(1).click();

       //service.stop();

    }

//    @AfterTest
//    public void stopServer(){
//        startTestServer().stop();
//    }
}
