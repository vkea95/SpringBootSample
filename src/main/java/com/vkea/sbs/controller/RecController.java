package com.vkea.sbs.controller;

import com.vkea.sbs.model.ProgramListResponse;
import com.vkea.sbs.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/video/rec", headers = "Accept=application/json")
public class RecController {

    private static Logger logger = LoggerFactory.getLogger(RecController.class);
    @Autowired
    VideoService videoService;
    @RequestMapping(value = "/top", method = RequestMethod.GET)
    public ProgramListResponse getTopProgramList(
            @RequestParam(value = "userId", required = true) String userId
    ) {
        logger.debug("userId ={} ", userId);

        logger.info("info log ");
        logger.warn("warn log ");
        logger.error("error log ");
        return videoService.getTop();
    }

}
