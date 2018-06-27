package com.vkea.sbs.service.impl;

import com.vkea.sbs.model.Program;
import com.vkea.sbs.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@CacheConfig(cacheNames = {"programDetail"})
public class CacheServiceImpl implements CacheService {
    private static Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Override
    @Cacheable
    public Program getProgram(String programId) {
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        logger.debug("I'm in getProgram method.{}",time);
        return new Program();
    }
}
