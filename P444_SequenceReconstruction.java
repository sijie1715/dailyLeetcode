package Solutions;

import java.util.*;

public class P444_SequenceReconstruction {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        // every consecutive integers in org need to appear in a seq
        // if consecutive (x,y) doesn't exist then no constrain on x,y order; will have >1 topsort
        // treat every pair in seqs as prereq then topsort

        // graph to record <X, all Ys>
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // indegree to record <Y, indegree>
        Map<Integer, Integer> indegree = new HashMap<>();

        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                graph.putIfAbsent(seq.get(i), new ArrayList<>());
                indegree.putIfAbsent(seq.get(i), 0);
                if (i > 0) {
                    graph.get(seq.get(i - 1)).add(seq.get(i));
                    indegree.put(seq.get(i), indegree.get(seq.get(i)) + 1);
                }
            }
        }
        // if indegree.size()>org.length then has extra elements
        // if indegree.size()<org.length then not enough elements
        if (indegree.size() != org.length) return false;

        Deque<Integer> d = new ArrayDeque<>();
        //can't screen deque with index because seqs might not cover full range
        for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                d.add(entry.getKey());
            }
        }

        int idx = 0;
        while (!d.isEmpty()) {
            // if has more than 1 in deque, then means more than 1 topsort solution
            if (d.size() > 1) return false;
            int curr = d.poll();
            // check if right sequence
            if (org[idx] != curr) return false;
            idx++;
            for (int next : graph.get(curr)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) d.add(next);
            }
        }
        return idx == org.length;
    }
}
