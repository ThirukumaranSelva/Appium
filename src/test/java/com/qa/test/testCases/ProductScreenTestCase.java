package com.qa.test.testCases;

import com.qa.test.development.base.BaseClass;
import com.qa.test.development.pages.LoginPage;
import com.qa.test.development.pages.ProductsDetailsPage;
import com.qa.test.development.pages.ProductsPage;
import com.qa.test.development.pages.SettingsPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProductScreenTestCase extends BaseClass {
    LoginPage loginPage;
    ProductsPage productsPage;
    JSONObject jsonObject;
    InputStream dataInputStream;
    SettingsPage settingsPage;
    ProductsDetailsPage productsDetailsPage;

    @BeforeClass
    public void beforeClass() throws IOException {

        try {

            String dataFile =
                    System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "com" + File.separator + "qa" + File.separator + "test" + File.separator + "development" + File.separator + "testData" + File.separator + "prerequisitesTestData.json";

            System.out.println(dataFile);
            dataInputStream = new FileInputStream(dataFile);
            System.out.println(dataInputStream);
            JSONTokener jsonToken = new JSONTokener(dataInputStream);
            jsonObject = new JSONObject(jsonToken);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (dataInputStream != null) {
                dataInputStream.close();
            }
        }

    }

    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage();
    }

    @Test(enabled = false)
    public void validateProduct() {
        productsPage = loginPage.loginPage(jsonObject.getJSONObject("validCredentials").getString("username"),
                jsonObject.getJSONObject("validCredentials").getString("password"));
        String actualProductTitle = productsPage.productTitle();
        String expectedProductTitle = propertiesTestData.getProperty("productTitle");
        Assert.assertEquals(actualProductTitle, expectedProductTitle);
        String actualProductPrice = productsPage.productPrice();
        String expectedProductPrice = propertiesTestData.getProperty("productPrice");
        Assert.assertEquals(actualProductPrice, expectedProductPrice);
        settingsPage = productsPage.menuButton();
        loginPage = settingsPage.logout();
    }

    @Test
    public void validateProductDetails() {
        productsPage = loginPage.loginPage(jsonObject.getJSONObject("validCredentials").getString("username"),
                jsonObject.getJSONObject("validCredentials").getString("password"));
        productsDetailsPage = productsPage.clickProductTitle();

        String actualProductTitle = productsDetailsPage.pdProductTitle();
        String expectedProductTitle = propertiesTestData.getProperty("productTitle");
        Assert.assertEquals(actualProductTitle, expectedProductTitle);

        String actualProductDesc = productsDetailsPage.pdProductTitleDesc();
        String expectedProductDesc = propertiesTestData.getProperty("productTitleDesc");
        Assert.assertEquals(actualProductDesc, expectedProductDesc);

        String actualProductPrice = productsDetailsPage.pdProductPrice();
        String expectedProductPrice = propertiesTestData.getProperty("productPrice");
        Assert.assertEquals(actualProductPrice, expectedProductPrice);

        productsPage = productsDetailsPage.backToProductPage();
        settingsPage = productsPage.menuButton();
        loginPage = settingsPage.logout();
    }

    @AfterMethod
    public void afterMethod() {

    }

}
