package Solutions;

public class P1740_FindDistanceInABinaryTree {

    public int findDistance(TreeNode root, int p, int q) {
        TreeNode lca = findLCA(root, p, q);
        return getDepth(lca, p, 0) + getDepth(lca, q, 0);
    }

    private int getDepth(TreeNode root, int p, int d) {
        if (root == null) {
            return -1;
        }
        if (root.val == p) {
            return d;
        }
        int left = getDepth(root.left, p, d + 1);
        if (left < 0) {
            return getDepth(root.right, p, d + 1);
        }
        return left;
    }

    private TreeNode findLCA(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        }
        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);

        // if find either, return root
        if (root.val == p || root.val == q || (left != null && right != null)) {
            return root;
        }

        return left == null ? right : left;
    }
}
