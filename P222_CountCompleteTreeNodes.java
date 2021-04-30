package Solutions;

public class P222_CountCompleteTreeNodes {
    // 1 node = height 0
    private int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }

    public int countNodes(TreeNode root) {
        int count = 0, h = height(root);
        while (root != null) {
            if (height(root.right) == h - 1) {
                // end of last row is on the right side; add all of left side node count
                count += 1 << h;
                root = root.right;
            } else {
                // add all of right side but with height - 1
                count += 1 << (h - 1);
                root = root.left;
            }
            h--;
        }
        return count;
    }
}
