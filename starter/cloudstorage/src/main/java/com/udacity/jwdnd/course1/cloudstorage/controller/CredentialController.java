package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("credential")
public class CredentialController {

    private final CredentialService credentialService;
    private final UserService userService;
    private final EncryptionService encryptionService;
    private final CredentialMapper credentialMapper;

    public CredentialController(CredentialService credentialService, UserService userService, EncryptionService encryptionService, CredentialMapper credentialMapper) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
        this.credentialMapper = credentialMapper;
    }

    @PostMapping
    public String addCredential(@ModelAttribute Credential credential, Authentication authentication) {

        int userId = userService.getUser(authentication.getName()).getUserId();
        credential.setUserId(userId);

        if (credential.getCredentialId() == null) {
            encryptAndSetPassword(credential);
            credentialService.addCredential(credential);
        } else {

            Credential currentCredentials = credentialMapper.getCredentialById(credential.getCredentialId(), userId);
            String currentPassword = encryptionService.decryptValue(currentCredentials.getPassword(), currentCredentials.getKey());

            if (!currentPassword.equals(credential.getPassword())) {
                encryptAndSetPassword(credential);
            }

            credentialService.updateCredential(credential);
        }

        return "redirect:/home/result";
    }

    private void encryptAndSetPassword(Credential credential) {
        credential.setKey(encryptionService.generateKey());
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), credential.getKey());
        credential.setPassword(encryptedPassword);
    }


    @PostMapping("delete")
    public String deleteCredential(@ModelAttribute("credentialId") Integer credentialId, RedirectAttributes redirectAttributes, Authentication authentication) {
        int userId = userService.getUser(authentication.getName()).getUserId();
        credentialService.deleteCredential(credentialId, userId);

        return "redirect:/home/result";
    }

}
