package Solutions;

public class P1644_LowestCommonAncestorOfABinaryTreeII {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private boolean pfound, qfound;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        pfound = false;
        qfound = false;
        TreeNode LCA = LCA(root, p, q);
        return pfound && qfound ? LCA : null;
    }

    private TreeNode LCA(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return null;
        // need to go through entire tree because node might not exist
        // so recur first
        TreeNode left = LCA(node.left, p, q);
        TreeNode right = LCA(node.right, p, q);
        if (node == p) {
            pfound = true;
            return node;
        }
        if (node == q) {
            qfound = true;
            return node;
        }
        // if (left == null && right == null) return null;
        // if (left != null && right != null) return node;
        // return left == null ? right : left;
        return left == null ? right : right == null ? left : node;
    }
}
