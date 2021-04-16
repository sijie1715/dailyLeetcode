package Solutions;

import java.util.*;

public class P381_InsertDeleteGetRandomO1_DuplicatesAllowed {
    Map<Integer, Set<Integer>> map;
    List<Integer> nums;
    Random rand;

    /**
     * Initialize your data structure here.
     */
    public void RandomizedCollection() {
        map = new HashMap<>();
        nums = new ArrayList<>();
        rand = new Random();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        boolean contain = map.containsKey(val);
        if (!map.containsKey(val)) map.put(val, new HashSet<>());
        map.get(val).add(nums.size());
        nums.add(val);
        return !contain;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        boolean contain = map.containsKey(val);
        if (!contain) return false;
        int idx = map.get(val).iterator().next();
        map.get(val).remove(idx);
        if (idx != nums.size() - 1) {
            int lastone = nums.get(nums.size() - 1);
            nums.set(idx, lastone);
            map.get(lastone).remove(nums.size() - 1);
            map.get(lastone).add(idx);
        }
        nums.remove(nums.size() - 1);

        if (map.get(val).isEmpty()) map.remove(val);
        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}
