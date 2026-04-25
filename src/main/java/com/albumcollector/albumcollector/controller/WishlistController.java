package com.albumcollector.albumcollector.controller;

import com.albumcollector.albumcollector.model.dto.RecordDTO;
import com.albumcollector.albumcollector.model.dto.WishlistDTO;
import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.model.entity.Wishlist;
import com.albumcollector.albumcollector.service.WishlistService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "http://localhost:5173")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public List<WishlistDTO> getWishlists() {
        return wishlistService.getWishlist()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/export/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportWishlist(@PathVariable Long id) {
        byte[] wishlistData = wishlistService.exportWishlist(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=wishlist_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(wishlistData);
    }

    @PostMapping
    public WishlistDTO createWishlist(@RequestBody WishlistDTO wishlistDTO) {
        Wishlist saved = wishlistService.createWishlist(convertToEntity(wishlistDTO));
        return convertToDTO(saved);
    }

    @PostMapping("/{wishlistId}/records/{recordId}")
    public WishlistDTO addRecordToWishlist(@PathVariable Long wishlistId, @PathVariable Long recordId) {
        Wishlist updated = wishlistService.addToWishlist(recordId, wishlistId);
        return convertToDTO(updated);
    }

    @DeleteMapping("/{wishlistId}/records/{recordId}")
    public WishlistDTO removeRecordFromWishlist(@PathVariable Long wishlistId, @PathVariable Long recordId) {
        Wishlist updated = wishlistService.removeFromWishlist(recordId, wishlistId);
        return convertToDTO(updated);
    }

    private Wishlist convertToEntity(WishlistDTO wishlistDTO) {
        Wishlist wishlist = new Wishlist();
        wishlist.setId(wishlistDTO.getId());

        Set<Record> records = new HashSet<>();
        if (wishlistDTO.getRecords() != null) {
            records = wishlistDTO.getRecords().stream()
                    .map(this::convertRecordToEntity)
                    .collect(Collectors.toSet());
            records.forEach(record -> record.setWishlist(wishlist));
        }
        wishlist.setRecords(records);

        return wishlist;
    }

    private WishlistDTO convertToDTO(Wishlist wishlist) {
        Set<RecordDTO> records = wishlist.getRecords() == null
                ? new HashSet<>()
                : wishlist.getRecords().stream()
                .map(this::convertRecordToDTO)
                .collect(Collectors.toSet());

        return new WishlistDTO(wishlist.getId(), records);
    }

    private RecordDTO convertRecordToDTO(Record record) {
        return new RecordDTO(
                record.getId(),
                record.getArtist(),
                record.getTitle(),
                record.getGenre(),
                record.getFavourite(),
                record.getMedium(),
                record.getYear()
        );
    }

    private Record convertRecordToEntity(RecordDTO recordDTO) {
        Record record = new Record();
        record.setId(recordDTO.getId());
        record.setArtist(recordDTO.getArtist());
        record.setTitle(recordDTO.getTitle());
        record.setGenre(recordDTO.getGenre());
        record.setFavourite(recordDTO.isFavourite());
        record.setMedium(recordDTO.getMedium());
        record.setYear(recordDTO.getYear());
        return record;
    }
}