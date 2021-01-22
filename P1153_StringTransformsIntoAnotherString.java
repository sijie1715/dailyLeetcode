package Solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class P1153_StringTransformsIntoAnotherString {
    public boolean canConvert(String str1, String str2) {
        // record transform mapping
        // same char should transform into same char; otherwise return false
        // if value is later a key, then there's a linkedlist; need to transform from back to start
        // e.g. "aabcc"->"ccdee" (a->c->e) need to transform c->e first
        // if linkedlist has cycle then need an extra tmp variable to break the cycle
        // so values.size() < 26 (if all 26 are needed then can't transform)
        if (str1.equals(str2)) return true;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            if (map.containsKey(str1.charAt(i)) && map.get(str1.charAt(i)) != str2.charAt(i)) {
                return false;
            }
            map.put(str1.charAt(i), str2.charAt(i));
        }
        return new HashSet<Character>(map.values()).size() < 26;
    }
}
