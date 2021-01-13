package com.boss.poetrydb.model;

import java.util.List;

public class Favourites {
    private String username;
    private List<String> favourites;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<String> favourites) {
        this.favourites = favourites;
    }

    public Favourites() {
    }

    public Favourites(String username, List<String> favourites) {
        this.username = username;
        this.favourites = favourites;
    }

    @Override
    public String toString() {
        return "Favourites{" +
                "username='" + username + '\'' +
                ", favourites=" + favourites +
                '}';
    }
}
