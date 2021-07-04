package pageobjects.generalstore;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;
import static pageobjects.generalstore.ProductsPage.getAmount;

public class CartPage {
    AndroidDriver<AndroidElement> driver;
    public CartPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/rvCartProductList")
    List<AndroidElement> cartProductList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    AndroidElement totalAmount;

    @AndroidFindBy(className = "android.widget.CheckBox")
    AndroidElement checkBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    AndroidElement proceedButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    AndroidElement termsAndConditions;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='CLOSE']")
    AndroidElement closeTAndCButton;

    public double getTotalAmount(){
        String totalInCart = totalAmount.getText();
        return getAmount(totalInCart);
    }

    public void readTAndC(){
        TouchAction<?> action = new TouchAction<>(driver);
        action.longPress(longPressOptions()
                .withElement(element(termsAndConditions))
                .withDuration(ofSeconds(2)))
                .release()
                .perform();
        closeTAndCButton.click();
    }

    public AndroidElement getCheckBox() {
        return checkBox;
    }
    public AndroidElement getProceedButton() {
        return proceedButton;
    }
}
