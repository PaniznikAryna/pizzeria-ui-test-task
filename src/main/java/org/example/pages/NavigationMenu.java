package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationMenu {
    private WebDriver driver;

    public NavigationMenu(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//li[@id=\"menu-item-389\"]")
    private WebElement menuButton;

    public void moveToMenuButton(){
        Actions action = new Actions(driver);
        action.moveToElement(menuButton).perform();
    }

    @FindBy(xpath = "//li[@id=\"menu-item-390\"]")
    private WebElement pizzaInMenuButton;

    public void clickPizzaInMenuButton(){
        pizzaInMenuButton.click();
    }

    public String getPizzaMenuLink() {
        WebElement link = pizzaInMenuButton.findElement(By.tagName("a"));
        return link.getAttribute("href");
    }

    @FindBy(xpath = "//li[@id=\"menu-item-391\"]")
    private WebElement desertInMenuButton;

    public void clickDesertInMenuButton(){
        desertInMenuButton.click();
    }

    public String getDesertMenuLink() {
        WebElement link = desertInMenuButton.findElement(By.tagName("a"));
        return link.getAttribute("href");
    }

    @FindBy(xpath = "//li[@id=\"menu-item-393\"]")
    private WebElement drinkInMenuButton;

    public void clickDrinkInMenuButton(){
        drinkInMenuButton.click();
    }

    public String getDrinkMenuLink() {
        WebElement link = drinkInMenuButton.findElement(By.tagName("a"));
        return link.getAttribute("href");
    }

}
