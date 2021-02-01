package Solutions;

import java.util.*;

public class P126_WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // check if endWord is in wordList
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;

        // get begin/endword idx
        int si = -1, ei = -1;
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).equals(endWord)) ei = i;
        }
        wordList.add(beginWord);
        si = wordList.size() - 1;

        // build graph
        int n = wordList.size();
        Map<String, List<String>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String curr = wordList.get(i);
            graph.put(curr, new ArrayList<>());
            if (i == ei) continue;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                String next = wordList.get(j);
                if (isConnected(curr, next)) graph.get(curr).add(next);
            }
        }

        // bfs
        Queue<List<String>> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(new ArrayList<>(Arrays.asList(beginWord)));
        int step = 0;
        boolean finish = false;
        while (!q.isEmpty()) {
            Set<String> localVisit = new HashSet<>();
            for (int sz = q.size(); sz > 0; sz--) {
                List<String> currPath = q.poll();
                String currNode = currPath.get(step);
                if (currNode.equals(endWord)) {
                    finish = true;
                    q.offer(currPath);
                    continue;
                }
                for (String nextNode : graph.get(currNode)) {
                    List<String> nextPath = new ArrayList(currPath);
                    if (!visited.contains(nextNode)) {
                        nextPath.add(nextNode);
                        q.offer(nextPath);
                        localVisit.add(nextNode);
                    }
                }
            }
            visited.addAll(localVisit);
            step++;
            if (finish) break;
        }

        // get all paths that end with endWord to res
        for (List<String> path : q) {
            if (path.get(step - 1).equals(endWord)) res.add(new ArrayList(path));
        }

        return res;
    }

    private boolean isConnected(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (count > 1) return false;
            if (s1.charAt(i) != s2.charAt(i)) count++;
        }
        return count == 1;
    }
}
