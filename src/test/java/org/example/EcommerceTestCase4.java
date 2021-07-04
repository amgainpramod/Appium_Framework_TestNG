package org.example;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Base;

import java.io.IOException;
import java.util.List;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class EcommerceTestCase4 extends Base {

    @Test
    public void totalValidation() throws IOException, InterruptedException {
        AndroidDriver<AndroidElement> driver = getCapabilities("GeneralStore");
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Shital");
        driver.hideKeyboard();
        driver.findElementByXPath("//android.widget.RadioButton[@text='Female']").click();
        driver.findElementById("android:id/text1").click();

        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));").click();
        driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();


        int productCount = driver.findElementsById("com.androidsample.generalstore:id/productName").size();
        System.out.println(productCount);

        List<AndroidElement> productList = driver.findElementsById("com.androidsample.generalstore:id/productName");
        List<AndroidElement> priceList = driver.findElementsById("com.androidsample.generalstore:id/productPrice");
        List<AndroidElement> addToCartElements = driver.findElementsById("com.androidsample.generalstore:id/productAddCart");

        double sum = 0;
        for (int i = 0; i < productCount; i++) {
            String productName = productList.get(i).getText();
            String priceString = priceList.get(i).getText();//.replace("$", "");
            double price = getAmount(priceString);
            if (productName.contains("Jordan")) {
                sum += price;
                AndroidElement addToCartElement = addToCartElements.get(i);
                addToCartElement.click();
            }
        }
        System.out.println(sum);
        driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
        Thread.sleep(2000);
        //String productNameInCart = driver.findElementById("com.androidsample.generalstore:id/productName").getText();
        //Assert.assertEquals(productNameInCart, "Jordan 6 Rings");

        String priceInCart = driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();//.replace("$ ", "");
        double total = getAmount(priceInCart);

        System.out.println(total);

        Assert.assertEquals(sum, total);

        driver.findElementByClassName("android.widget.CheckBox").click();

        AndroidElement termsAndConditions = driver.findElementById("com.androidsample.generalstore:id/termsButton");

        TouchAction<?> action = new TouchAction<>(driver);

        action.longPress(longPressOptions()
                .withElement(element(termsAndConditions))
                .withDuration(ofSeconds(2)))
                .release()
                .perform();

        driver.findElementByXPath("//android.widget.Button[@text='CLOSE']").click();

        driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();

    }

    public static double getAmount(String priceString){
        String priceSubString = priceString.substring(1);
        return Double.parseDouble(priceSubString);
    }
}
