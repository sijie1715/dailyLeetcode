package Solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class P785_IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        // mark nodes with color -1,1,default0
        int[] colors = new int[graph.length];

        // dfs every node and mark colors alternatively
        // skip nodes that are already marked
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length > 0 && colors[i] == 0) {
                Deque<Integer> d = new ArrayDeque<>();
                d.add(i);
                colors[i] = 1;
                while (!d.isEmpty()) {
                    int cur = d.poll();
                    for (int next : graph[cur]) {
                        if (colors[next] == 0) {
                            colors[next] = -colors[cur];
                            d.add(next);
                        } else {
                            if (colors[next] == colors[cur]) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
