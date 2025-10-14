package org.example.test_task_4;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.pages.BasketPage;
import org.example.pages.LoginPage;
import org.example.pages.MenuPage;
import org.example.pages.PromoPage;
import org.example.utils.DriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Epic("Корзина")
@Feature("Работа с корзиной и оформлением заказа")
@DisplayName("Тесты для функциональности корзины")
public class BasketTest {

    WebDriver driver;
    BasketPage basket;
    MenuPage menuPage;
    LoginPage loginPage;
    PromoPage promoPage;


    @BeforeEach
    void setUp(){
//        ChromeOptions chromeOptions = new ChromeOptions();
//        driver = new ChromeDriver(chromeOptions);
        driver = DriverFactory.createChromeDriver();        driver.get("https://pizzeria.skillbox.cc/cart/");
        basket = new BasketPage(driver);
        menuPage = new MenuPage(driver);
        loginPage = new LoginPage(driver);
        promoPage = new PromoPage(driver);
    }


    @Test
    @Story("Изменение количества товаров")
    @DisplayName("Добавление и удаление товаров из корзины")
    @Description("Проверяет, что при добавлении двух товаров и удалении одного — количество в корзине изменяется корректно")
    void testChangingCountOfItemsInBasket(){
        int countBefore = basket.getItemCount();

        driver.get("https://pizzeria.skillbox.cc/product-category/menu/");

        menuPage.addDrinkToBasket();
        menuPage.addDesertToBasket();
        menuPage.goToBasket();
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
        driver.get("https://pizzeria.skillbox.cc/product-category/menu/");
        menuPage.addDrinkToBasket();
        menuPage.goToBasket();
        menuPage.goToBasket();

        float amountBefore = basket.getAmount();

        basket.changeCountItem();

        basket.clickButtonUpdateBasket();

        float amountAfter = basket.getAmount();
        Assertions.assertEquals(amountAfter, amountBefore*4, "Конечная сумма не равна начальной, умноженной на 4");
    }

    @Test
    @Story("Оформление заказа")
    @DisplayName("Переход к оплате для авторизованного пользователя")
    @Description("Проверяет, что авторизованный пользователь может перейти к оформлению заказа")
    void testGoingToPaymentForTheAuthorizedUser() throws InterruptedException {
        driver.get("https://pizzeria.skillbox.cc/my-account/");
        loginPage.login("test1233314143", "testuser");

        loginPage.goToMenu();
        menuPage.addDrinkToBasket();
        Thread.sleep(1000);
        menuPage.goToBasket();
        basket = new BasketPage(driver);

        basket.clickButtonGoToPay();
        Assertions.assertTrue(basket.isDisplayedButtonPlaceOrder(), "Пользователь не авторизирован");
    }

    @Test
    @Story("Оформление заказа")
    @DisplayName("Переход к оплате для неавторизованного пользователя")
    @Description("Проверяет, что неавторизованный пользователь получает сообщение о необходимости авторизации")
    void testGoingToPaymentForTheUnauthorizedUser() throws InterruptedException {
        driver.get("https://pizzeria.skillbox.cc/product-category/menu/");
        menuPage.addDrinkToBasket();
        Thread.sleep(1000);
        menuPage.goToBasket();
        basket = new BasketPage(driver);

        basket.clickButtonGoToPay();
        Assertions.assertTrue(basket.isDisplayedLinkAuthorizedUser(), "Сообщение о необходимости авторизации отсутствует");
    }

    @Test
    @Story("Промокоды")
    @DisplayName("Применение промокода из раздела 'Акции'")
    @Description("Проверяет, что промокод из раздела 'Акции' успешно применяется в корзине")
    void testUsingPromoCode() throws InterruptedException {
        driver.get("https://pizzeria.skillbox.cc/promo/");
        String coupon = promoPage.getPromotion();
        System.out.println(coupon);

        driver.get("https://pizzeria.skillbox.cc/product-category/menu/");
        menuPage.addDrinkToBasket();
        Thread.sleep(1000);
        menuPage.goToBasket();
        basket = new BasketPage(driver);

        basket.applyCoupon(coupon);
        Assertions.assertTrue(basket.isCouponAppliedMessageVisible(), "Сообщение о применении купона не появилось");

    }

    @AfterEach
    void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
