package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM NOTES WHERE credentialid = #{credentialid}")
    Credential getCredential(Integer credentialid);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Credential> getCredentials(Integer userid);

    @Insert("INSERT INTO NOTES (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insertCredential(Credential credential);
}
