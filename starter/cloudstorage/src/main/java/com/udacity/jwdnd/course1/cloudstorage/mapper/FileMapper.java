package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE userid=#{userId}")
    List<File> getAllFiles(int userId);

    @Select("SELECT *  FROM FILES WHERE fileid=#{fileId} and userid=#{userId}")
    File getFileById(int fileId, int userId);

    @Select("SELECT *  FROM FILES WHERE filename=#{fileName} and userid=#{userId}")
    File getFileByName(String fileName, int userId);

    @Select("SELECT count(*) as count FROM FILES WHERE fileid=#{fileId} and userid=#{userId}")
    long checkFile(int fileId, int userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{fileName}, " +
            "#{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyColumn = "fileid", keyProperty = "fileId")
    int insertFile(File file);

    @Update("UPDATE FILES SET filename=#{fileName}, contenttype=#{contentType}, filesize=#{fileSize}, " +
            "filedata=#{fileData} where fileid=#{fileId} and userid=#{userId}")
    int updateFile(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId} and userid = #{userId}")
    boolean deleteFile(int fileId, int userId);

}
