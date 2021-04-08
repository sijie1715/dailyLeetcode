package Solutions;

import java.util.HashMap;
import java.util.Map;

public class P106_ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
        return build(0, postorder.length - 1, 0, inorder.length - 1, postorder, inorder, inMap);
    }

    private TreeNode build(int ps, int pe, int is, int ie, int[] postorder, int[] inorder, Map<Integer, Integer> inMap) {
        if (ps > pe || is > ie) return null;
        TreeNode root = new TreeNode(postorder[pe]);
        int inroot = inMap.get(root.val);
        // left branch size = ir - is
        TreeNode left = build(ps, ps + inroot - is - 1, is, inroot - 1, postorder, inorder, inMap);
        TreeNode right = build(ps + inroot - is, pe - 1, inroot + 1, ie, postorder, inorder, inMap);
        root.left = left;
        root.right = right;
        return root;
    }
}
