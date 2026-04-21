package com.albumcollector.albumcollector.model.dto;

public class RecordDTO {
    private Long id;
    private String artist;
    private String title;
    private String genre;
    private boolean favourite;
    private String medium;
    private Integer year;
    private Long collectionId;


    public RecordDTO(Long id, String artist, String title, String genre, boolean favourite, String medium, Integer year) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.genre = genre;
        this.favourite = favourite;
        this.medium = medium;
        this.year = year;
    }

    public RecordDTO(Long id, String artist, String title, String genre, boolean favourite, String medium, Integer year, Long collectionId) {
        this(id, artist, title, genre, favourite, medium, year);
        this.collectionId = collectionId;
    }

//    SETTERS AND GETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public boolean isFavourite() { return favourite; }
    public void setFavourite(boolean favourite) { this.favourite = favourite; }
    public String getMedium() { return medium; }
    public void setMedium(String medium) { this.medium = medium; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public Long getCollectionId() { return collectionId; }
    public void setCollectionId(Long collectionId) { this.collectionId = collectionId; }
}
