package com.example.cognito.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PoemModel {
    @SerializedName("title")
    @Expose
    @NonNull
    private String title;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("lines")
    @Expose
    private List<String> lines = null;
    @SerializedName("linecount")
    @Expose
    private String linecount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public String getLinecount() {
        return linecount;
    }

    public void setLinecount(String linecount) {
        this.linecount = linecount;
    }

    @Override
    public String toString() {
        return "PoemModel{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", lines=" + lines +
                ", linecount='" + linecount + '\'' +
                '}';
    }
}