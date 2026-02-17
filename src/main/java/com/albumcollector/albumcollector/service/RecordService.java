package com.albumcollector.albumcollector.service;

import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.repository.RecordRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    private final RecordRepository recordRepository;
    public RecordService(RecordRepository recordRepository){
        this.recordRepository = recordRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertNewRecord(Record levy){
        this.entityManager.persist(levy);
    }

    public List<Record> findAll(){
        return recordRepository.findAll();
    }
    public long Count() {
        long count = recordRepository.count();
        return count;
    }
}
