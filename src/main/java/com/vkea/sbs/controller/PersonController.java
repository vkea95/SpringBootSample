package com.vkea.sbs.controller;

import com.vkea.sbs.model.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@RestController
//@Timed
class PersonController {

    @Autowired
    CaffeineCache prgoramCache;
    Map<Integer, Program> programMap = new HashMap<>();
//    Counter steveCounter;

//    Timer findPersonTimer;
    List<Program> people = new ArrayList<Program>();


//    public PersonController(MeterRegistry registry) {
//        // constructs a gauge to monitor the size of the population
////        registry.mapSize(programMap, "population");
//
////        registry.mapSize(programMap, "programDetailMap");
//        // registers a gauge to observe the size of the population
//        registry.collectionSize(people, "population");
//
//        // register a counter of questionable usefulness
//        steveCounter = registry.counter("find_steve", "steve");
//
//        // register a timer -- though for request timing it is easier to use @Timed
//        findPersonTimer = registry.timer("http_requests", "method", "GET");
//    }


    @GetMapping("/api/program")
    public List<Program> listPeople() {
        return new ArrayList<>();
    }

    @GetMapping("/api/program/")
    public Program findPerson(@RequestParam(value = "id", required = true) String id) {
        return programMap.get(id);
    }
}