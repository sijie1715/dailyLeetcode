package Solutions;

import java.util.HashSet;
import java.util.Set;

public class P1676_LowestCommonAncestorOfABinaryTreeIV {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode lca;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<TreeNode> set = new HashSet<>();
        for (TreeNode node : nodes) set.add(node);
        dfs(root, set);
        return lca;
    }

    private int dfs(TreeNode root, Set<TreeNode> set) {
        if (root == null) return 0;
        int left = dfs(root.left, set);
        int right = dfs(root.right, set);

        // every node will be visited only once; no need to worry about duplicates
        int count = left + right;
        if (set.contains(root)) count++;

        // need to make sure lca is not assigned yet; or else parent will re-assign
        if (count == set.size() && lca == null) lca = root;
        return count;
    }
}
