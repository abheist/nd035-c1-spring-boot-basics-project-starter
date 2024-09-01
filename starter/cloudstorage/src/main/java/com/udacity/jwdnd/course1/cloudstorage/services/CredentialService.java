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

    public void addCredential(String url, String username, String key, String password, int userId) {
        Credential credential = new Credential(0, url, username, key, password, userId);
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

    public void updateCredential(String url, String username, String key, String password, int credentialId,
                                 int userId) {
        credentialMapper.editCredential(credentialId, url, username, key, password, userId);
    }

}
