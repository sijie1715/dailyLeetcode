package Solutions;

import java.util.HashMap;
import java.util.Map;

public class P1371_FindTheLongestSubstringContainingVowelsInEvenCounts {
    public int findTheLongestSubstring(String s) {
        // use bit map to record occurance of aeiou (only record odd/even)
        // xor "^=" can be used as toggle
        // cur ^= 1 << "aeiou".indexOf(vowel) will toggle count of vowel
        // "aeiou".indexOf(non-vowel)=-1, so cur ^= 1 << ("aeiou".indexOf(vowel) + 1) >> 1

        // HashMap to record first occurance of each cur, map.put(0,-1)
        // (i - last same cur index) is length of valid substring
        // e.g. "aaa", i=0or2 has cur==1, so 2-0; i=1 has cur==0, so 1-(-1)
        int res = 0, cur = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < s.length(); i++) {
            cur ^= 1 << ("aeiou".indexOf(s.charAt(i)) + 1) >> 1;
            map.putIfAbsent(cur, i);
            res = Math.max(res, i - map.get(cur));
        }
        return res;
    }
}
