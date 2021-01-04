package Solutions;

import java.util.*;

public class P210_CourseScheduleII {
    // DFS
    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        // table to record prereqs for every course
        List<Integer>[] table = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            table[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            table[pre[0]].add(pre[1]);
        }

        Stack<Integer> revOrder = new Stack<>();
        boolean[] localMark = new boolean[numCourses];
        boolean[] globalMark = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, localMark, globalMark, table, revOrder)) {
                return new int[0];
            }
        }
        int[] ans = new int[numCourses];
        for (int i = numCourses - 1; i >= 0; i--) {
            ans[i] = revOrder.pop();
        }
        return ans;
    }

    private boolean hasCycle(int course, boolean[] localMark, boolean[] globalMark, List<Integer>[] table, Stack<Integer> revOrder) {
        if (localMark[course]) {
            return true;
        }
        if (globalMark[course]) {
            return false;
        }
        localMark[course] = globalMark[course] = true;
        for (int nextCourse : table[course]) {
            if (hasCycle(nextCourse, localMark, globalMark, table, revOrder)) {
                return true;
            }
        }
        localMark[course] = false;
        revOrder.push(course);
        return false;
    }

    // BFS
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        Deque<Integer> canTake = new LinkedList<>();
        int[] res = new int[numCourses];
        // indegree[i] is the number of prereq course i has
        int[] indegree = new int[numCourses];
        // graph[i] are all the courses that has i has prereq
        List<Integer>[] graph = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        for (int[] pre : prerequisites) {
            graph[pre[1]].add(pre[0]);
            indegree[pre[0]]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) canTake.add(i);
        }
        int idx = 0;
        while (canTake.size() > 0) {
            int course = canTake.poll();
            res[idx++] = course;
            for (int helped : graph[course]) {
                indegree[helped]--;
                if (indegree[helped] == 0) canTake.add(helped);
            }
        }
        return idx == numCourses ? res : new int[0];
    }
}
