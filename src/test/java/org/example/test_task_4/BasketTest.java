package org.example.test_task_4;

import org.example.pages.BasketPage;
import org.example.pages.LoginPage;
import org.example.pages.MenuPage;
import org.example.pages.PromoPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BasketTest {

    WebDriver driver;
    BasketPage basket;
    MenuPage menuPage;
    LoginPage loginPage;
    PromoPage promoPage;


    @BeforeEach
    void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://pizzeria.skillbox.cc/cart/");
        basket = new BasketPage(driver);
        menuPage = new MenuPage(driver);
        loginPage = new LoginPage(driver);
        promoPage = new PromoPage(driver);
    }

    @Test
    void testChangingCountOfItemsInBasket() throws InterruptedException {
        int countBefore = basket.getItemCount();

        driver.get("https://pizzeria.skillbox.cc/product-category/menu/");
        menuPage.addDrinkToBasket();
        menuPage.addDesertToBasket();
        menuPage.goToBasket();

        int countAfter = basket.getItemCount();
        Assertions.assertEquals(countBefore + 2, countAfter, "Количество товаров не увеличилось на 2");

        countBefore = countAfter;
        basket.removeFirstItem();
        menuPage.goToBasket();

        countAfter = basket.getItemCount();
        Assertions.assertEquals(countBefore - 1, countAfter, "Количество товаров не уменьшилось на 1");
    }


    @Test
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
