package org.example.test_task_4;

import io.qameta.allure.Description;
import org.example.pages.LoginPage;
import org.example.pages.MyAccount;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MyAccountTest {

    WebDriver driver;
    LoginPage loginPage;
    MyAccount myAccount;

    @BeforeEach
    void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://pizzeria.skillbox.cc/my-account/");
        loginPage = new LoginPage(driver);
        myAccount = new MyAccount(driver);
    }

    @Test
    @Description("Загрузка файла")
    void testUploadingFile(){
        loginPage.login("test1233314143", "testuser");

        myAccount.clickButtonDataAccount();
        driver.get("https://pizzeria.skillbox.cc/my-account/edit-account/");

        myAccount.uploadFile();

    }

    @AfterEach
    void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
