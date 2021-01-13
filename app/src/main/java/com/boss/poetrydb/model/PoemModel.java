package com.boss.poetrydb.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.boss.poetrydb.common.Constants;
import com.boss.poetrydb.daoconverters.StringListConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = Constants.POEM_TABLE_NAME)
public class PoemModel {
    @PrimaryKey
    @SerializedName("title")
    @Expose
    @NonNull
    private String title;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("lines")
    @Expose
    @TypeConverters(StringListConverter.class)
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