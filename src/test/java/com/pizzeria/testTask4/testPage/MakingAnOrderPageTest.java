package com.pizzeria.testTask4.testPage;

import com.pizzeria.testTask4.BaseTest;
import com.pizzeria.testTask4.extensions.ScreenshotOnFailureExtension;
import com.pizzeria.utils.Constant;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import com.pizzeria.pages.BasketPage;
import com.pizzeria.pages.LoginPage;
import com.pizzeria.pages.MakingAnOrderPage;
import com.pizzeria.pages.MenuPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.junit5.AllureJunit5;

import static com.pizzeria.utils.ConfigReader.get;

import java.time.Duration;
import java.time.LocalDate;

@ExtendWith(ScreenshotOnFailureExtension.class)
@ExtendWith({AllureJunit5.class, ScreenshotOnFailureExtension.class})
@Epic("Оформление заказа")
@Feature("Страница Оформления заказа")
@DisplayName("Тесты оформления заказа")
public class MakingAnOrderPageTest extends BaseTest {
    MakingAnOrderPage makingAnOrderPage;
    LoginPage loginPage;
    MenuPage menuPage;
    BasketPage basket;

    @BeforeEach
    void openMakingAnOrderPage() {
        driver.get(get("url.checkoutPage"));
        makingAnOrderPage = new MakingAnOrderPage(driver);
        loginPage = new LoginPage(driver);
        menuPage = new MenuPage(driver);
        basket = new BasketPage(driver);
    }

    @Test
    @Story("Установка даты")
    @DisplayName("Проверка установки даты доставки на завтра")
    @Description("Авторизация, добавление товара в корзину, переход к оформлению и установка даты доставки")
    void testSettingTheOrderDate() {
        driver.get(get("url.accountPage"));
        loginPage.login(get("user.login"), get("user.password"))
                .goToMenu();

        driver.navigate().refresh();

        menuPage.addDrinkToBasket()
                .goToBasket();

        driver.navigate().refresh();
        basket = new BasketPage(driver);

        makingAnOrderPage.goToMakingAnOrder();

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String expectedDate = tomorrow.toString();

        String actualDate = makingAnOrderPage.setDate(expectedDate)
                .getDate();

        Assertions.assertEquals(expectedDate, actualDate, "Дата в поле не соответствует ожидаемой");
    }

    @Test
    @Story("Оформление заказа с оплатой наличными")
    @DisplayName("Проверка успешного оформления заказа с оплатой при доставке")
    @Description("Авторизация, добавление товара, заполнение формы, выбор оплаты наличными и проверка успешного оформления")
    void testMakingTheOrderWithCashPayment() {
        driver.get(get("url.accountPage"));
        loginPage.login(get("user.login"), get("user.password"))
                .goToMenu();

        driver.navigate().refresh();

        menuPage.addDrinkToBasket()
                .goToBasket();

        driver.navigate().refresh();
        basket = new BasketPage(driver);

        makingAnOrderPage.goToMakingAnOrder()
                .setForm()
                .selectPaymentOnDelivery()
                .acceptTermsAndConditions()
                .clickButtonMakingAnOrder();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(Constant.ORDER_SUCCESS_MESSAGE_XPATH)
        ));

        WebElement paymentInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(Constant.CASH_PAYMENT_INFO_XPATH)
        ));

        Assertions.assertTrue(successMessage.isDisplayed(), "Сообщение о получении заказа не найдено");
        Assertions.assertTrue(paymentInfo.isDisplayed(), "Информация об оплате наличными не найдена");
    }
}
