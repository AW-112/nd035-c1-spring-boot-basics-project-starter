package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    @FindBy(id = "result-Success")
    private WebElement successMessage;

    @FindBy(id = "link-home")
    private WebElement linkHome;

    public ResultPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void linkHomeClick(){
        linkHome.click();
    }

    public String getSuccessMessage() {
        return this.successMessage.getText();
    }
}
