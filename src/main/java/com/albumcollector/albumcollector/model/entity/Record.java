package com.albumcollector.albumcollector.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name="prodtest")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private String artist;
    private String title;
    private String genre;
    private boolean favourite;
    private String medium;
    private Integer year;


    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public boolean getFavourite() {
        return favourite;
    }

    public void setYear(int yearInteger) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
