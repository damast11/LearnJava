package eviction.strategy.impl;

import eviction.strategy.Eviction;
import service.impl.CacheGuava;


public class EvictionLru implements Eviction {

    private final CacheGuava cacheGuava;

    public EvictionLru(CacheGuava cacheGuava) {
        this.cacheGuava = cacheGuava;
    }

    @Override
    public void execute() {
        cacheGuava.remove();
    }
}
