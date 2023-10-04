package com.qa.test.development.pages;

import com.qa.test.development.base.BaseClass;
import com.qa.test.development.base.MenuPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductsPage extends MenuPage {
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
    private WebElement productsTitle;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
    private WebElement getProductTitle;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
    private WebElement productPrice;
    public String verifyLogin(){
       return attribute(productsTitle,"text");
    }
    public String productTitle(){
        return attribute(getProductTitle, "text");
    }
    public String productPrice(){
        return attribute(productPrice,"text");
    }
    public ProductsDetailsPage clickProductTitle(){
        click(getProductTitle);
        return new ProductsDetailsPage();
    }
}
