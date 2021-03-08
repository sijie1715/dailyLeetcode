package Solutions;

public class P99_RecoverBinarySearchTree {
    private TreeNode first;
    private TreeNode second;
    private TreeNode last;

    public void recoverTree(TreeNode root) {
        // inorder traverse the tree -> sorted array
        // find 2 misplaced elements x1,x2: s[1,3,2,4]
        // 1st: s[i]>s[i+1] -> x1=s[i]
        // 2nd: s[j]>s[j+1] -> x2=s[j+1]
        last = new TreeNode(Integer.MIN_VALUE);
        inTraverse(root);
        swap();
    }

    private void inTraverse(TreeNode root) {
        if (root == null) return;

        inTraverse(root.left);

        // update first, second
        if (first == null && root.val < last.val) {
            first = last;
        }
        if (first != null && root.val < last.val) {
            second = root;
        }
        last = root;

        inTraverse(root.right);
    }

    private void swap() {
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
