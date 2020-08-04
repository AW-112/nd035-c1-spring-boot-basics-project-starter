package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {
    private Integer fileid;
    private String filename;
    private String contenttype;
    private String fileSize;
    private Integer userid;
    private byte[] filedata;

    public File(Integer fileid, String filename, String contenttype, String fileSize, Integer userid, byte[] filedata) {
        this.fileid = fileid;
        this.filename = filename;
        this.contenttype = contenttype;
        this.fileSize = fileSize;
        this.userid = userid;
        this.filedata = filedata;
    }

    public Integer getFileid() {
        return fileid;
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

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] fileData) {
        this.filedata = fileData;
    }
}
