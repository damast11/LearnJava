package service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;

public class CacheGuava {

    private Cache<String, String> cache ;

    public CacheGuava() {
        cache = CacheBuilder.newBuilder().maximumSize(10).concurrencyLevel(1).build();
    }

    public String fetchVal(String key) {
        String val = cache.getIfPresent(key);
        return val;
    }

    public void putVal(String key, String value) {
        cache.put(String.valueOf(key), value);
    }

    public CacheStats getStatistic() {
        return cache.stats();
    }

    public void remove () {
        cache.cleanUp();
    }
}
