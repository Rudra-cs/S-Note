package com.rudra.snote.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Notes implements Serializable {

    private String Id;
    private String title;
    private String subTitle;
    private String noteText;
    private String dateTime;
    private String imagePath;
    private String color;
    private String webLink;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    //    @NonNull
//    @Override
//    public String toString() {
//        return title + " : " + dateTime;
//    }

}


