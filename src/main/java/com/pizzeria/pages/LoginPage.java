package com.pizzeria.pages;

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
    public static final String LOGIN_USER_NAME_XPATH = "//input[@id=\"username\"]";
    public static final String LOGIN_PASSWORD_XPATH = "//input[@id=\"password\"]";
    public static final String BUTTON_LOGIN_XPATH = "//button[@name=\"login\"]";
    public static final String MENU_URL_PATH = "/product-category/menu";
    public static final String MENU_LINK_XPATH_CONTAINS = "//a[contains(@href,'product-category/menu')]";

    private WebDriver driver;
    private final Duration WAIT = Duration.ofSeconds(10);

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = LOGIN_USER_NAME_XPATH)
    private WebElement loginUserName;

    @FindBy(xpath = LOGIN_PASSWORD_XPATH)
    private WebElement loginPassword;

    @FindBy(xpath = BUTTON_LOGIN_XPATH)
    private WebElement buttonLogin;

    @Step("Вход в тестовый аккаунт")
    public LoginPage login(String userName, String password) {
        loginUserName.sendKeys(userName);
        loginPassword.sendKeys(password);
        buttonLogin.click();
        return this;
    }

    @Step("Переход на страницу \"Меню\" через клик")
    public LoginPage goToMenu() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        WebElement menuLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(MENU_LINK_XPATH_CONTAINS)
        ));
        menuLink.click();
        wait.until(ExpectedConditions.urlContains(MENU_URL_PATH));
        return this;
    }

}
