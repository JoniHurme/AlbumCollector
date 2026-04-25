package com.albumcollector.albumcollector.service;

import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.model.entity.Wishlist;
import com.albumcollector.albumcollector.repository.RecordRepository;
import com.albumcollector.albumcollector.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final RecordRepository recordRepository;

    public WishlistService(WishlistRepository wishlistRepository, RecordRepository recordRepository) {
        this.wishlistRepository = wishlistRepository;
        this.recordRepository = recordRepository;
    }

    public Wishlist addToWishlist(Long recordId, Long wishlistId) {
        Record record = recordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Record not found: " + recordId));
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new IllegalArgumentException("Wishlist not found: " + wishlistId));

        record.setWishlist(wishlist);
        recordRepository.save(record);
        return wishlist;
    }

    public Wishlist removeFromWishlist(Long recordId, Long wishlistId) {
        Record record = recordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Record not found: " + recordId));
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new IllegalArgumentException("Wishlist not found: " + wishlistId));

        if (record.getWishlist() != null && wishlistId.equals(record.getWishlist().getId())) {
            record.setWishlist(null);
            recordRepository.save(record);
        }

        return wishlist;
    }

    public List<Wishlist> getWishlist() {
        return wishlistRepository.findAll();
    }

    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public byte[] exportWishlist(Long id) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Wishlist not found: " + id));

        try {
            return PdfService.generateWishlistPdf(new ArrayList<>(wishlist.getRecords()));
        } catch (IOException exception) {
            throw new IllegalStateException("Failed to generate wishlist export for id: " + id, exception);
        }
    }
}
