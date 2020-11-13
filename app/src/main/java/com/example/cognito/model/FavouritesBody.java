package com.example.cognito.model;

public class FavouritesBody {
    private String title;

    public FavouritesBody() {
    }

    public FavouritesBody(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "FavouritesBody{" +
                "title='" + title + '\'' +
                '}';
    }

}
