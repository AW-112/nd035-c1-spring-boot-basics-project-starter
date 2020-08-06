package com.udacity.jwdnd.c1.review;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement buttonSubmit;

    @FindBy(id = "success-msg")
    private WebElement successMessage;

    @FindBy(id = "error-msg")
    private WebElement errorMessage;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void assignSignupFields(String firstName, String lastName, String userName, String password) {
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputUsername.sendKeys(userName);
        inputPassword.sendKeys(password);
    }

    public String getInputFirstName() { return inputFirstName.getAttribute("value"); }

    public String getInputLastName() { return inputLastName.getAttribute("value"); }

    public String getInputUserName() { return inputUsername.getAttribute("value"); }

    public String getInputPassword() { return inputPassword.getAttribute("value"); }

    public String getErrorMessage() { return errorMessage.getText(); }

    public String getSuccessMessage() { return successMessage.getText(); }

    public void submitForm() { buttonSubmit.click(); }
}
