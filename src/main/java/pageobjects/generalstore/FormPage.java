package pageobjects.generalstore;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import static utilities.ScrollUtility.scrollTo;


public class FormPage {
    AndroidDriver<AndroidElement> driver;
    public FormPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @AndroidFindBy(id ="com.androidsample.generalstore:id/nameField")
    public AndroidElement nameField;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    public AndroidElement radioFemale;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
    public AndroidElement radioMale;

    @AndroidFindBy(id = "android:id/text1")
    public AndroidElement countrySelector;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    public AndroidElement shopButton;

    public void fillFormWith(String name, String countryName, String gender){
        nameField.sendKeys(name);
        countrySelector.click();
        scrollTo(countryName).click();
        if(gender.equalsIgnoreCase("Male")){
            radioMale.click();
        }
        else {
            radioFemale.click();
        }
        shopButton.click();


        //new ProductsPage(driver);
    }
}


