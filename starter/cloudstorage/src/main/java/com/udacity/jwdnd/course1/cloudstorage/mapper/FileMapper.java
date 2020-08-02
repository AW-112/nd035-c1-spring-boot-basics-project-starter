package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    File getFile(Integer userid, String filename);

    @Select("SELECT * FROM FILES WHERE id = #{fileid}")
    File getFile(Integer fileid);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> getFiles(Integer userid);

    @Insert("INSERT INTO FILES (filename, contentType, fileSize, userid, fileData) VALUES (#{filename}, #{contentType}, #{fileSize}, #{userid}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);
}
