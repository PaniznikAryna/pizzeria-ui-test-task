package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void login(String userName, String password){
        loginUserName.sendKeys(userName);
        loginPassword.sendKeys(password);
        buttonLogin.click();
    }

}


