package org.example.pages;

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

public class MyAccount {
    private WebDriver driver;

    public MyAccount(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@class=\"woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--edit-account\"]")
    private WebElement buttonDataAccount;

    @Step("Нажатие на кнопку \"Данные аккаунта\"")
    public void clickButtonDataAccount(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(buttonDataAccount));
        buttonDataAccount.click();
    }

    @FindBy(xpath = "//input[@id=\"uploadFile\"]")
    private WebElement inputUploadFile;

    @Step("Загрузка файла test.jpg")
    public void uploadFile() {
        URL resource = getClass().getClassLoader().getResource("test.jpg");
        if (resource == null) {
            throw new IllegalArgumentException("Файл 'test.jpg' не найден в resources.");
        }

        String filePath = new File(resource.getFile()).getAbsolutePath();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block';", inputUploadFile);

        inputUploadFile.sendKeys(filePath);
    }


}
