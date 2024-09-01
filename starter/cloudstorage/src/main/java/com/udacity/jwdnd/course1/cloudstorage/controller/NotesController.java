package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        noteService.addNote(note);

        return "redirect:/note";
    }

    @PutMapping
    public String edit(@ModelAttribute Note note, Authentication authentication) {

        int userId = userService.getUser(authentication.getName()).getUserId();
        note.setUserId(userId);
        noteService.updateNote(note);

        return "redirect:/note";
    }

    @PostMapping("delete")
    public String delete(@ModelAttribute("noteId") Integer noteId, Authentication authentication) {

        int userId = userService.getUser(authentication.getName()).getUserId();
        noteService.deleteNoteById(noteId, userId);

        return "redirect:/note";
    }

}
