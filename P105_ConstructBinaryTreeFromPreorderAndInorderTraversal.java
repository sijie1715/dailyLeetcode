package Solutions;

import java.util.HashMap;
import java.util.Map;

public class P105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return build(0, 0, inorder.length - 1, preorder, inorder, inMap);
    }

    private TreeNode build(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder, Map<Integer, Integer> inMap) {
        if (preStart > preorder.length - 1 || inStart > inEnd) return null;
        int inRoot = inMap.get(preorder[preStart]);
        TreeNode root = new TreeNode(preorder[preStart]);

        root.left = build(preStart + 1, inStart, inRoot - 1, preorder, inorder, inMap);
        root.right = build(preStart + (inRoot - inStart + 1), inRoot + 1, inEnd, preorder, inorder, inMap);

        return root;
    }
}
