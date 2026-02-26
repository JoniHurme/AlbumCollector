package com.albumcollector.albumcollector.controller;

import com.albumcollector.albumcollector.service.RecordService;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/records")
public class RecordController {

    private final RecordService service;

    public RecordController(RecordService service) {
        this.service = service;
    }

}
