package pageobjects.generalstore;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {
    public ProductsPage(AndroidDriver<AndroidElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    List<AndroidElement> productList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    List<AndroidElement> priceList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
    List<AndroidElement> addToCartElements;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    AndroidElement cartButton;

    public double addProducts(){
        int productCount = productList.size();

        double sum = 0.0;
        for(int i = 0; i < productCount; i++){
            String priceString = priceList.get(i).getText();
            double price = getAmount(priceString);
            addToCartElements.get(i).click();
            sum += price;
        }
        return sum;
    }

    public static double getAmount(String priceString) {
        String priceSubString = priceString.substring(1);
        return Double.parseDouble(priceSubString);
    }

    public AndroidElement getCartButton() {
        return cartButton;
    }
}
