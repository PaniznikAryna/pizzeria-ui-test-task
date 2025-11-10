package com.pizzeria.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class MyAccountPage {
    public static final String BUTTON_DATA_ACCOUNT_XPATH = "//li[@class=\"woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--edit-account\"]";
    public static final String INPUT_UPLOAD_FILE_XPATH = "//input[@id=\"uploadFile\"]";

    private WebDriver driver;
    private final Duration WAIT = Duration.ofSeconds(20);

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = BUTTON_DATA_ACCOUNT_XPATH)
    private WebElement buttonDataAccount;

    @Step("Нажатие на кнопку \"Данные аккаунта\"")
    public MyAccountPage clickButtonDataAccount() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(buttonDataAccount));
        buttonDataAccount.click();
        return this;
    }

    @FindBy(xpath = INPUT_UPLOAD_FILE_XPATH)
    private WebElement inputUploadFile;

    @Step("Загрузка файла test.jpg")
    public MyAccountPage uploadFile() {
        URL resource = getClass().getClassLoader().getResource("test.jpg");
        if (resource == null) {
            throw new IllegalArgumentException("Файл 'test.jpg' не найден в resources.");
        }

        String filePath = new File(resource.getFile()).getAbsolutePath();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block';", inputUploadFile);

        inputUploadFile.sendKeys(filePath);
        return this;
    }
}
