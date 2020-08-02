package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

public class File {
    private Integer fileId;
    private String filename;
    private String contenttype;
    private String fileSize;
    private Integer userid;
    private Blob filedata;

    public File(String filename, String contenttype, String fileSize, Integer userid, Blob filedata) {
        this.filename = filename;
        this.contenttype = contenttype;
        this.fileSize = fileSize;
        this.userid = userid;
        this.filedata = filedata;
    }

    public Integer getFileId() {
        return fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Blob getFiledata() {
        return filedata;
    }

    public void setFiledata(Blob fileData) {
        this.filedata = fileData;
    }
}
