package org.example.test_task_4;

import io.qameta.allure.Description;
import org.example.pages.BonusProgram;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BonusProgramTest {

    WebDriver driver;
    BonusProgram bonusProgram;


    @BeforeEach
    void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://pizzeria.skillbox.cc/bonus/");
        bonusProgram = new BonusProgram(driver);
    }

    @Test
    @Description("Успешное оформление карты")
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
