package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("credential")
public class CredentialController {

    private final CredentialService credentialService;
    private final UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping
    public String addCredential(@ModelAttribute Credential credential, Authentication authentication, RedirectAttributes redirectAttributes) {
        int userId = userService.getUser(authentication.getName()).getUserId();
        credential.setUserId(userId);
        credentialService.addCredential(credential);

        return "redirect:/credential";
    }

    @PutMapping
    public Credential updateCredential(@ModelAttribute Credential credential, Authentication authentication) {
        int userId = userService.getUser(authentication.getName()).getUserId();
        credential.setUserId(userId);
        credentialService.updateCredential(credential);

        return credential;
    }

    @DeleteMapping
    public String deleteCredential(@ModelAttribute("credentialId") Integer credentialId, RedirectAttributes redirectAttributes, Authentication authentication) {
        int userId = userService.getUser(authentication.getName()).getUserId();
        credentialService.deleteCredential(credentialId, userId);

        return "redirect:/credential";
    }

}
