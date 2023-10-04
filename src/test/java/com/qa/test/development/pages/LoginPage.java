package com.qa.test.development.pages;

import com.qa.test.development.base.BaseClass;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseClass {

    @AndroidFindBy(accessibility = "test-Username")
    private WebElement userName;
    @AndroidFindBy(accessibility = "test-Password")
    private WebElement userPassword;
    @AndroidFindBy(accessibility = "test-LOGIN")
    private WebElement userLoginButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    private WebElement errorMessage;

    public LoginPage setUserName(String name) {
        clear(userName);
        sendKeys(userName, name);
        return this;
    }

    public LoginPage setPassword(String password) {
        clear(userPassword);
        sendKeys(userPassword, password);
        return this;
    }

    public ProductsPage loginButton() {
        click(userLoginButton);
        return new ProductsPage();
    }

    public ProductsPage loginPage(String username, String password) {
        setUserName(username);
        setPassword(password);
        return loginButton();
    }



    public String errorMessage() {
        return attribute(errorMessage, "text");
    }
}
