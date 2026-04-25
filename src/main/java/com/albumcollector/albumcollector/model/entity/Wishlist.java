package com.albumcollector.albumcollector.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "wishlist")
    private Set<Record> records = new HashSet<>();

    public Set<Record> getRecord() {
        return records;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }



    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
