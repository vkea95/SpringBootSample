package com.vkea.sbs.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${cache.programDetail.maximumSize}")
    private int pgCacheSize;
    @Value("${cache.programDetail.expireAfterAccess}")
    private int pgExpireTime;

//    @Bean
//    public MeterRegistry meterRegistry(){
//        return new PrometheusMeterRegistry();
//    }

    @Bean
    public CacheManager cacheManager(Ticker ticker) {

        SimpleCacheManager manager = new SimpleCacheManager();
        CaffeineCache programDetailCache = programDetailCache(ticker);
        manager.setCaches(Arrays.asList(programDetailCache));

        return manager;
    }

    @Bean
    public CaffeineCache programDetailCache(Ticker ticker) {
        String name = "programDetail";
        return new CaffeineCache(name, Caffeine.newBuilder()
                .recordStats()
//                .expireAfterWrite(pgExpireTime, TimeUnit.SECONDS)
                .maximumSize(pgCacheSize)
                .ticker(ticker)
                .build());
    }

//
//    @Bean
//    public CacheMetricsCollector cacheMetrics(CaffeineCache programDetailCache) {
//        CacheMetricsCollector cacheMetrics = new CacheMetricsCollector().register();
////        cacheMetrics
//        cacheMetrics.addCache("programDetailLabel", programDetailCache.getNativeCache());
//
//        return cacheMetrics;
//    }

    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }

}
