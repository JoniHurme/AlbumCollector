package com.albumcollector.albumcollector.service;

import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.repository.RecordRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {

    private final ApplicationEventPublisher publisher;

    private final RecordRepository recordRepository;
    public RecordService(ApplicationEventPublisher publisher, RecordRepository recordRepository){
//        Constructors
        this.publisher = publisher;
        this.recordRepository = recordRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

//    @Transactional
//    public void insertNewRecord(Record levy){
//        entityManager.persist(levy);
//    }

    @Transactional
    public Record insertNewRecord(Record levy){
        Record saved = recordRepository.save(levy);
//        entityManager.persist(levy);
//        publisher.publishEvent(new NewRecordEvent(saved.getId()));
        return saved;
    }

    @Transactional
    public void removeRecord(Long id){
        Record levy = entityManager.find(Record.class, id);
        entityManager.remove(levy);
    }

    public List<Record> findAll(){
        return new ArrayList<>(recordRepository.findAll());
//        return recordRepository.findAll();
    }

    public long count() {
        long count = recordRepository.count();
        return count;
    }

    public Long countFavourites(){
       Long favourites = recordRepository.countAllByFavouriteIsTrue();
       return favourites;
    }
}
