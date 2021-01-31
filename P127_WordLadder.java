package Solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P127_WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // check endword is in wordlist
        wordList.add(beginWord);
        int si = wordList.size() - 1, ei = -1, n = wordList.size();
        for (int i = 0; i < wordList.size(); i++) {
            if (i == si && wordList.get(i) != endWord) return 0;
            if (wordList.get(i).equals(endWord)) {
                ei = i;
                break;
            }
        }

        // build graph based on word connection (differ by 1 char)
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            if (i == ei) continue;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (isConnected(wordList.get(i), wordList.get(j))) {
                    graph[i].add(j);
                }
            }
        }

        // bfs form beginword
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.offer(si);
        visited[si] = true;
        for (int step = 1; !q.isEmpty(); step++) {
            for (int sz = q.size(); sz > 0; sz--) {
                int curr = q.poll();
                if (curr == ei) return step;
                for (int next : graph[curr]) {
                    if (!visited[next]) {
                        q.offer(next);
                        visited[next] = true;
                    }
                }
            }
        }
        return 0;
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
