package com.albumcollector.albumcollector.service;

import com.albumcollector.albumcollector.model.entity.Collection;
import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.model.entity.Wishlist;
import com.albumcollector.albumcollector.repository.CollectionRepository;
import com.albumcollector.albumcollector.repository.RecordRepository;
import com.albumcollector.albumcollector.repository.WishlistRepository;
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
    private final WishlistRepository wishlistRepository;

    public RecordService(RecordRepository recordRepository, CollectionRepository collectionRepository, WishlistRepository wishlistRepository){
//        Constructors
        this.recordRepository = recordRepository;
        this.collectionRepository = collectionRepository;
        this.wishlistRepository = wishlistRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public Record insertNewRecord(Record levy, Long collectionId, Long wishlistId){
        if (collectionId != null) {
            levy.setCollection(collectionRepository.findById(collectionId)
                    .orElseThrow(() -> new IllegalArgumentException("Collection not found: " + collectionId)));
        }
        if (wishlistId != null) {
            levy.setWishlist(wishlistRepository.findById(wishlistId)
                    .orElseThrow(() -> new IllegalArgumentException("Wishlist not found: " + wishlistId)));
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

    public Collection getCollectionId(Long collectionId) {
        return collectionRepository.findById(collectionId)
                .orElseThrow(() -> new IllegalArgumentException("Collection not found: " + collectionId));
    }

    public Wishlist getWishlistId(Long wishlistId) {
        return wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new IllegalArgumentException("Wishlist not found: " + wishlistId));
    }
}
