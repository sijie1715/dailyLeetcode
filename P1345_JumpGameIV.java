package Solutions;

import java.util.*;

public class P1345_JumpGameIV {
    public int minJumps(int[] arr) {
        // BFS for min steps
        // HashMap for storing indices of same value
        // next steps include same value indices and i+/-1
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], x -> new LinkedList()).add(i);
        }
        boolean[] visited = new boolean[arr.length];
        Queue<Integer> q = new LinkedList<>();
        visited[0] = true;
        q.add(0);

        int step = 0;
        while (!q.isEmpty()) {
            for (int size = q.size(); size > 0; size--) {
                int curr = q.poll();
                if (curr == arr.length - 1) return step;
                visited[curr] = true;
                List<Integer> next = map.get(arr[curr]);
                next.add(curr + 1);
                next.add(curr - 1);
                for (int i : next) {
                    if (i >= 0 && i < arr.length && !visited[i]) {
                        q.add(i);
                    }
                }
                next.clear(); // this will clear the list stored in map
            }
            step++;
        }
        return arr.length - 1;
    }
}
