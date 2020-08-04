package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {
    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping(value = "/credentials")
    public String addCrendential(Authentication auth, Credential credential, Model model) {
        if (credential.getCredentialid() != null) {
            if (this.credentialService.updateCredential(credential)) {
                model.addAttribute("errorMessage", "There was an error updating the credential. Please try again!");
            }
        }
        else if (!credentialService.addCredential(auth.getName(),credential)) {
            model.addAttribute("errorMessage", "There was an error adding the credential. Please try again!");
        }

        return "result";
    }

    @GetMapping("/credentials/delete/{credentialid}")
    public String deleteCredential(@PathVariable Integer credentialid) {
        this.credentialService.deleteCredential(credentialid);
        return "result";
    }
}
