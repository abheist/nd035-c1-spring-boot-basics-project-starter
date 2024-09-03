package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

    public static final String ABHEIST_URL = "http://abheist.com/";
    public static final String ABHI_USERNAME = "abheist";
    public static final String ABHI_PASSWORD = "abheist_password";

    public static final String STAR_URL = "http://starwars.com/";
    public static final String STAR_USERNAME = "star";
    public static final String STAR_PASSWORD = "wars";


    @LocalServerPort
    protected int port;

    protected WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    protected void createNote(String noteTitle, String noteDescription, HomePage homePageInstance) {
        homePageInstance.navigateToNotesTab();
        homePageInstance.addNewNote();
        homePageInstance.setNoteTitleValue(noteTitle);
        homePageInstance.setNoteDescriptionValue(noteDescription);
        homePageInstance.saveNoteChanges();

        ResultPage resultPageInstance = new ResultPage(driver);
        resultPageInstance.clickOk();

        homePageInstance.navigateToNotesTab();
    }


    protected void createCredentialAndVerify(HomePage homePageInstance) {
        createCredentialInstance(ABHEIST_URL, ABHI_USERNAME, ABHI_PASSWORD, homePageInstance);
        homePageInstance.navigateToCredentialTab();
        Credential credentialInstance = homePageInstance.getFirstCredential();

        Assertions.assertEquals(ABHEIST_URL, credentialInstance.getUrl());
        Assertions.assertEquals(ABHI_USERNAME, credentialInstance.getUsername());
        Assertions.assertNotEquals(ABHI_PASSWORD, credentialInstance.getPassword());
    }

    protected void createCredentialInstance(String url, String username, String password, HomePage homePageInstance) {
        homePageInstance.navigateToCredentialTab();
        homePageInstance.addNewCredentialValue();
        setCredentialFieldValues(url, username, password, homePageInstance);
        homePageInstance.saveCredentialChanges();

        ResultPage resultPageInstance = new ResultPage(driver);
        resultPageInstance.clickOk();
        homePageInstance.navigateToCredentialTab();
    }

    protected void setCredentialFieldValues(String url, String username, String password, HomePage homePageInstance) {
        homePageInstance.setCredentialUrlValue(url);
        homePageInstance.setCredentialUsernameValue(username);
        homePageInstance.setCredentialPasswordValue(password);
    }

    protected void deleteNote(HomePage homePageInstance) {
        homePageInstance.deleteNote();

        ResultPage resultPageInstance = new ResultPage(driver);
        resultPageInstance.clickOk();
    }

    /**
     * PLEASE DO NOT DELETE THIS method.
     * Helper method for Udacity-supplied sanity checks.
     **/
    protected void doMockSignUp(String firstName, String lastName, String userName, String password) {
        // Create a dummy account for logging in later.

        // Visit the sign-up page.
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        driver.get("http://localhost:" + this.port + "/signup");
        webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

        // Fill out credentials
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
        WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
        inputFirstName.click();
        inputFirstName.sendKeys(firstName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
        WebElement inputLastName = driver.findElement(By.id("inputLastName"));
        inputLastName.click();
        inputLastName.sendKeys(lastName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        WebElement inputUsername = driver.findElement(By.id("inputUsername"));
        inputUsername.click();
        inputUsername.sendKeys(userName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputPassword.click();
        inputPassword.sendKeys(password);

        // Attempt to sign up.
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
        WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
        buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
        Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
    }


    /**
     * PLEASE DO NOT DELETE THIS method.
     * Helper method for Udacity-supplied sanity checks.
     **/
    protected void doLogIn(String userName, String password) {
        // Log in to our dummy account.
        driver.get("http://localhost:" + this.port + "/login");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        WebElement loginUserName = driver.findElement(By.id("inputUsername"));
        loginUserName.click();
        loginUserName.sendKeys(userName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        WebElement loginPassword = driver.findElement(By.id("inputPassword"));
        loginPassword.click();
        loginPassword.sendKeys(password);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        webDriverWait.until(ExpectedConditions.titleContains("Home"));

    }
    
    public HomePage signUpAndLogin() {
        String firstName = "Abhishek";
        String lastName = "Singh";
        String userName = "abhi";
        String password = "abhi_pass";

        driver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.setInputFirstName(firstName);
        signupPage.setSetInputLastName(lastName);
        signupPage.setUsername(userName);
        signupPage.setPassword(password);
        signupPage.signUp();

        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername(userName);
        loginPage.setPassword(password);
        loginPage.login();

        return new HomePage(driver);
    }


}
