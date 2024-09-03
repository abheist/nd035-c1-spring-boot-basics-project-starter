package com.udacity.jwdnd.course1.cloudstorage;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteTests extends CloudStorageApplicationTests {

    @Test
    public void testNoteCreationAndDisplay() {
        String noteTitle = "My First Uda Note";
        String noteDescription = "This is first udacity note for assignment";

        HomePage homePageInstance1 = signUpAndLogin();
        try {
            createNote(noteTitle, noteDescription, homePageInstance1);
        } catch (Exception e) {
            Assertions.fail("Failed to create note: " + e.getMessage());
        }

        homePageInstance1.navigateToNotesTab();
        homePageInstance1 = new HomePage(driver);

        Note noteInstance = homePageInstance1.getFirstNote();

        Assertions.assertEquals(noteTitle, noteInstance.getNoteTitle());
        Assertions.assertEquals(noteDescription, noteInstance.getNoteDescription());

        deleteNote(homePageInstance1);

        homePageInstance1.logout();
    }

    @Test
    public void testNoteUpdate() {

        String noteTitle = "My second Uda Note";
        String noteDescription = "This is second udacity note for assignment";

        HomePage homePageInstance2 = signUpAndLogin();
        createNote(noteTitle, noteDescription, homePageInstance2);

        homePageInstance2.navigateToNotesTab();
        homePageInstance2 = new HomePage(driver);

        homePageInstance2.editNote();
        String modifiedNoteTitle = "Modified Udacity";
        homePageInstance2.modifyNoteTitle(modifiedNoteTitle);

        String modifiedNoteDescription = "second modified udacity note for assignment 1st.";
        homePageInstance2.modifyNoteDescription(modifiedNoteDescription);
        homePageInstance2.saveNoteChanges();

        ResultPage resultPageInstance = new ResultPage(driver);
        resultPageInstance.clickOk();

        homePageInstance2.navigateToNotesTab();
        Note note = homePageInstance2.getFirstNote();

        Assertions.assertEquals(modifiedNoteTitle, note.getNoteTitle());
        Assertions.assertEquals(modifiedNoteDescription, note.getNoteDescription());

        deleteNote(homePageInstance2);
        homePageInstance2.logout();
    }

    @Test
    public void testNoteDeletion() {
        String noteTitle = "My third Uda Note";
        String noteDescription = "third udacity note for assignment.";

        HomePage homePageInstance3 = signUpAndLogin();
        createNote(noteTitle, noteDescription, homePageInstance3);

        homePageInstance3.navigateToNotesTab();
        homePageInstance3 = new HomePage(driver);

        Assertions.assertFalse(homePageInstance3.noNotes(driver));

        deleteNote(homePageInstance3);

        Assertions.assertTrue(homePageInstance3.noNotes(driver));
        homePageInstance3.logout();
    }
}