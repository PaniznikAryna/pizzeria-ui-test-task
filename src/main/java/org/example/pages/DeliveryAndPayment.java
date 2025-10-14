package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeliveryAndPayment {
    private WebDriver driver;

    public DeliveryAndPayment(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//iframe")
    private WebElement iframeDeliveryAndPayment;

    @Step("Переключаемся в iframe")
    public void switchToIframeDeliveryAndPayment() {
        driver.switchTo().frame(iframeDeliveryAndPayment);
    }

    @FindBy(xpath = "//li[contains(.,'Минимальная сумма заказа')]")
    private WebElement minOrderAmount;

    @Step("Проверка того, что минимальная сумма заказа равно 800 рублей")
    public boolean minOrderAmountIs800Rubles() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(minOrderAmount));

        return minOrderAmount.getText().contains("800 рублей");
    }
}
