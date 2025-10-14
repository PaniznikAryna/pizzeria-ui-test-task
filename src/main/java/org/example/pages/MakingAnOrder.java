package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MakingAnOrder {
    private WebDriver driver;

    public MakingAnOrder(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Переход на страницу оформления заказа")
    public void goToMakingAnOrder() {
        By basketLocator = By.xpath("//a[@href=\"http://pizzeria.skillbox.cc/checkout/\"]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(basketLocator)).click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(basketLocator)).click();
        }
    }

    @FindBy(xpath = "//input[@type=\"date\"]")
    private WebElement inputDate;

    @Step("Установка даты")
    public void setDate(String date) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(inputDate));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", inputDate, date);
    }


    @Step("Получение даты")
    public String getDate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(inputDate));
        return inputDate.getAttribute("value");
    }

    @FindBy(xpath = "//input[@id=\"billing_first_name\"]")
    private WebElement inputFirstName;

    @FindBy(xpath = "//input[@id=\"billing_last_name\"]")
    private WebElement inputLastName;

    @FindBy(xpath = "//input[@id=\"billing_address_1\"]")
    private WebElement inputAddress;

    @FindBy(xpath = "//input[@id=\"billing_city\"]")
    private WebElement inputCity;

    @FindBy(xpath = "//input[@id=\"billing_state\"]")
    private WebElement inputState;

    @FindBy(xpath = "//input[@id=\"billing_postcode\"]")
    private WebElement inputPostcode;

    @FindBy(xpath = "//input[@id=\"billing_phone\"]")
    private WebElement inputPhone;

    @FindBy(xpath = "//input[@id=\"billing_email\"]")
    private WebElement inputEmail;

    @Step("Заполнение формы заказа")
    public void setForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        inputFirstName.sendKeys("test");
        inputLastName.sendKeys("test");
        inputAddress.sendKeys("test");
        inputCity.sendKeys("test");
        inputState.sendKeys("test");
        inputPostcode.sendKeys("213342");
        inputPhone.sendKeys("+79991231231");
        inputEmail.clear();
        inputEmail.sendKeys("test@exemple.com");
        WebElement countryDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select2-selection--single")));
        countryDropdown.click();

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.select2-search__field")));
        searchInput.sendKeys("Belarus");

        WebElement result = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'select2-results__option') and text()='Belarus']")));
        result.click();
    }

    @FindBy(xpath = "//input[@id='payment_method_cod']")
    private WebElement radioPaymentOnDelivery;

    @Step("Выбор способа оплаты: оплата при доставке")
    public void selectPaymentOnDelivery() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(radioPaymentOnDelivery));
        radioPaymentOnDelivery.click();
    }

    @FindBy(xpath = "//input[@id='terms']")
    private WebElement checkboxTerms;

    @Step("Согласие с условиями использования сайта")
    public void acceptTermsAndConditions() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(checkboxTerms));
        if (!checkboxTerms.isSelected()) {
            checkboxTerms.click();
        }
    }

    @FindBy(xpath = "//button[@id=\"place_order\"]")
    private WebElement buttonMakingAnOrder;

    @Step("Нажатие на кнопку \"Оформить заказ\"")
    public void clickButtonMakingAnOrder(){
        buttonMakingAnOrder.click();
    }


}
