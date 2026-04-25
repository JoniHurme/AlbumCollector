package com.albumcollector.albumcollector.model.dto;

import java.util.Set;

public class CollectionDTO{

    private Long id;
    private Set<RecordDTO> records;

    public CollectionDTO(Long id, Set<RecordDTO> records) {
        this.id = id;
        this.records = records;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Set<RecordDTO> getRecords() {
        return records;
    }
    public void setRecords(Set<RecordDTO> records) {
        this.records = records;
    }

}
