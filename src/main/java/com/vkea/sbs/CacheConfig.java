package com.vkea.sbs;

import com.codahale.metrics.MetricRegistry;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import com.vkea.sbs.cache.CacheMetricsCollector;
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

    @Bean
    public CacheManager cacheManager(Ticker ticker) {
        CaffeineCache programDetailCache = buildCache("programDetail", ticker, 30);
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(programDetailCache));

//        manager.getCache("").getNativeCache().
        programDetailCache.getNativeCache().stats();
        CacheMetricsCollector cacheMetrics = new CacheMetricsCollector().register();
//        crateCacheStats(programDetailCache);
        return manager;
    }

    private CaffeineCache buildCache(String name, Ticker ticker, int minutesToExpire) {
        // Use a registry that is exported using a Reporter (via console, JMX, Graphite, etc)
        MetricRegistry registry = new MetricRegistry();
        return new CaffeineCache(name, Caffeine.newBuilder()
//                .recordStats()// () -> new MetricsStatsCounter(registry, "example") can turn on statistics collection. The Cache.stats() method returns a CacheStats which provides statistics such as
//                .expireAfterWrite(pgExpireTime, TimeUnit.SECONDS)
                .maximumSize(pgCacheSize)
                .ticker(ticker)
                .build());
    }

    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }

}
