package com.albumcollector.albumcollector.service;

import com.albumcollector.albumcollector.model.entity.Collection;
import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.repository.CollectionRepository;
import com.albumcollector.albumcollector.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final RecordRepository recordRepository;

    public CollectionService(CollectionRepository collectionRepository, RecordRepository recordRepository) {
        this.collectionRepository = collectionRepository;
        this.recordRepository = recordRepository;
    }

    public Collection addToCollection(Long recordId, Long collectionId) {
        Record record = recordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Record not found: " + recordId));
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new IllegalArgumentException("Collection not found: " + collectionId));

        record.setCollection(collection);
        recordRepository.save(record);
        return collection;
    }

    public Collection removeFromCollection(Long recordId, Long collectionId) {
        Record record = recordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Record not found: " + recordId));
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new IllegalArgumentException("Collection not found: " + collectionId));

        if (record.getCollection() != null && collectionId.equals(record.getCollection().getId())) {
            record.setCollection(null);
            recordRepository.save(record);
        }

        return collection;
    }

    public List<Collection> getCollection() {
        return collectionRepository.findAll();
    }


    public Collection createCollection(Collection collection) {
        return collectionRepository.save(collection);
    }

}
