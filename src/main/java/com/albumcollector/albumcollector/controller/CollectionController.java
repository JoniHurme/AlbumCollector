package com.albumcollector.albumcollector.controller;


import com.albumcollector.albumcollector.model.dto.CollectionDTO;
import com.albumcollector.albumcollector.model.dto.RecordDTO;
import com.albumcollector.albumcollector.model.entity.Collection;
import com.albumcollector.albumcollector.model.entity.Record;
import com.albumcollector.albumcollector.service.CollectionService;
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
@RequestMapping("/api/collection")
@CrossOrigin(origins = "http://localhost:5173")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping
    public List<CollectionDTO> getCollections() {
        return collectionService.getCollection()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CollectionDTO createCollection(@RequestBody CollectionDTO collectionDTO) {
        Collection saved = collectionService.createCollection(convertToEntity(collectionDTO));
        return convertToDTO(saved);
    }

    @PostMapping("/{collectionId}/records/{recordId}")
    public CollectionDTO addRecordToCollection(@PathVariable Long collectionId, @PathVariable Long recordId) {
        Collection updated = collectionService.addToCollection(recordId, collectionId);
        return convertToDTO(updated);
    }

    @DeleteMapping("/{collectionId}/records/{recordId}")
    public CollectionDTO removeRecordFromCollection(@PathVariable Long collectionId, @PathVariable Long recordId) {
        Collection updated = collectionService.removeFromCollection(recordId, collectionId);
        return convertToDTO(updated);
    }

    private Collection convertToEntity(CollectionDTO collectionDTO) {
        Collection collection = new Collection();
        collection.setId(collectionDTO.getId());

        Set<Record> records = new HashSet<>();
        if (collectionDTO.getRecords() != null) {
            records = collectionDTO.getRecords().stream()
                    .map(this::convertRecordToEntity)
                    .collect(Collectors.toSet());
            records.forEach(record -> record.setCollection(collection));
        }
        collection.setRecords(records);

        return collection;
    }

    private CollectionDTO convertToDTO(Collection collection) {
        Set<RecordDTO> records = collection.getRecords() == null
                ? new HashSet<>()
                : collection.getRecords().stream()
                .map(this::convertRecordToDTO)
                .collect(Collectors.toSet());

        return new CollectionDTO(collection.getId(), records);
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
//                record.getCollection()
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
//        record.setCollection(recordDTO.getCollection());
        return record;
    }
}
