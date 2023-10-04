package com.qa.test.development.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductsDetailsPage extends ProductsPage{
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
    private WebElement productTitle;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
    private WebElement getProductTitleDesc;

    @AndroidFindBy(accessibility = "test-Price")
    private WebElement pdProductPrice;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-BACK TO PRODUCTS\"]/android.widget.TextView")
    private WebElement backToProductsPage;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Inventory item page\"]")
    private WebElement scrollableElement;

    public String pdProductTitle(){
       return attribute(productTitle,"text");
    }
    public String pdProductTitleDesc(){
        return attribute(getProductTitleDesc,"text");
    }
    public String pdProductPrice(){
        scrollableOld(scrollableElement,1.0);
        return attribute(pdProductPrice,"text");
    }
    public ProductsPage backToProductPage(){
        click(backToProductsPage);
        return new ProductsPage();
    }
}
