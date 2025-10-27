package com.pizzeria.pages;

import com.pizzeria.utils.Constant;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeliveryAndPaymentPage {
    private WebDriver driver;
    private final Duration WAIT = Duration.ofSeconds(30);

    public DeliveryAndPaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = Constant.IFRAME_DELIVERY_AND_PAYMENT_XPATH)
    private WebElement iframeDeliveryAndPayment;

    @Step("Переключаемся в iframe")
    public DeliveryAndPaymentPage switchToIframeDeliveryAndPayment() {
        driver.switchTo().frame(iframeDeliveryAndPayment);
        return this;
    }

    @FindBy(xpath = Constant.MIN_ORDER_AMOUNT_XPATH)
    private WebElement minOrderAmount;

    @Step("Проверка того, что минимальная сумма заказа равно 800 рублей")
    public boolean minOrderAmountIs800Rubles() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(minOrderAmount));

        return minOrderAmount.getText().contains("800 рублей");
    }
}
