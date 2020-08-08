package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomeNote {
    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(className = "list-notes-title")
    private List<WebElement> noteTitleClass;

    @FindBy(className = "list-notes-description")
    private List<WebElement> noteDescriptionClass;

    @FindBy(className = "list-notes-edit")
    private List<WebElement> noteEditClass;

    @FindBy(className = "list-notes-delete")
    private List<WebElement> noteDeleteClass;

    @FindBy(id = "btn-note-add")
    private WebElement noteAdd;

    public HomeNote(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void assignNoteData(String noteTitle, String noteDescription) {
        this.noteTitle.clear();
        this.noteDescription.clear();
        this.noteTitle.sendKeys(noteTitle);
        this.noteDescription.sendKeys(noteDescription);
    }

    public void submitNote() {
        this.noteDescription.submit();
    }

    public String getNoteTitle() {
        return this.noteTitle.getAttribute("value");
    }

    public String getNoteDescription() {
        return this.noteDescription.getAttribute("value");
    }

    public String getRecentNoteTitle() {
        return this.noteTitleClass.get(this.noteDescriptionClass.size() - 1).getText();
    }

    public String getRecentNoteDescription() {
        return this.noteDescriptionClass.get(this.noteDescriptionClass.size() - 1).getText();
    }

    public void noteEditClick() {
        noteEditClass.get(0).click();
    }

    public void noteAddClick() {
        this.noteAdd.click();
    }

    public void noteDeleteClick() {
        noteDeleteClass.get(0).click();
    }
}