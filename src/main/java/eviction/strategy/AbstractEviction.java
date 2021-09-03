package eviction.strategy;

import lombok.RequiredArgsConstructor;
import service.CacheEntryService;

@RequiredArgsConstructor
public abstract class AbstractEviction {

    public final CacheEntryService cacheEntryService;

    public void flush(Integer key) {
        var cache = cacheEntryService.getCache();
        cache.remove(key);
    }
}
