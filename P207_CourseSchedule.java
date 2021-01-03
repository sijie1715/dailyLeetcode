package Solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P207_CourseSchedule {
    // BFS
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        // record number of prereqs
        int[] prereqs = new int[numCourses];
        for (int[] prereq : prerequisites) {
            prereqs[prereq[0]]++;
        }

        // add ones with 0 prereqs to deque
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (prereqs[i] == 0) d.add(i);
        }

        // take courses from deque; updates prereqs and add new eligible to deque
        int visited = 0;
        while (!d.isEmpty()) {
            int course = d.poll();
            visited++;
            for (int[] prereq : prerequisites) {
                if (prereq[1] == course) {
                    prereqs[prereq[0]]--;
                    if (prereqs[prereq[0]] == 0) d.add(prereq[0]);
                }
            }
        }
        return visited == numCourses;
    }

    // DFS
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] prereq : prerequisites) {
            graph[prereq[0]].add(prereq[1]);
        }
        boolean[] mark = new boolean[numCourses];
        boolean[] globalMark = new boolean[numCourses];
        for (int course = 0; course < numCourses; course++) {
            if (hasCycle(mark, globalMark, graph, course)) return false;
        }
        return true;
    }

    private boolean hasCycle(boolean[] mark, boolean[] globalMark, List<Integer>[] graph, int course) {
        if (mark[course]) return true;
        // globalMark tracks checked course; retrun false if already checked
        if (globalMark[course]) return false;
        mark[course] = globalMark[course] = true;
        for (int prereq : graph[course]) {
            if (hasCycle(mark, globalMark, graph, prereq)) return true;
        }
        mark[course] = false;
        return false;
    }

}
