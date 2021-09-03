package eviction.strategy.impl;

import eviction.strategy.AbstractEviction;
import eviction.strategy.Eviction;
import listener.RemoveListener;
import service.CacheEntryService;

public class EvictionLfu extends AbstractEviction implements Eviction {

    public EvictionLfu(CacheEntryService cacheEntryService) {
        super(cacheEntryService);
    }

    @Override
    public void execute() {
        var lfuKey = cacheEntryService.getLFUKey();
        flush(lfuKey);
        new RemoveListener(lfuKey);
    }
}
