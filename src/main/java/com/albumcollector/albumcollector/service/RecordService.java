package com.albumcollector.albumcollector.service;

import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    public final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> findAll(){
        return recordRepository.findAll();
    }
}
