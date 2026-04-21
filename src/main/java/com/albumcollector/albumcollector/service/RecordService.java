package com.albumcollector.albumcollector.service;

import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.repository.CollectionRepository;
import com.albumcollector.albumcollector.repository.RecordRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {


    private final RecordRepository recordRepository;
    private final CollectionRepository collectionRepository;

    public RecordService(RecordRepository recordRepository, CollectionRepository collectionRepository){
//        Constructors
        this.recordRepository = recordRepository;
        this.collectionRepository = collectionRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public Record insertNewRecord(Record levy, Long collectionId){
        if (collectionId != null) {
            levy.setCollection(collectionRepository.findById(collectionId)
                    .orElseThrow(() -> new IllegalArgumentException("Collection not found: " + collectionId)));
        }
        return recordRepository.save(levy);
    }

    @Transactional
    public void removeRecord(Long id){
        Record levy = entityManager.find(Record.class, id);
        entityManager.remove(levy);
    }

    public List<Record> findAll(){
        return new ArrayList<>(recordRepository.findAll());
    }
}
