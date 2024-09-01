package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final UserMapper userMapper;
    private final CredentialMapper credentialMapper;

    public CredentialService(UserMapper userMapper, CredentialMapper credentialMapper) {
        this.userMapper = userMapper;
        this.credentialMapper = credentialMapper;
    }

    public void addCredential(Credential credential) {
        credentialMapper.createCredential(credential);
    }

    public List<Credential> getAllCredentials(Integer userId) {
        return credentialMapper.getAllCredentials(userId);
    }

    public Credential getCredential(int credentialId, int userId) {
        return credentialMapper.getCredentialById(credentialId, userId);
    }

    public void deleteCredential(int credentialId, int userId) {
        credentialMapper.deleteCredential(credentialId, userId);
    }

    public void updateCredential(Credential credential) {
        credentialMapper.editCredential(credential);
    }

}
