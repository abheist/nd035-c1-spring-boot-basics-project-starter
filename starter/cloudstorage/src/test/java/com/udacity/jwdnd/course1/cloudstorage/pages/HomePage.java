package com.udacity.jwdnd.course1.cloudstorage.pages;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriverWait wait;
    private final JavascriptExecutor js;
    @FindBy(id = "btn-logout")
    private WebElement logoutButton;
    @FindBy(id = "fileUpload")
    private WebElement fileUpload;
    @FindBy(id = "btn-add-new-note")
    private WebElement btnAddNewNote;
    @FindBy(id = "btn-add-new-credential")
    private WebElement btnAddNewCredential;
    @FindBy(id = "note-title")
    private WebElement txtNoteTitle;
    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;
    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;
    @FindBy(id = "note-description")
    private WebElement txtNoteDescription;
    @FindBy(id = "btn-note-save-changes")
    private WebElement btnNoteSaveChanges;
    @FindBy(id = "note-table-title")
    private WebElement tableNoteTitle;
    @FindBy(id = "note-table-description")
    private WebElement tableNoteDescription;
    @FindBy(id = "edit-note-button")
    private WebElement btnEditNote;
    @FindBy(id = "edit-credential-button")
    private WebElement btnEditCredential;
    @FindBy(id = "note-description")
    private WebElement txtModifyNoteDescription;
    @FindBy(id = "delete-note-link")
    private WebElement ancDeleteNote;
    @FindBy(id = "delete-credential-link")
    private WebElement aDeleteCredential;
    @FindBy(id = "credential-url")
    private WebElement txtCredentialUrl;
    @FindBy(id = "credential-username")
    private WebElement txtCredentialUsername;
    @FindBy(id = "credential-password")
    private WebElement txtCredentialPassword;
    @FindBy(id = "credential-save-changes-button")
    private WebElement btnCredentialSaveChanges;
    @FindBy(id = "credential-table-url")
    private WebElement tblCredentialUrl;
    @FindBy(id = "credential-table-username")
    private WebElement tblCredentialUsername;
    @FindBy(id = "credential-table-password")
    private WebElement tblCredentialPassword;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 500);
    }

    public void logout() {
        js.executeScript("arguments[0].click();", logoutButton);
    }

    public void editNote() {
        js.executeScript("arguments[0].click();", btnEditNote);
    }

    public void editCredential() {
        js.executeScript("arguments[0].click();", btnEditCredential);
    }

    public void deleteNote() {
        js.executeScript("arguments[0].click();", ancDeleteNote);
    }

    public void deleteCredential() {
        js.executeScript("arguments[0].click();", aDeleteCredential);
    }

    public void uploadFile() {
        js.executeScript("arguments[0].click();", fileUpload);
    }

    public void addNewNote() {
        js.executeScript("arguments[0].click();", btnAddNewNote);
    }

    public void addNewCredential() {
        js.executeScript("arguments[0].click();", btnAddNewCredential);
    }

    public void setNoteTitle(String noteTitle) {
        js.executeScript("arguments[0].value='" + noteTitle + "';", txtNoteTitle);
    }

    public void setCredentialUrl(String url) {
        js.executeScript("arguments[0].value='" + url + "';", txtCredentialUrl);
    }

    public void setCredentialUsername(String username) {
        js.executeScript("arguments[0].value='" + username + "';", txtCredentialUsername);
    }

    public void setCredentialPassword(String password) {
        js.executeScript("arguments[0].value='" + password + "';", txtCredentialPassword);
    }

    public void modifyNoteTitle(String newNoteTitle) {
        wait.until(ExpectedConditions.elementToBeClickable(txtNoteTitle)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(txtNoteTitle)).sendKeys(newNoteTitle);
    }

    public void modifyNoteDescription(String newNoteDescription) {
        wait.until(ExpectedConditions.elementToBeClickable(txtModifyNoteDescription)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(txtModifyNoteDescription)).sendKeys(newNoteDescription);
    }

    public void navToNotesTab() {
        js.executeScript("arguments[0].click();", navNotesTab);
    }

    public void navToCredentialsTab() {
        js.executeScript("arguments[0].click();", navCredentialsTab);
    }

    public void setNoteDescription(String noteDescription) {
        js.executeScript("arguments[0].value='" + noteDescription + "';", txtNoteDescription);
    }

    public void saveNoteChanges() {
        js.executeScript("arguments[0].click();", btnNoteSaveChanges);
    }

    public void saveCredentialChanges() {
        js.executeScript("arguments[0].click();", btnCredentialSaveChanges);
    }

    public boolean noNotes(WebDriver driver) {
        return !isElementPresent(By.id("note-table-title"), driver) && !isElementPresent(By.id("note-table-description"), driver);
    }

    public boolean noCredentials(WebDriver driver) {
        return !isElementPresent(By.id("credential-table-url"), driver) &&
                !isElementPresent(By.id("credential-table-username"), driver) &&
                !isElementPresent(By.id("credential-table-password"), driver);
    }

    public boolean isElementPresent(By locatorKey, WebDriver driver) {
        try {
            driver.findElement(locatorKey);

            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public Note getFirstNote() {
        String title = wait.until(ExpectedConditions.elementToBeClickable(tableNoteTitle)).getText();
        String description = tableNoteDescription.getText();

        return new Note(title, description);
    }

    public Credential getFirstCredential() {
        String url = wait.until(ExpectedConditions.elementToBeClickable(tblCredentialUrl)).getText();
        String username = tblCredentialUsername.getText();
        String password = tblCredentialPassword.getText();

        return new Credential(url, username, password);
    }
}
