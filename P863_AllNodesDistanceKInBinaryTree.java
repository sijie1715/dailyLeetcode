package Solutions;

public class P863_AllNodesDistanceKInBinaryTree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        // DFS to build map, then BFS to get all nodes
        Map<Integer, List<Integer>> map = new HashMap<>();
        buildMap(root, null, map);
        Set<Integer> visited = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(target.val);
        visited.add(target.val);
        while (!q.isEmpty()) {
            if (K == 0) {
                res.addAll(q);
                return res;
            }
            for (int sz = q.size(); sz > 0; sz--) {
                int cur = q.poll();
                for (int next : map.get(cur)) {
                    if (visited.contains(next)) continue;
                    q.add(next);
                    visited.add(next);
                }
            }
            K--;
        }
        return res;
    }

    private void buildMap(TreeNode node, TreeNode parent, Map<Integer, List<Integer>> map) {
        if (node == null) return;
        if (!map.containsKey(node.val)) {
            map.put(node.val, new ArrayList<>());
            if (parent != null) {
                map.get(node.val).add(parent.val);
                map.get(parent.val).add(node.val);
            }
            buildMap(node.left, node, map);
            buildMap(node.right, node, map);
        }
    }
}
