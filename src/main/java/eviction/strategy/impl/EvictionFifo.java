package eviction.strategy.impl;

import entity.CacheEntry;
import eviction.strategy.AbstractEviction;
import eviction.strategy.Eviction;
import listener.RemoveListener;
import service.CacheEntryService;

import java.util.Map;
import java.util.stream.Collectors;

public class EvictionFifo extends AbstractEviction implements Eviction {

    public EvictionFifo(CacheEntryService cacheEntryService) {
        super(cacheEntryService);
    }

    @Override
    public void execute() {
        var eldestEntry = findKeyByNumberInQueue();
        new RemoveListener(eldestEntry);
        flush(eldestEntry);
    }

    private int findKeyByNumberInQueue() {
        var eldestEntry = findEldestEntry();
        return cacheEntryService.getCache().entrySet().stream()
                .filter(integerCacheEntryEntry -> integerCacheEntryEntry.getValue().getNumberInQueue() == eldestEntry)
                .map(Map.Entry::getKey).collect(Collectors.toList()).get(0);
    }

    private int findEldestEntry() {
        return cacheEntryService.getCache().values().stream()
                .mapToInt(CacheEntry::getNumberInQueue)
                .min()
                .orElseThrow(NullPointerException::new);
    }
}
