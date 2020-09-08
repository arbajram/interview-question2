package com.example.demo.utils;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.stereotype.Component;

@Component
public class CacheHelper {

    private CacheManager cacheManager;
    private Cache<Integer, Integer[]> storedArrayCache;

    public CacheHelper() {
        cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder().build();
        cacheManager.init();

        storedArrayCache = cacheManager
                .createCache("storedArray", CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(
                                Integer.class, Integer[].class,
                                ResourcePoolsBuilder.heap(1000)));
    }

    public Cache<Integer, Integer[]> getStoredArrayCacheFromCacheManager() {
        return cacheManager.getCache("storedArray", Integer.class, Integer[].class);
    }
}
