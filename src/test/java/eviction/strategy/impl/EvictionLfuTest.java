package eviction.strategy.impl;

import entity.CacheEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.CacheEntryService;

import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EvictionLfuTest {

    @Mock
    CacheEntryService cacheEntryService;

    @InjectMocks
    EvictionLfu testInstance;

    @Test
    void shouldExecute() {
        var cache = createCache();
        given(cacheEntryService.getLFUKey()).willReturn(1);
        given(cacheEntryService.getCache()).willReturn(cache);

        testInstance.execute();

        verify(cache).remove(1);
    }

    private Map<Integer, CacheEntry> createCache() {
        var cacheEntry1 = new CacheEntry().setNumberInQueue(1).setData("test1").setFrequency(1);
        var cacheEntry2 = new CacheEntry().setNumberInQueue(2).setData("test2").setFrequency(2);
        var cacheEntry3 = new CacheEntry().setNumberInQueue(3).setData("test3").setFrequency(3);
        return Map.of(1, cacheEntry1, 2, cacheEntry2, 3, cacheEntry3);
    }

}