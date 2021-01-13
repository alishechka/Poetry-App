package com.boss.poetrydb.model;

public class TitleSearch {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TitleSearch(String title) {
        this.title = title;
    }

    public TitleSearch() {
    }

    @Override
    public String toString() {
        return "TitleSearch{" +
                "title='" + title + '\'' +
                '}';
    }
}