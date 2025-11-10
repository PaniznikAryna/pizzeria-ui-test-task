package com.pizzeria.testTask4.testPage;

import com.pizzeria.testTask4.BaseTest;
import com.pizzeria.testTask4.extensions.ScreenshotOnFailureExtension;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import com.pizzeria.pages.BasketPage;
import com.pizzeria.pages.LoginPage;
import com.pizzeria.pages.MenuPage;
import com.pizzeria.pages.PromoPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import io.qameta.allure.junit5.AllureJunit5;

import static com.pizzeria.utils.ConfigReader.get;


@ExtendWith(ScreenshotOnFailureExtension.class)
@ExtendWith({AllureJunit5.class, ScreenshotOnFailureExtension.class})
@Epic("Корзина")
@Feature("Работа с корзиной и оформлением заказа")
@DisplayName("Тесты для функциональности корзины")
public class BasketPageTest extends BaseTest {

    BasketPage basket;
    MenuPage menuPage;
    LoginPage loginPage;
    PromoPage promoPage;

    @BeforeEach
    void openBasketPage() {
        driver.get(get("url.basketPage"));
        basket = new BasketPage(driver);
        menuPage = new MenuPage(driver);
        loginPage = new LoginPage(driver);
        promoPage = new PromoPage(driver);
    }

    @Test
    @Story("Изменение количества товаров")
    @DisplayName("Добавление и удаление товаров из корзины")
    @Description("Проверяет, что при добавлении двух товаров и удалении одного — количество в корзине изменяется корректно")
    void testChangingCountOfItemsInBasket() {
        int countBefore = basket.getItemCount();

        driver.get(get("url.menuPage"));

        menuPage.addDrinkToBasket()
                .addDesertToBasket()
                .goToBasket();
        driver.navigate().refresh();

        int countAfter = basket.getItemCount();
        Assertions.assertEquals(countBefore + 2, countAfter, "Количество товаров не увеличилось на 2");

        countBefore = countAfter;
        basket.removeFirstItem();
        menuPage.goToBasket();
        driver.navigate().refresh();

        countAfter = basket.getItemCount();
        Assertions.assertEquals(countBefore - 1, countAfter, "Количество товаров не уменьшилось на 1");
    }


    @Test
    @Story("Обновление суммы")
    @DisplayName("Обновление суммы при изменении количества товара")
    @Description("Проверяет, что сумма в корзине корректно пересчитывается после изменения количества товара")
    void testUpdatingCostWhenTheContentChanges() throws InterruptedException {
        driver.get(get("url.menuPage"));
        menuPage.addDrinkToBasket()
                .goToBasket();
        driver.navigate().refresh();

        float amountBefore = basket.getAmount();

        basket.changeCountItem()
                .clickButtonUpdateBasket();

        menuPage.goToBasket();
        driver.navigate().refresh();

        float amountAfter = basket.getAmount();
        Assertions.assertEquals(amountAfter, amountBefore * 4, "Конечная сумма не равна начальной, умноженной на 4");
    }

    @Test
    @Story("Оформление заказа")
    @DisplayName("Переход к оплате для авторизованного пользователя")
    @Description("Проверяет, что авторизованный пользователь может перейти к оформлению заказа")
    void testGoingToPaymentForTheAuthorizedUser() {
        driver.get(get("url.accountPage"));
        loginPage.login(get("user.login"), get("user.password"))
                .goToMenu();

        driver.navigate().refresh();
        driver.get(get("url.menuPage"));

        menuPage.addDrinkToBasket()
                .goToBasket();
        basket = new BasketPage(driver);

        boolean isVisible = basket.clickButtonGoToPay()
                .isDisplayedButtonPlaceOrder();

        Assertions.assertTrue(isVisible, "Пользователь не авторизирован");

    }

    @Test
    @Story("Оформление заказа")
    @DisplayName("Переход к оплате для неавторизованного пользователя")
    @Description("Проверяет, что неавторизованный пользователь получает сообщение о необходимости авторизации")
    void testGoingToPaymentForTheUnauthorizedUser() {
        driver.get(get("url.menuPage"));
        menuPage.addDrinkToBasket()
                .goToBasket();
        basket = new BasketPage(driver);

        boolean isVisible = basket.clickButtonGoToPay()
                .isDisplayedLinkAuthorizedUser();

        Assertions.assertTrue(isVisible, "Сообщение о необходимости авторизации отсутствует");
    }

    @Test
    @Story("Промокоды")
    @DisplayName("Применение промокода из раздела 'Акции'")
    @Description("Проверяет, что промокод из раздела 'Акции' успешно применяется в корзине")
    void testUsingPromoCode() {
        driver.get(get("url.promoPage"));
        String coupon = promoPage.getPromotion();

        driver.get(get("url.menuPage"));
        menuPage.addDrinkToBasket()
                .addDrinkToBasket();

        driver.navigate().refresh();
        menuPage.goToBasket();
        basket = new BasketPage(driver);

        boolean isVisible = basket.applyCoupon(coupon)
                .isCouponAppliedMessageVisible();

        Assertions.assertTrue(isVisible, "Сообщение о применении купона не появилось");

    }
}
