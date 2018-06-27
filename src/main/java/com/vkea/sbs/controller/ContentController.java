package com.vkea.sbs.controller;

import com.vkea.sbs.model.Program;
import com.vkea.sbs.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/video/content", headers = "Accept=application/json")
public class ContentController {
    private static Logger logger = LoggerFactory.getLogger(ContentController.class);
    @Autowired
    VideoService videoService;

    @Autowired
    CaffeineCache caffeineCache;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Program> getProgramList(
            @RequestParam(value = "programIds", required = true) String programIds,
            @RequestParam(value = "userId", required = true) String userId
    ) {
        String[] idList = programIds.split(",");
        return videoService.getProgramDetailList(Arrays.asList(idList));
    }


    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String stats() {
        return "";
//        return cacheStats.hitCount() + " " + cacheStats.missCount() + " " + cacheStats.evictionCount();
    }
}

