package Solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P140_WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        //map<s, list of strings of broken down s>
        return dfs(s, wordDict, new HashMap<String, List<String>>());
    }

    private List<String> dfs(String s, List<String> wordDict, Map<String, List<String>> map) {
        // memo to prune duplicated branches
        if (map.containsKey(s)) return map.get(s);
        List<String> res = new ArrayList<>();
        // base case
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> nextList = dfs(s.substring(word.length()), wordDict, map);
                for (String next : nextList) {
                    res.add(word + (next.length() == 0 ? "" : " ") + next);
                }
            }
        }
        map.put(s, res);
        return res;
    }
}
