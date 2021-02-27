package Solutions;

import java.util.HashSet;
import java.util.Set;

public class P421_MaximumXOROfTwoNumbersInAnArray {
    public int findMaximumXOR1(int[] nums) {
        /*
        1. check starting from the left most bit see if it can be 1; use bitmask to check bit by bit
        2. a^b=c -> a^b^b=a=c^b -> get desired result c first, check if any b can produce b^c=a where a is in nums
        */
        int mask = 0;
        int maxXor = 0;
        for (int i = 31; i >= 0; i--) {
            mask |= (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }
            // curXor is next desired result
            int curXor = maxXor | (1 << i);
            for (int prefix : set) {
                // if (x==curXor^prefix) then (x^prefix==curXor)
                if (set.contains(curXor ^ prefix)) {
                    maxXor = curXor;
                    break;
                }
            }
        }
        return maxXor;
    }

    public int findMaximumXOR(int[] nums) {
        // 32-level trie to store all numbers
        TrieNode root = new TrieNode();
        for (int num : nums) {
            TrieNode cur = root;
            for (int i = 31; i >= 0; i--) {
                // >>> is unsigned shift
                int curBit = (num >>> i) & 1;
                if (cur.next[curBit] == null) {
                    cur.next[curBit] = new TrieNode();
                }
                cur = cur.next[curBit];
            }
        }

        // try to make every bit 1
        int maxXor = 0;
        for (int num : nums) {
            TrieNode cur = root;
            int curXor = 0;
            for (int i = 31; i >= 0; i--) {
                int curBit = (num >>> i) & 1;
                // if exists node that can xor to 1
                if (cur.next[curBit ^ 1] != null) {
                    curXor |= (1 << i);
                    cur = cur.next[curBit ^ 1];
                } else {
                    cur = cur.next[curBit];
                }
            }
            maxXor = Math.max(maxXor, curXor);
        }
        return maxXor;
    }

    private class TrieNode {
        TrieNode[] next;

        TrieNode() {
            next = new TrieNode[2];
        }
    }
}
