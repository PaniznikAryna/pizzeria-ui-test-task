package com.pizzeria.pages;


import com.pizzeria.utils.Constant;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MakingAnOrderPage {
    public static final String CHECKOUT_LINK_XPATH = "//a[contains(@href,'/checkout/')]";
    public static final String DATE_INPUT_XPATH = "//input[@type='date']";

    public static final String BILLING_FIRST_NAME_XPATH = "//input[@id='billing_first_name']";
    public static final String BILLING_LAST_NAME_XPATH = "//input[@id='billing_last_name']";
    public static final String BILLING_ADDRESS_1_XPATH = "//input[@id='billing_address_1']";
    public static final String BILLING_CITY_XPATH = "//input[@id='billing_city']";
    public static final String BILLING_STATE_XPATH = "//input[@id='billing_state']";
    public static final String BILLING_POSTCODE_XPATH = "//input[@id='billing_postcode']";
    public static final String BILLING_PHONE_XPATH = "//input[@id='billing_phone']";
    public static final String BILLING_EMAIL_XPATH = "//input[@id='billing_email']";

    public static final String COUNTRY_DROPDOWN_CSS = ".select2-selection--single";
    public static final String COUNTRY_SEARCH_INPUT_CSS = "input.select2-search__field";
    public static final String COUNTRY_RESULT_XPATH_TEMPLATE = "//li[contains(@class,'select2-results__option') and normalize-space(text())='%s']";

    public static final String PAYMENT_METHOD_CASH_XPATH = "//input[@id='payment_method_cod']";
    public static final String TERMS_CHECKBOX_XPATH = "//input[@id='terms']";
    public static final String PLACE_ORDER_BUTTON_XPATH = "//button[@id='place_order']";

    private final WebDriver driver;
    private final Duration WAIT = Duration.ofSeconds(10);

    public MakingAnOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Переход на страницу оформления заказа")
    public MakingAnOrderPage goToMakingAnOrder() {
        By basketLocator = By.xpath(CHECKOUT_LINK_XPATH);
        WebDriverWait wait = new WebDriverWait(driver, WAIT);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(basketLocator)).click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(basketLocator)).click();
        }
        return this;
    }

    @Step("Установка даты")
    public MakingAnOrderPage setDate(String date) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        WebElement dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DATE_INPUT_XPATH)));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'))", dateInput, date);
        return this;
    }

    @Step("Получение даты")
    public String getDate() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        WebElement dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DATE_INPUT_XPATH)));
        return dateInput.getAttribute("value");
    }

    @FindBy(xpath = BILLING_FIRST_NAME_XPATH)
    private WebElement inputFirstName;

    @FindBy(xpath = BILLING_LAST_NAME_XPATH)
    private WebElement inputLastName;

    @FindBy(xpath = BILLING_ADDRESS_1_XPATH)
    private WebElement inputAddress;

    @FindBy(xpath = BILLING_CITY_XPATH)
    private WebElement inputCity;

    @FindBy(xpath = BILLING_STATE_XPATH)
    private WebElement inputState;

    @FindBy(xpath = BILLING_POSTCODE_XPATH)
    private WebElement inputPostcode;

    @FindBy(xpath = BILLING_PHONE_XPATH)
    private WebElement inputPhone;

    @FindBy(xpath = BILLING_EMAIL_XPATH)
    private WebElement inputEmail;

    @Step("Заполнение формы заказа")
    public MakingAnOrderPage setForm() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);

        wait.until(ExpectedConditions.visibilityOf(inputFirstName));
        inputFirstName.clear();
        inputFirstName.sendKeys(Constant.FIRST_NAME);

        inputLastName.clear();
        inputLastName.sendKeys(Constant.LAST_NAME);

        inputAddress.clear();
        inputAddress.sendKeys(Constant.ADDRESS);

        inputCity.clear();
        inputCity.sendKeys(Constant.CITY);

        inputState.clear();
        inputState.sendKeys(Constant.STATE);

        inputPostcode.clear();
        inputPostcode.sendKeys(Constant.POSTCODE);

        inputPhone.clear();
        inputPhone.sendKeys(Constant.PHONE);

        inputEmail.clear();
        inputEmail.sendKeys(Constant.EMAIL);

        WebElement countryDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(COUNTRY_DROPDOWN_CSS)));
        countryDropdown.click();

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(COUNTRY_SEARCH_INPUT_CSS)));
        searchInput.clear();
        searchInput.sendKeys(Constant.COUNTRY);

        String countryResultXpath = String.format(COUNTRY_RESULT_XPATH_TEMPLATE, Constant.COUNTRY);
        WebElement result = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(countryResultXpath)));
        result.click();

        return this;
    }

    @FindBy(xpath = PAYMENT_METHOD_CASH_XPATH)
    private WebElement radioPaymentOnDelivery;

    @Step("Выбор способа оплаты: оплата при доставке")
    public MakingAnOrderPage selectPaymentOnDelivery() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(radioPaymentOnDelivery));
        if (!radio.isSelected()) {
            radio.click();
        }
        return this;
    }

    @FindBy(xpath = TERMS_CHECKBOX_XPATH)
    private WebElement checkboxTerms;

    @Step("Согласие с условиями использования сайта")
    public MakingAnOrderPage acceptTermsAndConditions() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(checkboxTerms));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        return this;
    }

    @FindBy(xpath = PLACE_ORDER_BUTTON_XPATH)
    private WebElement buttonMakingAnOrder;

    @Step("Нажатие на кнопку \"Оформить заказ\"")
    public MakingAnOrderPage clickButtonMakingAnOrder() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        WebElement placeOrderBtn = wait.until(ExpectedConditions.elementToBeClickable(buttonMakingAnOrder));
        placeOrderBtn.click();
        return this;
    }

    @Step("Проверка появления сообщения об успешном заказе")
    public boolean isOrderSuccessMessageVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT);
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constant.ORDER_SUCCESS_MESSAGE_XPATH)));
            return message.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Проверка отображения информации об оплате наличными")
    public boolean isCashPaymentInfoVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT);
            WebElement info = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constant.CASH_PAYMENT_INFO_XPATH)));
            return info.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
