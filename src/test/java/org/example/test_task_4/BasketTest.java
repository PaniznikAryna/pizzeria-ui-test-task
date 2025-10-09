package org.example.test_task_4;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BasketTest {
    //- Изменение количества товаров (+/-) +--
    //- Обновление суммы при изменении содержимого +--
    //- Переход к оплате (для авторизованного пользователя) +--
    //- Применение промокода (из раздела "Акции") +--

    WebDriver driver;

    @BeforeEach
    void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://pizzeria.skillbox.cc/cart/");
    }

    @Test
    void testIncreasingProductInTheBasket(){

    }

    @Test
    void testReducingProductInTheBasket(){

    }

    @Test
    void testUpdatingCostWhenTheContentChanges(){

    }

    @Test
    void testGoingToPaymentForTheAuthorizedUser(){

    }

    @Test
    void testGoingToPaymentForTheUnauthorizedUser(){

    }

    @Test
    void testUsingPromoCode(){

    }

    @AfterEach
    void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }


}
