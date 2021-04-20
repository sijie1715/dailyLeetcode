package Solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class P1146_SnapshotArray {
    // List<TreeMap<snapCount, val>>
    List<TreeMap<Integer, Integer>> map;
    int snapCount;

    public void SnapshotArray(int length) {
        snapCount = 0;
        map = new ArrayList<>();
        // if call snap at 0, all vals are 0
        for (int i = 0; i < length; i++) {
            map.add(new TreeMap<>());
            map.get(i).put(0, 0);
        }
    }

    public void set(int index, int val) {
        map.get(index).put(snapCount, val);
    }

    public int snap() {
        return snapCount++;
    }

    public int get(int index, int snap_id) {
        return map.get(index).floorEntry(snap_id).getValue();
    }
}
