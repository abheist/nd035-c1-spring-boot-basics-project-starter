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
    private WebElement addNewNoteButton;

    @FindBy(id = "btn-add-new-credential")
    private WebElement addNewCredentialButton;

    @FindBy(id = "note-title")
    private WebElement noteTitleInput;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTabNavigation;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTabNavigation;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id = "btn-note-save-changes")
    private WebElement noteSaveChangesButton;

    @FindBy(id = "note-table-title")
    private WebElement noteTitleTable;

    @FindBy(id = "note-table-description")
    private WebElement noteDescriptionTable;

    @FindBy(id = "edit-note-button")
    private WebElement editNoteButton;

    @FindBy(id = "edit-credential-button")
    private WebElement editCredentialButton;

    @FindBy(id = "note-description")
    private WebElement updateNoteDescriptionInput;

    @FindBy(id = "delete-note-link")
    private WebElement deleteNoteLink;

    @FindBy(id = "delete-credential-link")
    private WebElement deleteCredentialLink;

    @FindBy(id = "credential-url")
    private WebElement credentialURLInput;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameInput;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordInput;

    @FindBy(id = "credential-save-changes-button")
    private WebElement credentialSaveChangesButton;

    @FindBy(id = "credential-table-url")
    private WebElement credentialTableURL;

    @FindBy(id = "credential-table-username")
    private WebElement credentialUsernameTable;

    @FindBy(id = "credential-table-password")
    private WebElement credentialPasswordTable;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 500);
    }

    public void logout() {
        js.executeScript("arguments[0].click();", logoutButton);
    }

    public void editNote() {
        js.executeScript("arguments[0].click();", editNoteButton);
    }

    public void editCredential() {
        js.executeScript("arguments[0].click();", editCredentialButton);
    }

    public void deleteNote() {
        js.executeScript("arguments[0].click();", deleteNoteLink);
    }

    public void deleteCredential() {
        js.executeScript("arguments[0].click();", deleteCredentialLink);
    }

    public void uploadFile() {
        js.executeScript("arguments[0].click();", fileUpload);
    }

    public void addNewNote() {
        js.executeScript("arguments[0].click();", addNewNoteButton);
    }

    public void addNewCredentialValue() {
        js.executeScript("arguments[0].click();", addNewCredentialButton);
    }

    public void setNoteTitleValue(String noteTitle) {
        js.executeScript("arguments[0].value='" + noteTitle + "';", noteTitleInput);
    }

    public void setCredentialUrlValue(String url) {
        js.executeScript("arguments[0].value='" + url + "';", credentialURLInput);
    }

    public void setCredentialUsernameValue(String username) {
        js.executeScript("arguments[0].value='" + username + "';", credentialUsernameInput);
    }

    public void setCredentialPasswordValue(String password) {
        js.executeScript("arguments[0].value='" + password + "';", credentialPasswordInput);
    }

    public void modifyNoteTitle(String newNoteTitle) {
        wait.until(ExpectedConditions.elementToBeClickable(noteTitleInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitleInput)).sendKeys(newNoteTitle);
    }

    public void modifyNoteDescription(String newNoteDescription) {
        wait.until(ExpectedConditions.elementToBeClickable(updateNoteDescriptionInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(updateNoteDescriptionInput)).sendKeys(newNoteDescription);
    }

    public void navigateToNotesTab() {
        js.executeScript("arguments[0].click();", noteTabNavigation);
    }

    public void navigateToCredentialTab() {
        js.executeScript("arguments[0].click();", credentialTabNavigation);
    }

    public void setNoteDescriptionValue(String noteDescription) {
        js.executeScript("arguments[0].value='" + noteDescription + "';", noteDescriptionInput);
    }

    public void saveNoteChanges() {
        js.executeScript("arguments[0].click();", noteSaveChangesButton);
    }

    public void saveCredentialChanges() {
        js.executeScript("arguments[0].click();", credentialSaveChangesButton);
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
        String title = wait.until(ExpectedConditions.elementToBeClickable(noteTitleTable)).getText();
        String description = noteDescriptionTable.getText();

        return new Note(title, description);
    }

    public Credential getFirstCredential() {
        String url = wait.until(ExpectedConditions.elementToBeClickable(credentialTableURL)).getText();
        String username = credentialUsernameTable.getText();
        String password = credentialPasswordTable.getText();

        return new Credential(url, username, password);
    }
}
