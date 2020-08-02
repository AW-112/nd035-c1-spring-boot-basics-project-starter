package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {
    private Integer fileId;
    private String filename;
    private String contentType;
    private String fileSize;
    private Integer userid;
    private Byte[] fileData;

    public File(String filename, String contentType, String fileSize, Integer userid, Byte[] fileData) {
        this.filename = filename;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userid = userid;
        this.fileData = fileData;
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
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

    public Byte[] getFileData() {
        return fileData;
    }

    public void setFileData(Byte[] fileData) {
        this.fileData = fileData;
    }
}
