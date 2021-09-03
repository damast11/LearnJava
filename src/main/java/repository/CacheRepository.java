package repository;

import entity.CacheEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class CacheRepository {
    private Map<Integer, CacheEntry> cacheEntries = new ConcurrentHashMap<>(100000);
}
