package com.pizzeria.testTask4.testPage;

import com.pizzeria.testTask4.BaseTest;
import com.pizzeria.testTask4.extensions.ScreenshotOnFailureExtension;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import com.pizzeria.pages.DeliveryAndPaymentPage;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.pizzeria.utils.ConfigReader.get;

@ExtendWith(ScreenshotOnFailureExtension.class)
@ExtendWith({AllureJunit5.class, ScreenshotOnFailureExtension.class})
@Epic("Доставка и оплата")
@Feature("Страница Доставка и оплата")
@DisplayName("Тест для страницы доставки и оплаты")
public class DeliveryAndPaymentPageTest extends BaseTest {

    DeliveryAndPaymentPage deliveryAndPaymentPage;

    @BeforeEach
    void openDeliveryAndPaymentPage() {
        driver.get(get("url.deliveryPage"));
        deliveryAndPaymentPage = new DeliveryAndPaymentPage(driver);
    }

    @Test
    @Story("Минимальная сумма заказа")
    @DisplayName("Проверка минимальной суммы заказа — 800 рублей")
    @Description("Проверяет, что на странице доставки указана минимальная сумма заказа в размере 800 рублей")
    void testMinimumOrderAmount() {
        boolean isVisible = deliveryAndPaymentPage.switchToIframeDeliveryAndPayment()
                .minOrderAmountIs800Rubles();

        Assertions.assertTrue(isVisible, "Текст не содержит '800 рублей'");
    }
}
