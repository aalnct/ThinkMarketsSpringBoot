package com.thinkmarkets.ThinkMarketsSpringBoot.service.serviceimpl;

// refresh cache

import com.thinkmarkets.ThinkMarketsSpringBoot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheImplementation {

    @Autowired
    private CacheManager cacheManager;

    @Value("${evict.cache.every.hour}")
    private boolean evictCacheEveryHour;

    /**
     * Evicting cache in every one hour
     */
    @Scheduled(cron = "${evict.cache.hourly.schedule}")
    public void evictAllCache () {
        if (evictCacheEveryHour) {
            cacheManager.getCacheNames().stream()
                    .forEach(cacheNames -> cacheManager.getCacheNames().clear());
        }
    }
}
