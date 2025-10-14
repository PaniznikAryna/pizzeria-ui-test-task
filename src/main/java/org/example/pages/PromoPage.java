package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PromoPage {
    private WebDriver driver;

    public PromoPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//strong")
    private WebElement coupon;

    @Step("Получение промокода")
    public String getPromotion(){
        return coupon.getText();
    }
}
