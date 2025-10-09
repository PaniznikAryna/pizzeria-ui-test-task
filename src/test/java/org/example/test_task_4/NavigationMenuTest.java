package org.example.test_task_4;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class NavigationMenuTest {
    //- Переход по всем разделам:
    //  - Меню -> Пицца +--
    //  - Меню -> Десерты +--
    //  - Меню -> Напитки +--

    WebDriver driver;

    @BeforeEach
    void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://pizzeria.skillbox.cc/");
    }

    @Test
    void testSwitchingFromMenuToPizza(){

    }

    @Test
    void testSwitchingFromMenuToDesserts(){

    }

    @Test
    void testSwitchingFromMenuToDrinks(){

    }

    @AfterEach
    void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
