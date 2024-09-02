package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final JavascriptExecutor js;
    @FindBy(id = "inputUsername")
    private WebElement inputUsername;
    @FindBy(id = "inputPassword")
    private WebElement inputPassword;
    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void setUsername(String username) {
        js.executeScript("arguments[0].value='" + username + "';", inputUsername);
    }

    public void setPassword(String password) {
        js.executeScript("arguments[0].value='" + password + "';", inputPassword);
    }

    public void login() {
        js.executeScript("arguments[0].click();", loginButton);
    }

}
