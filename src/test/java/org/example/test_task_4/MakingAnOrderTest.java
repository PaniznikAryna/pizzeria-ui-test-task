package org.example.test_task_4;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MakingAnOrderTest {
    //- Установка даты заказа +--
    //- Успешное оформление заказа с оплатой наличными +--

    WebDriver driver;

    @BeforeEach
    void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://pizzeria.skillbox.cc/checkout/");
    }

    @Test
    void testSettingTheOrderDate(){

    }

    @Test
    void testMakingTheOrderWithCashPayment(){

    }

    @AfterEach
    void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

}
