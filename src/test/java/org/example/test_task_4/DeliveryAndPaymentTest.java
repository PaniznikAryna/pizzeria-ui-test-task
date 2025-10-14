package org.example.test_task_4;

import io.qameta.allure.Description;
import org.example.pages.DeliveryAndPayment;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DeliveryAndPaymentTest {

    WebDriver driver;
    DeliveryAndPayment deliveryAndPayment;

    @BeforeEach
    void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://pizzeria.skillbox.cc/delivery/");
        deliveryAndPayment = new DeliveryAndPayment(driver);
    }

    @Description("Проверка минимальной суммы заказа (800 руб)")
    @Test
    void testMinimumOrderAmount(){
        deliveryAndPayment.switchToIframeDeliveryAndPayment();
        Assertions.assertTrue(deliveryAndPayment.minOrderAmountIs800Rubles(), "Текст не содержит '800 рублей'");
    }

    @AfterEach
    void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
