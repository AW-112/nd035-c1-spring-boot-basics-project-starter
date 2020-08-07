package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CredentialController {
    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping(value = "/credentials")
    public String addCrendential(Authentication auth, Credential credential, Model model) {
        if (credential.getCredentialid() != null) {
            if (!this.credentialService.updateCredential(credential)) {
                model.addAttribute("errorMessage", "There was an error updating the credential. Please try again!");
            }
        }
        else if (!credentialService.addCredential(auth.getName(),credential)) {
            model.addAttribute("errorMessage", "There was an error adding the credential. Please try again!");
        }

        return "result";
    }

    @GetMapping("/credentials/delete/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId) {
        this.credentialService.deleteCredential(credentialId);
        return "result";
    }

    @GetMapping(value = "/credentials/decode-password", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> decodePassword(@RequestParam Integer credentialId) {
        String decryptedPassword = this.credentialService.decryptPassword(credentialId);
        Map<String,String> map = new HashMap();
        map.put("decryptedPassword", decryptedPassword);
        return map;
    }
}
