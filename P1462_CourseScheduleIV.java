package Solutions;

import java.util.*;

public class P1462_CourseScheduleIV {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        int[] indegree = new int[n];
        // graph<prereq,courses>
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        // prereqs<course, prereqs>
        Map<Integer, Set<Integer>> pres = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
            pres.put(i, new HashSet<>());
        }

        for (int[] prereq : prerequisites) {
            indegree[prereq[1]]++;
            graph.get(prereq[0]).add(prereq[1]);
        }

        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) d.add(i);
        }

        while (!d.isEmpty()) {
            int curr = d.poll();
            for (int next : graph.get(curr)) {
                indegree[next]--;
                pres.get(next).add(curr);
                pres.get(next).addAll(pres.get(curr));
                if (indegree[next] == 0) d.add(next);
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] q : queries) {
            ans.add(pres.get(q[1]).contains(q[0]));
        }
        return ans;
    }
}
