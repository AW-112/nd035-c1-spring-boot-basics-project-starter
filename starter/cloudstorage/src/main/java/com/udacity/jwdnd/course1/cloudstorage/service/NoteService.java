package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    private final UserService userService;

    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public boolean addNote(String username, Note note) {
        note.setUserid(this.userService.getUser(username).getUserid());
        return noteMapper.insertNote(note) > 0;
    }

    public List<Note> getNotes(String username) {
        return noteMapper.getNotes(this.userService.getUser(username).getUserid());
    }

    public boolean updateNote(Note note) {
        return noteMapper.updateNote(note) > 0;
    }

    public void deleteNote(Integer noteid) {
        noteMapper.deleteNote(noteid);
    }
}
