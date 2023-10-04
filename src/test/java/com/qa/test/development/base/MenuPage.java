package com.qa.test.development.base;

import com.qa.test.development.pages.SettingsPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MenuPage extends BaseClass{
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
    private WebElement menuButton;

    public SettingsPage menuButton(){
        click(menuButton);
        return new SettingsPage();
    }

}
