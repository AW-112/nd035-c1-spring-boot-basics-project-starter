package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    private Integer noteid;
    private String noteTitle;
    private String notedescription;
    private Integer userid;

    public Note(String noteTitle, String notedescription, Integer userid) {
        this.noteTitle = noteTitle;
        this.notedescription = notedescription;
        this.userid = userid;
    }
}
