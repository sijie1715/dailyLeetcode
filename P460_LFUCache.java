package Solutions;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class P460_LFUCache {
    HashMap<Integer, Integer> vals;     // <key, val>
    HashMap<Integer, Integer> counts;   // <key, count>
    HashMap<Integer, LinkedHashSet<Integer>> sameCounts;    // <count, Set<keys>>
    int min, cap;

    public void LFUCache(int capacity) {
        this.cap = capacity;
        this.min = -1;
        this.vals = new HashMap<>();
        this.counts = new HashMap<>();
        this.sameCounts = new HashMap<>();
        sameCounts.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if (!vals.containsKey(key)) return -1;
        // update counts
        int curCount = counts.get(key);
        counts.put(key, curCount + 1);
        sameCounts.get(curCount).remove(key);
        // update min
        if (curCount == min && sameCounts.get(curCount).size() == 0) {
            min++;
        }
        if (!sameCounts.containsKey(curCount + 1)) {
            sameCounts.put(curCount + 1, new LinkedHashSet<>());
        }
        sameCounts.get(curCount + 1).add(key);
        return vals.get(key);
    }

    public void put(int key, int value) {
        if (cap <= 0) return;
        if (vals.containsKey(key)) {
            vals.put(key, value);
            get(key);    // min and count are updated here
            return;
        }
        // new key; check cap and remove LFU; min set to 1
        if (vals.size() == cap) {
            int del = sameCounts.get(min).iterator().next();
            vals.remove(del);
            counts.remove(del);
            sameCounts.get(min).remove(del);
        }
        min = 1;
        vals.put(key, value);
        counts.put(key, 1);
        sameCounts.get(1).add(key);
    }
}
