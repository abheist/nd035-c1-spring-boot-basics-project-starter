package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> getAllCredentials(int userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId} AND userid = #{userId}")
    Credential getCredentialById(int credentialId, int userId);

    @Select("SELECT COUNT(*) as count FROM CREDENTIALS WHERE credentialid = #{credentialId} and userid = #{userId}")
    long checkCredential(int credentialId, int userId);

    @Insert("INSERT INTO CRENDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, " +
            "#{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyColumn = "credentialid", keyProperty = "credentialId")
    int createCredential(Credential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password=#{password}, WHERE " +
            "credentialid = #{credentialId} and userid=#{userId}")
    int editCredential(Integer credentialId, String url, String username, String key, String password, int userId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId} and userid = #{userId}")
    boolean deleteCredential(int credentialId, int userId);

}
