package Solutions;

import java.util.*;

public class P269_AlienDictionary {
    public String alienOrder(String[] words) {
        // check every consecutive 2 words w1,w2
        // the first (ONLY the first) different char in w1,w2 (c1,c2) will note an edge c1->c2
        // update indegree.get(c2) and graph.get(c1).add(c2)
        // topsort with bfs
        // *** if w1.length>w2.length&&w1.startswith(w2) then return ""
        // *** c1 from deque might not have c2, continue
        Map<Character, Integer> indegree = new HashMap<>();
        Map<Character, List<Character>> graph = new HashMap<>();

        // record all distinct chars in indegree; if final string length<indegree.size() then has cycle
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                indegree.put(word.charAt(i), 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int j;
            for (j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    graph.putIfAbsent(w1.charAt(j), new ArrayList<>());
                    indegree.put(w2.charAt(j), indegree.get(w2.charAt(j)) + 1);
                    graph.get(w1.charAt(j)).add(w2.charAt(j));
                    break;
                }
            }
            // if w1 startswith w2 && w1 longer then wrong
            if (j == w2.length() && w1.length() > w2.length()) return "";
        }
        Deque<Character> d = new ArrayDeque<>();
        for (char key : indegree.keySet()) {
            if (indegree.get(key) == 0) d.add(key);
        }
        StringBuilder sb = new StringBuilder();
        while (!d.isEmpty()) {
            char curr = d.poll();
            sb.append(curr);
            // *** c1 from deque might not have c2, continue
            if (!graph.containsKey(curr)) continue;
            for (char next : graph.get(curr)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) d.add(next);
            }
        }

        return sb.length() == indegree.size() ? sb.toString() : "";
    }
}
