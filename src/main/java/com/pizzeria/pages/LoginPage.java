package com.pizzeria.pages;

import com.pizzeria.utils.Constant;
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
    private final Duration WAIT = Duration.ofSeconds(10);

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = Constant.LOGIN_USER_NAME_XPATH)
    private WebElement loginUserName;

    @FindBy(xpath = Constant.LOGIN_PASSWORD_XPATH)
    private WebElement loginPassword;

    @FindBy(xpath = Constant.BUTTON_LOGIN_XPATH)
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
                By.xpath(Constant.MENU_LINK_XPATH_CONTAINS)
        ));
        menuLink.click();
        wait.until(ExpectedConditions.urlContains(Constant.MENU_URL_PATH));
        return this;
    }

}


