package com.pizzeria.pages;

import com.pizzeria.utils.Constant;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BonusProgramPage {
    private WebDriver driver;
    private final Duration WAIT = Duration.ofSeconds(30);

    public BonusProgramPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = Constant.BONUS_USER_NAME_XPATH)
    private WebElement bonusUserName;

    @Step("Заполнение поля \"Имя\"")
    public BonusProgramPage FillingUserName() {
        bonusUserName.sendKeys("Aryna");
        return this;
    }

    @FindBy(xpath = Constant.BONUS_PHONE_XPATH)
    private WebElement bonusPhone;

    @Step("Заполнение поля \"Телефон\"")
    public BonusProgramPage FillingPhone() {
        bonusPhone.sendKeys(Constant.PHONE);
        return this;
    }

    @FindBy(xpath = Constant.BUTTON_ISSUE_CARD_XPATH)
    private WebElement buttonIssueCard;

    @Step("Отправить данные для оформления карты")
    public BonusProgramPage clickButtonIssueCard() {
        buttonIssueCard.click();
        return this;
    }

    @Step("Закрытие всплывающего окна")
    public BonusProgramPage closeAlert() {
        driver.switchTo().alert().accept();
        return this;
    }

    @FindBy(xpath = Constant.CARD_HAS_BEEN_ISSUED_XPATH)
    private WebElement cardHasBeenIssued;

    @Step("Проверка успешно ли оформлена карта")
    public boolean displayH3WithCardHasBeenIssued() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(cardHasBeenIssued));
        return cardHasBeenIssued.isDisplayed();
    }

}
