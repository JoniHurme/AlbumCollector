package com.albumcollector.albumcollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AlbumCollectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlbumCollectorApplication.class, args);
    }

//    @GetMapping("/")
//    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return String.format("Hello %s!", name);
//    }
//
//    @GetMapping("/main")
//    public String mainView(@RequestParam(name = "name", required = false, defaultValue = "World")String name, Model model) {
//        model.addAttribute("name", name);
//        return "MainView";
//    }

}
