package statistic;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class AverageTimeCounter {

    private final List<Long> times = new ArrayList<>();

    public void addTimes(Long time) {
        times.add(time);
    }

    public Long countAverageTime(long time) {
        times.add(time);
        var size = times.size();
        var aLong = times.stream().reduce(Long::sum).orElseThrow(NullPointerException::new);
        return aLong/size;
    }
}
