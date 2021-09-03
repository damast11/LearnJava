package service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import entity.CacheEntry;
import listener.RemoveListener;
import lombok.RequiredArgsConstructor;
import repository.CacheRepository;
import service.CacheEntryService;
import statistic.AverageTimeCounter;

import java.util.Map;

@RequiredArgsConstructor
public class CacheEntryServiceImpl implements CacheEntryService {

    private static final int CAPACITY = 100000;

    private static int countInQueue = 0;

    private final CacheRepository cacheRepository;

    @Override
    public synchronized void addCacheEntry(int key, String data) {
        var start = System.currentTimeMillis();
        var cacheEntries = cacheRepository.getCacheEntries();
        if (!isFull()) {
            var temp = new CacheEntry();
            temp.setData(data);
            temp.setFrequency(0);
            temp.setNumberInQueue(countInQueue++);
            cacheEntries.put(key, temp);
        }else {
            var entryKeyToBeRemoved = getLFUKey();
            cacheEntries.remove(entryKeyToBeRemoved);
            new RemoveListener(entryKeyToBeRemoved);
            var temp = new CacheEntry();
            temp.setData(data);
            temp.setFrequency(0);
            cacheEntries.put(key, temp);
            var end = System.currentTimeMillis();
            AverageTimeCounter.addTimes(end - start);
        }
    }

    @Override
    public String getCacheEntry(int key) {
        var cacheEntries = cacheRepository.getCacheEntries();
        if(cacheEntries.containsKey(key)) {
            var temp = cacheEntries.get(key);
            var frequency = temp.getFrequency();
            temp.setFrequency(++frequency);
            cacheEntries.put(key, temp);
            return temp.getData();
        }
        return null; // cache miss
    }

    @Override
    public Map<Integer, CacheEntry> getCache() {
        return cacheRepository.getCacheEntries();
    }

    public int getLFUKey() {
        var key = 0;
        var minFreq = Integer.MAX_VALUE;

        for(Map.Entry<Integer, CacheEntry> entry : cacheRepository.getCacheEntries().entrySet())
        {
            if(minFreq > entry.getValue().getFrequency())
            {
                key = entry.getKey();
                minFreq = entry.getValue().getFrequency();
            }
        }
        return key;
    }
    private boolean isFull() {
        return cacheRepository.getCacheEntries().size() == CAPACITY;
    }
}
