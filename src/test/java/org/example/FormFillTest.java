package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.generalstore.CartPage;
import pageobjects.generalstore.FormPage;
import pageobjects.generalstore.ProductsPage;
import testdata.TestDataGeneralStore;
import utilities.Base;
import utilities.ScrollUtility;

import java.io.IOException;

public class FormFillTest extends Base {

    @Test(dataProvider = "excelData", dataProviderClass = TestDataGeneralStore.class)
    public void testValidation(String name, String country, String gender)
            throws IOException, InterruptedException {
        AndroidDriver<AndroidElement> driver = getCapabilities("GeneralStore");
        FormPage formPage = new FormPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        ScrollUtility.setDriver(driver);

        formPage.fillFormWith(name, country, gender);

        double productSum = productsPage.addProducts();
        productsPage.getCartButton().click();
        double cartTotal = cartPage.getTotalAmount();
        Assert.assertEquals(productSum, cartTotal);

        cartPage.readTAndC();
        cartPage.getCheckBox().click();
        cartPage.getProceedButton().click();
    }
}
