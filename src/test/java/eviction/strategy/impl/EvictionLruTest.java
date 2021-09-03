package eviction.strategy.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.impl.CacheGuava;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EvictionLruTest {

    @Mock
    CacheGuava cacheGuava;

    @InjectMocks
    EvictionLru testInstance;

    @Test
    void shouldExecute() {

        testInstance.execute();

        verify(cacheGuava).remove();
    }

}