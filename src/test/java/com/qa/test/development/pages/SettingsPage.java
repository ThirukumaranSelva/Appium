package com.qa.test.development.pages;

import com.qa.test.development.base.BaseClass;
import com.qa.test.development.base.MenuPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SettingsPage extends BaseClass {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-LOGOUT\"]/android.widget.TextView")
    private WebElement logout;

    public LoginPage logout(){
        click(logout);
        return new LoginPage();
    }
}
