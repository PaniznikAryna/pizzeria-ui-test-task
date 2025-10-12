package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BonusProgram {
    private WebDriver driver;

    public BonusProgram(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id=\"bonus_username\"]")
    private WebElement bonusUserName;

    public void FillingUserName(){
        bonusUserName.sendKeys("Aryna");
    }

    @FindBy(xpath = "//input[@id=\"bonus_phone\"]")
    private WebElement bonusPhone;

    public void FillingPhone(){
        bonusPhone.sendKeys("+79991231231");
    }

    @FindBy(xpath = "//button[@class=\"woocommerce-Button woocommerce-button button woocommerce-form-register__submit\"]")
    private WebElement buttonIssueCard;

    public void clickButtonIssueCard(){
        buttonIssueCard.click();
    }

    public void closeAlert() {
        driver.switchTo().alert().accept();
    }

    @FindBy(xpath = "//h3[starts-with(text(),'Ваша карта оформлена!')]")
    private WebElement  cardHasBeenIssued;

    public boolean displayH3WithCardHasBeenIssued(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(cardHasBeenIssued));
        return cardHasBeenIssued.isDisplayed();
    }



}
