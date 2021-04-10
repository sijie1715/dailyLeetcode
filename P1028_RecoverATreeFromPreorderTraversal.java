package Solutions;

import java.util.Stack;

public class P1028_RecoverATreeFromPreorderTraversal {
    public TreeNode recoverFromPreorder(String S) {
        int lvl = 0, val = 0;
        Stack<TreeNode> stk = new Stack<>();
        for (int i = 0; i < S.length(); ) {
            for (lvl = 0; S.charAt(i) == '-'; i++) {
                lvl++;
            }
            for (val = 0; i < S.length() && S.charAt(i) != '-'; i++) {
                val = val * 10 + (S.charAt(i) - '0');
            }
            while (stk.size() > lvl) stk.pop();
            TreeNode node = new TreeNode(val);
            if (!stk.isEmpty()) {
                if (stk.peek().left == null) {
                    stk.peek().left = node;
                } else {
                    stk.peek().right = node;
                }
            }
            stk.push(node);
        }
        while (stk.size() > 1) stk.pop();
        return stk.pop();
    }
}
