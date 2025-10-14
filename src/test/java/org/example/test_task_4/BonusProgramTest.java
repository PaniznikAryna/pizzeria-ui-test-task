package org.example.test_task_4;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.pages.BonusProgram;
import org.example.utils.DriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Epic("Бонусная программа")
@Feature("Оформление бонусной карты")
@DisplayName("Тест для страницы бонусной программы")
public class BonusProgramTest {

    WebDriver driver;
    BonusProgram bonusProgram;


    @BeforeEach
    void setUp(){
//        ChromeOptions chromeOptions = new ChromeOptions();
//        driver = new ChromeDriver(chromeOptions);
        driver = DriverFactory.createChromeDriver();
        driver.get("https://pizzeria.skillbox.cc/bonus/");
        bonusProgram = new BonusProgram(driver);
    }

    @Test
    @Story("Оформление карты")
    @DisplayName("Успешное оформление бонусной карты")
    @Description("Проверяет, что пользователь может успешно оформить бонусную карту, заполнив имя и телефон")
    void testMakingCard(){
        bonusProgram.FillingUserName();
        bonusProgram.FillingPhone();

        bonusProgram.clickButtonIssueCard();
        bonusProgram.closeAlert();

        Assertions.assertTrue(bonusProgram.displayH3WithCardHasBeenIssued(), "Карта бонуса не была оформлена");
    }

    @AfterEach
    void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
