package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getAllNotes(int userId);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId} AND userid = #{userId}")
    Note getNoteById(int noteId, int userId);

    @Select("SELECT COUNT(*) as count FROM NOTES WHERE noteid = #{noteId} and userid = #{userId}")
    long checkNote(int noteId, int userId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, " +
            "#{userId})")
    @Options(useGeneratedKeys = true, keyColumn = "noteid", keyProperty = "noteId")
    int createNote(Note note);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription}" +
            "WHERE noteid = #{noteId} and userid = #{userId}")
    int editNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId} and userid = #{userId}")
    boolean deleteNote(int noteId, int userId);

}
