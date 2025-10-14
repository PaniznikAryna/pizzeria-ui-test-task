package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class PizzaSection {
    private WebDriver driver;

    public PizzaSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[@name='orderby']")
    private WebElement sortDropdown;

    @FindBy(xpath = "//button[text()='Применить']")
    private WebElement filterButton;

    @FindBy(xpath = "//ul[contains(@class,'products')]/li")
    private List<WebElement> pizzaItems;

    public void sortBy(String value) {
        Select select = new Select(sortDropdown);
        select.selectByValue(value);

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[contains(@class,'products')]/li")));
    }

    public void filterByPrice(int leftOffset, int rightOffset) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'price_slider_wrapper')]")));

        WebElement leftHandle = driver.findElement(By.xpath("(//span[contains(@class,'ui-slider-handle')])[1]"));
        WebElement rightHandle = driver.findElement(By.xpath("(//span[contains(@class,'ui-slider-handle')])[2]"));

        wait.until(ExpectedConditions.visibilityOf(leftHandle));
        wait.until(ExpectedConditions.visibilityOf(rightHandle));

        Actions actions = new Actions(driver);
        actions.clickAndHold(leftHandle).moveByOffset(leftOffset, 0).release().perform();
        actions.clickAndHold(rightHandle).moveByOffset(rightOffset, 0).release().perform();

        wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[contains(@class,'products')]/li")));
    }

    public List<Integer> getPizzaPrices() {
        return pizzaItems.stream().map(item -> Integer.parseInt(item.findElement(By.xpath(".//bdi")).getText().replaceAll("[^\\d]", "")) / 100).collect(Collectors.toList());
    }

    @FindBy(xpath = "//a[@data-product_id=\"425\"]")
    private WebElement buttonInBasket;

    public void clickButtonInBasket(){
        buttonInBasket.click();
    }

    @FindBy(xpath = "//a[@title=\"Подробнее\"]")
    private WebElement buttonMoreDetails;

    public void clickButtonMoreDetails(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(buttonMoreDetails));
        buttonMoreDetails.click();
    }

    @FindBy(xpath = "//td[@class='product-name']//a")
    private WebElement itemName;

    public String getItemName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(itemName));
        return itemName.getText();
    }
}
