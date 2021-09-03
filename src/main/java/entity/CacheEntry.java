package entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CacheEntry {
    private String data;
    private int frequency;
    private int numberInQueue;
}
