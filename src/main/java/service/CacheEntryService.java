package service;

import entity.CacheEntry;

import java.util.Map;

public interface CacheEntryService {

    void addCacheEntry(int key, String data);
    String getCacheEntry(int key);
    Map<Integer, CacheEntry> getCache();
    int getLFUKey();

}
