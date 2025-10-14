package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id=\"username\"]")
    private WebElement loginUserName;

    @FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement loginPassword;

    @FindBy(xpath = "//button[@name=\"login\"]")
    private WebElement buttonLogin;

    @Step("Вход в тестовый аккаунт")
    public void login(String userName, String password){
        loginUserName.sendKeys(userName);
        loginPassword.sendKeys(password);
        buttonLogin.click();
    }

    @Step("Переход на страницу \"Меню\"")
    public void goToMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement basketLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href=\"http://pizzeria.skillbox.cc/product-category/menu/\"]")
        ));
        basketLink.click();
    }
}


