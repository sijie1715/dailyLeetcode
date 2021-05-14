package Solutions;

public class P333_LargestBSTSubtree {
    public int largestBSTSubtree(TreeNode root) {
        return getLargest(root)[2];
    }

    // [min, max, BST]
    private int[] getLargest(TreeNode node) {
        if (node == null) {
            // all pass
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        int[] left = getLargest(node.left);
        int[] right = getLargest(node.right);

        if (node.val > left[1] && node.val < right[0]) {
            return new int[]{Math.min(left[0], node.val), Math.max(right[1], node.val), left[2] + right[2] + 1};
        } else {
            // all stop
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left[2], right[2])};
        }
    }
}
