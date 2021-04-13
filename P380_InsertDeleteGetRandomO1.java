package Solutions;

import java.util.*;

public class P380_InsertDeleteGetRandomO1 {
    // HashMap for O(1) read
    // need to store keys and track keyset size
    // use Random.nextInt(size) for randomize index
    // make sure map key is consecutive

    // <val, index in list>
    Map<Integer, Integer> map;
    List<Integer> nums;
    Random rand;

    /**
     * Initialize your data structure here.
     */
    public void RandomizedSet() {
        map = new HashMap<>();
        nums = new ArrayList<>();
        rand = new Random();

    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, nums.size());
        nums.add(val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int idx = map.get(val);
        if (idx != nums.size() - 1) {
            // swap with last element;
            int swapVal = nums.get(nums.size() - 1);
            nums.set(idx, swapVal);
            map.put(swapVal, idx);
        }
        map.remove(val);
        nums.remove(nums.size() - 1);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int idx = rand.nextInt(nums.size());
        return nums.get(idx);
    }
}
