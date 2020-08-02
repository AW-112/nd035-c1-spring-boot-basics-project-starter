package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    private final UserService userService;

    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public boolean addNote(Note note) {
        note.setUserid(this.userService.getCurrentUser().getUserid());
        return (noteMapper.insertNote(note) > 0);
    }

    public boolean updateNote(Note note) {
        return (noteMapper.updateNote(note) > 0);
    }

    public void deleteNote(Integer noteid) {
        noteMapper.deleteNote(noteid);
    }
}
