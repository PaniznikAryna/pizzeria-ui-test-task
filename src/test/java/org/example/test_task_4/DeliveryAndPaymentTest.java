package org.example.test_task_4;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.pages.DeliveryAndPayment;
import org.example.utils.DriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Epic("Доставка и оплата")
@Feature("Страница Доставка и оплата")
@DisplayName("Тест для страницы доставки и оплаты")
public class DeliveryAndPaymentTest {

    WebDriver driver;
    DeliveryAndPayment deliveryAndPayment;

    @BeforeEach
    void setUp(){
//        ChromeOptions chromeOptions = new ChromeOptions();
//        driver = new ChromeDriver(chromeOptions);
        driver = DriverFactory.createChromeDriver();
        driver.get("https://pizzeria.skillbox.cc/delivery/");
        deliveryAndPayment = new DeliveryAndPayment(driver);
    }

    @Test
    @Story("Минимальная сумма заказа")
    @DisplayName("Проверка минимальной суммы заказа — 800 рублей")
    @Description("Проверяет, что на странице доставки указана минимальная сумма заказа в размере 800 рублей")
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
