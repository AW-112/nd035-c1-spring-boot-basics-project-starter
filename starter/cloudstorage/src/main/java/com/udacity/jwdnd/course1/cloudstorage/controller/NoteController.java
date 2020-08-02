package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping(value = {"/notes/add"})
    public String addNote(Note note, Model model) {
        if (!noteService.addNote(note)) {
            model.addAttribute("errorMessage", "There was an error adding the note. Please try again!");
        }
        return "result";
    }

    @PostMapping(value = {"/notes/update"})
    public String updateNote(Note note, Model model) {
        if (!noteService.updateNote(note)) {
            model.addAttribute("errorMessage", "There was an error updating the note. Please try again!");
        }
        return "result";
    }
}
