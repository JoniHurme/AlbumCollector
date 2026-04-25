package com.albumcollector.albumcollector.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name="records")
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

    public Record() {
        this.collection = null;
        this.wishlist = null;
    }



//    Collection and wishlist relations with getters and setters of their own.
    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;
    public void setCollection(Collection collection) {
        this.collection = collection;
    }
    public Collection getCollection() {
        return collection;
    }


    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;
    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }
    public Wishlist getWishlist() {
        return wishlist;
    }


//    ATTRIBUTES
    private String artist;
    private String title;
    private String genre;
    private boolean favourite;
    private String medium;
    private Integer year;

//    GETTERS AND SETTERS
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

    public void setYear(Integer yearInteger) {
        this.year = yearInteger;
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
