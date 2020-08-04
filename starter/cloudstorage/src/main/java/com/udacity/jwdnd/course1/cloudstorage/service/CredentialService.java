package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;
    private final UserService userService;

    public CredentialService(CredentialMapper credentialMapper, UserService userService, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    public boolean addCredential(String username, Credential credential) {
        credential.setUserid(this.userService.getUser(username).getUserid());
        return credentialMapper.insertCredential(encryptPassword(credential)) > 0;
    }

    public List<Credential> getCredentials(String username) {
        return credentialMapper.getCredentials(this.userService.getUser(username).getUserid());
    }

    public boolean updateCredential(Credential credential) {
        Credential dbCredential = this.credentialMapper.getCredential(credential.getCredentialid());
        dbCredential.setUsername(credential.getUsername());
        dbCredential.setUrl(credential.getUrl());
        dbCredential.setPassword(credential.getPassword());
        return this.credentialMapper.updateCredential(encryptPassword(dbCredential)) > 0;
    }

    public void deleteCredential(Integer credentialid) {
        this.credentialMapper.deleteCredentials(credentialid);
    }

    private String createKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    private Credential encryptPassword(Credential credential) {
        credential.setKey(createKey());
        credential.setPassword(this.encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
        return credential;
    }

    private Credential decryptPassword(Credential credential) {
        credential.setPassword(this.encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        return credential;
    }
}
