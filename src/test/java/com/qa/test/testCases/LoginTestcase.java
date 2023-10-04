package com.qa.test.testCases;

import com.qa.test.development.base.BaseClass;
import com.qa.test.development.pages.LoginPage;
import com.qa.test.development.pages.ProductsPage;

import com.qa.test.development.pages.SettingsPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.*;

public class LoginTestcase extends BaseClass {
    LoginPage loginPage;
    ProductsPage productsPage;
    JSONObject jsonObject;
    InputStream dataInputStream;
    SettingsPage settingsPage;

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

    @Test
    public void invalidUsername() {
        loginPage.setUserName(jsonObject.getJSONObject("invalidUsername").getString("username"));
        loginPage.setPassword(jsonObject.getJSONObject("invalidUsername").getString("password"));
        loginPage.loginButton();
        String actual = loginPage.errorMessage();
        String expected = propertiesTestData.getProperty("errorMessage");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void invalidPassword() {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce11");
        loginPage.loginButton();
        String actual = loginPage.errorMessage();
        String expected = propertiesTestData.getProperty("errorMessage");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void invalidUsernameAndPassword() {
        loginPage.setUserName("standard_user11");
        loginPage.setPassword("secret_sauce11");
        loginPage.loginButton();
        String actual = loginPage.errorMessage();
        String expected = propertiesTestData.getProperty("errorMessage");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void validCredentials() {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        productsPage = loginPage.loginButton();
        String actual = productsPage.verifyLogin();
        String expected = propertiesTestData.getProperty("productsTitle");
        Assert.assertEquals(actual, expected);
        settingsPage=productsPage.menuButton();
        loginPage=settingsPage.logout();
    }
}
