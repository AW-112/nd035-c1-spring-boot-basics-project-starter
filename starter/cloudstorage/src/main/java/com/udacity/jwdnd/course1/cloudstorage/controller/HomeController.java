package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final CredentialService credentialService;
    private final NoteService noteService;

    public HomeController(CredentialService credentialService, NoteService noteService) {
        this.credentialService = credentialService;
        this.noteService = noteService;
    }

    @GetMapping()
    public String homeView(Authentication auth, Model model) {
        List<Note> notes = this.noteService.getNotes(auth.getName());
        model.addAttribute("notes", notes);

        List<Credential> credentials = this.credentialService.getCredentials(auth.getName());
        model.addAttribute("credentials", credentials);

        return "home";
    }
}
