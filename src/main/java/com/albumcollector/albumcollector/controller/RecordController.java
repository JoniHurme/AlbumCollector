package com.albumcollector.albumcollector.controller;

import com.albumcollector.albumcollector.model.dto.RecordDTO;
import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.service.RecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/records")
@CrossOrigin(origins = "http://localhost:5173") // For Vite dev server
//@CrossOrigin(origins = "http:/localhost:8085")
public class RecordController {

    private final RecordService service;

    public RecordController(RecordService service) {
        this.service = service;
    }

    @GetMapping
    public List<RecordDTO> getAllRecords() {
        return service.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RecordDTO addRecord(@RequestBody RecordDTO recordDTO) {
        Record record = convertToEntity(recordDTO);
        Record saved = service.insertNewRecord(record, recordDTO.getCollectionId(), recordDTO.getWishlistId());
        return convertToDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        service.removeRecord(id);
    }

    private RecordDTO convertToDTO(Record record) {
        return new RecordDTO(
                record.getId(),
                record.getArtist(),
                record.getTitle(),
                record.getGenre(),
                record.getFavourite(),
                record.getMedium(),
                record.getYear(),
                record.getCollection() == null ? null : record.getCollection().getId(),
                record.getWishlist() == null ? null : record.getWishlist().getId()
        );
    }

    private Record convertToEntity(RecordDTO recordDTO) {
        Record record = new Record();
        record.setId(recordDTO.getId());
        record.setArtist(recordDTO.getArtist());
        record.setTitle(recordDTO.getTitle());
        record.setGenre(recordDTO.getGenre());
        record.setFavourite(recordDTO.isFavourite());
        record.setMedium(recordDTO.getMedium());
        record.setYear(recordDTO.getYear());
        record.setCollection(recordDTO.getCollectionId() == null ? null : service.getCollectionId(recordDTO.getCollectionId()));
        record.setWishlist(recordDTO.getWishlistId() == null ? null : service.getWishlistId(recordDTO.getWishlistId()));
        return record;
    }
}
