package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping(value = {"/notes"})
    public String addNote(Authentication auth, Note note, Model model) {
        if (note.getNoteid()!=null) {
            if (!noteService.updateNote(note)) {
                model.addAttribute("errorMessage", "There was an error updating the note. Please try again!");
            }
        }
        else if (!noteService.addNote(auth.getName(), note)) {
            model.addAttribute("errorMessage", "There was an error adding the note. Please try again!");
        }
        return "result";
    }

    @GetMapping("/notes/delete/{noteid}")
    public String deleteNote(@PathVariable Integer noteid) {
        this.noteService.deleteNote(noteid);
        return "result";
    }
}
