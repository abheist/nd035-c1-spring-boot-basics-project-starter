package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final UserMapper userMapper;
    private final NoteMapper noteMapper;

    public NoteService(UserMapper userMapper, NoteMapper noteMapper) {
        this.userMapper = userMapper;
        this.noteMapper = noteMapper;
    }

    public List<Note> getAllNotes(int userId) {
        return this.noteMapper.getAllNotes(userId);
    }

    public Note getNoteById(int noteId, int userId) {
        return this.noteMapper.getNoteById(noteId, userId);
    }

    public void addNote(String noteTitle, String noteDescription, int userId) {
        Note note = new Note(0, noteTitle, noteDescription, userId);
        this.noteMapper.createNote(note);
    }

    public void deleteNoteById(int noteId, int userId) {
        this.noteMapper.deleteNote(noteId, userId);
    }

    public void updateNote(int noteId, String noteTitle, String noteDescription, int userId) {
        this.noteMapper.editNote(noteId, userId, noteTitle, noteDescription);
    }

}
