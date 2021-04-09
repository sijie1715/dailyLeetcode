package Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class P971_FlipBinaryTreeToMatchPreorderTraversal {
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stk = new Stack<>();
        int i = 0;
        stk.push(root);
        while (!stk.isEmpty()) {
            TreeNode node = stk.pop();
            if (node == null) continue;
            if (node.val != voyage[i++]) return Arrays.asList(-1);
            if (node.right != null && node.right.val == voyage[i]) {
                if (node.left != null) res.add(node.val);
                stk.push(node.left);
                stk.push(node.right);
            } else {
                stk.push(node.right);
                stk.push(node.left);
            }
        }
        return res;
    }
}
