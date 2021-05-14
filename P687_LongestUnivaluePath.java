package Solutions;

public class P687_LongestUnivaluePath {
    private int path;

    public int longestUnivaluePath(TreeNode root) {
        path = 0;
        getPathLength(root);
        return path;
    }

    private int getPathLength(TreeNode root) {
        if (root == null) return 0;
        int left = getPathLength(root.left);
        int right = getPathLength(root.right);
        int leftpath = root.left != null && root.val == root.left.val ? left + 1 : 0;
        int rightpath = root.right != null && root.val == root.right.val ? right + 1 : 0;
        path = Math.max(path, leftpath + rightpath);
        return Math.max(leftpath, rightpath);
    }
}
