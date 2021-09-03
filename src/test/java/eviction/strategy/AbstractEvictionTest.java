package eviction.strategy;

import entity.CacheEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.CacheEntryService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AbstractEvictionTest {

    @Mock
    CacheEntryService cacheEntryService;

    @Mock
    Map<Integer, CacheEntry> map;

    @Mock
    AbstractEviction testInstance;

    @Test
    void shouldFlush() {

        given(cacheEntryService.getCache()).willReturn(map);

        testInstance.flush(1);

        verify(map).remove(1);

    }

    private Map<Integer, CacheEntry> createCache() {
        var cacheEntry1 = new CacheEntry().setNumberInQueue(1).setData("test1").setFrequency(1);
        var cacheEntry2 = new CacheEntry().setNumberInQueue(2).setData("test2").setFrequency(2);
        var cacheEntry3 = new CacheEntry().setNumberInQueue(3).setData("test3").setFrequency(3);
        return Map.of(1, cacheEntry1, 2, cacheEntry2, 3, cacheEntry3);
    }

}