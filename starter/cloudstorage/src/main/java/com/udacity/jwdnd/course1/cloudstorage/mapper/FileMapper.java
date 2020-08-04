package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userid = #{userid} AND filename = #{filename}")
    File getFile(Integer userid, String filename);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> getFiles(Integer userid);

    @Insert("INSERT INTO FILES (filename, contenttype, fileSize, userid, filedata) VALUES (#{filename}, #{contenttype}, #{fileSize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int insertFile(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
    void deleteFile(Integer fileid);
}
