package Solutions;

import java.util.TreeMap;

public class P732_MyCalendarIII {
    // <start/end time, # of ongoing events>
    private TreeMap<Integer, Integer> events;

//    public MyCalendarThree() {
//        events = new TreeMap<>();
//    }

    public int book(int start, int end) {
        events.put(start, events.getOrDefault(start, 0) + 1);
        events.put(end, events.getOrDefault(end, 0) - 1);
        // running sum of values = # of ongoing events
        int runningSum = 0, res = 0;
        for (int value : events.values()) {
            runningSum += value;
            res = Math.max(res, runningSum);
        }
        return res;
    }
}
