package com.albumcollector.albumcollector.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name="test")
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

    private String band;

    public void setBand(String band) {
        this.band = band;
    }

    public String getBand() {
        return band;
    }

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
