import eviction.strategy.Eviction;
import eviction.strategy.impl.EvictionFifo;
import eviction.strategy.impl.EvictionLfu;
import eviction.strategy.impl.EvictionLru;
import repository.CacheRepository;
import service.impl.CacheEntryServiceImpl;
import service.impl.CacheGuava;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        var cacheEntryService = new CacheEntryServiceImpl(new CacheRepository());
        var cacheGuava = new CacheGuava();
        Eviction eviction;
        var sc = new Scanner(System.in);
        var strategy = "";
        System.out.println("add some entries");

            var data = sc.next();
            cacheEntryService.addCacheEntry(1, data);
            cacheEntryService.addCacheEntry(2, data);
            cacheEntryService.addCacheEntry(3, data);
            cacheEntryService.getCacheEntry(1);
            cacheEntryService.getCacheEntry(2);
            cacheGuava.putVal("1", "1");
            cacheGuava.putVal("2", "2");
            cacheGuava.putVal("3", "3");
            cacheGuava.fetchVal("1");
            cacheGuava.fetchVal("2");
            System.out.println("Choose strategy");
             strategy = "lru";

        if ("lfu".equalsIgnoreCase(strategy)) {
            eviction = new EvictionLfu(cacheEntryService);
            eviction.execute();
        }
        if ("fifo".equalsIgnoreCase(strategy)) {
            eviction = new EvictionFifo(cacheEntryService);
            eviction.execute();
        }
        else {
            eviction = new EvictionLru(cacheGuava);
            eviction.execute();
        }
        sc.close();
    }
}
