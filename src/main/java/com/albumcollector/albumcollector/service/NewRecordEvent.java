package com.albumcollector.albumcollector.service;

public class NewRecordEvent {
    private final Long recordID;

    public NewRecordEvent(Long recordID){
        this.recordID = recordID;
    }

    public Long getRecordID(){
        return recordID;
    }
}
