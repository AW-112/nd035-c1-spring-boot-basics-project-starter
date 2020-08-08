package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomeCredential {
    @FindBy(id = "btn-credential-add")
    private WebElement credentialAdd;

    @FindBy(id = "credential-url")
    private WebElement credentialURL;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(className = "list-credential-url")
    private List<WebElement> credentialURLClass;

    @FindBy(className = "list-credential-username")
    private List<WebElement> credentialUsernameClass;

    @FindBy(className = "list-credential-password")
    private List<WebElement> credentialPasswordClass;

    @FindBy(className = "list-credential-edit")
    private List<WebElement> credentialEditClass;

    @FindBy(className = "list-credential-delete")
    private List<WebElement> credentialDeleteClass;

    public HomeCredential(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void assignCredentialData(String url, String username, String password) {
        this.credentialURL.clear();
        this.credentialUsername.clear();
        this.credentialPassword.clear();
        this.credentialURL.sendKeys(url);
        this.credentialUsername.sendKeys(username);
        this.credentialPassword.sendKeys(password);
    }

    public void submitCredential() {
        this.credentialUsername.submit();
    }

    public String getModalURL() {
        return this.credentialURL.getAttribute("value");
    }

    public String getModalUsername() {
        return this.credentialUsername.getAttribute("value");
    }

    public String getModalPassword() {
        return this.credentialPassword.getAttribute("value");
    }

    public String getRecentCredentialURL() {
        return this.credentialURLClass.get(this.credentialURLClass.size() - 1).getText();
    }

    public String getRecentCredentialUsername() {
        return this.credentialUsernameClass.get(this.credentialUsernameClass.size() - 1).getText();
    }

    public String getRecentCredentialPassword() {
        return this.credentialPasswordClass.get(this.credentialPasswordClass.size() - 1).getText();
    }

    public void credentialEditClick() {
        credentialEditClass.get(0).click();
    }

    public void credentialAddClick() {
        credentialAdd.click();
    }

    public void credentialDeleteClick() {
        credentialDeleteClass.get(0).click();
    }
}