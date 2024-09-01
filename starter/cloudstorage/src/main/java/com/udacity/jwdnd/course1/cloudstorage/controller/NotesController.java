package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("note")
public class NotesController {

    private final NoteService noteService;
    private final UserService userService;

    public NotesController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping
    public String add(@ModelAttribute Note note, Authentication authentication) {

        int userId = userService.getUser(authentication.getName()).getUserId();
        note.setUserId(userId);

        if (note.getNoteId() == null) {
            noteService.addNote(note);
        } else {
            noteService.updateNote(note);
        }

        return "redirect:/home/result";
    }

    @PostMapping("delete")
    public String delete(@ModelAttribute("noteId") Integer noteId, Authentication authentication) {

        int userId = userService.getUser(authentication.getName()).getUserId();
        noteService.deleteNoteById(noteId, userId);

        return "redirect:/home/result";
    }

}
