package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTests extends CloudStorageApplicationTests {

    @Test
    public void testCreationOfCredentials() {
        HomePage homePageInstance = signUpAndLogin();
        createCredentialAndVerify(homePageInstance);
        homePageInstance.deleteCredential();

        ResultPage resultPageInstance = new ResultPage(driver);
        resultPageInstance.clickOk();
        homePageInstance.logout();
    }

    @Test
    public void testCredentialUpdate() {
        HomePage homePageInstance = signUpAndLogin();
        createCredentialAndVerify(homePageInstance);
        homePageInstance.editCredential();

        String starURL = STAR_URL;
        String starUsername = STAR_USERNAME;
        String starPassword = STAR_PASSWORD;
        setCredentialFieldValues(starURL, starUsername, starPassword, homePageInstance);

        homePageInstance.saveCredentialChanges();

        ResultPage resultPageInstance = new ResultPage(driver);
        resultPageInstance.clickOk();

        homePageInstance.navigateToCredentialTab();
        Credential updatedCredentials = homePageInstance.getFirstCredential();

        Assertions.assertEquals(starURL, updatedCredentials.getUrl());
        Assertions.assertEquals(starUsername, updatedCredentials.getUsername());

        String updatedCredentialsPassword = updatedCredentials.getPassword();
        Assertions.assertNotEquals(starPassword, updatedCredentialsPassword);

        homePageInstance.deleteCredential();
        resultPageInstance.clickOk();

        homePageInstance.logout();
    }

    @Test
    public void testCredentialDeletion() {
        HomePage homePageInstance = signUpAndLogin();

        createCredentialInstance(ABHEIST_URL, ABHI_USERNAME, ABHI_PASSWORD, homePageInstance);
        createCredentialInstance(STAR_URL, STAR_USERNAME, STAR_PASSWORD, homePageInstance);
        createCredentialInstance("http://abhishek.com/", "abhi", "pass", homePageInstance);

        Assertions.assertFalse(homePageInstance.noCredentials(driver));

        homePageInstance.deleteCredential();

        ResultPage resultPageInstance = new ResultPage(driver);
        resultPageInstance.clickOk();

        homePageInstance.navigateToCredentialTab();
        homePageInstance.deleteCredential();
        resultPageInstance.clickOk();

        homePageInstance.navigateToCredentialTab();
        homePageInstance.deleteCredential();
        resultPageInstance.clickOk();

        homePageInstance.navigateToCredentialTab();
        Assertions.assertTrue(homePageInstance.noCredentials(driver));
        homePageInstance.logout();
    }

}
