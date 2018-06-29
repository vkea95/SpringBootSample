package com.vkea.sbs.controller;

import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.vkea.sbs.model.Program;
import com.vkea.sbs.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "user", description = "Rest API for video content", tags = "Video API")
public class ContentController {
    private static Logger logger = LoggerFactory.getLogger(ContentController.class);
    @Autowired
    VideoService videoService;

    @Autowired
    CaffeineCache caffeineCache;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "Show top video list", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The resource not found")

    })
    public List<Program> getProgramList(
            @RequestParam(value = "programIds", required = true) String programIds,
            @RequestParam(value = "userId", required = true) String userId
    ) {
        String[] idList = programIds.split(",");
        return videoService.getProgramDetailList(Arrays.asList(idList));
    }


    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String stats() {
        CacheStats cacheStats = caffeineCache.getNativeCache().stats();
//        return "";
        return cacheStats.hitCount() + " " + cacheStats.missCount() + " " + cacheStats.evictionCount() + " " + cacheStats.averageLoadPenalty();
    }
}

