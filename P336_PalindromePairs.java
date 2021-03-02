package Solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P336_PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) map.put(words[i], i);
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) {
                String str1 = words[i].substring(0, j);     // j<=words[i].length because substring right open
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    // abc->a,bc->need "cb" to make "cb abc"
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                        List<Integer> list = new ArrayList<>();
                        list.add(map.get(str2rvs));
                        list.add(i);
                        res.add(list);
                    }
                }
                if (isPalindrome(str2)) {
                    // abc->ab,c->need "ba" to make "abc ba"
                    // *** need to avoid str2=="" because ["abc","cba"]
                    // *** ("abc"str1=="") is the same as ("cba"str2=="")
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length() != 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(map.get(str1rvs));
                        res.add(list);
                    }
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}
