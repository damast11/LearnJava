package repository;

import entity.CacheEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MemoryRepository {
    List<CacheEntry> cacheEntries = new ArrayList<>();
}
