package Solutions;

import java.util.ArrayList;
import java.util.List;

public class P113_PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        // recursion
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        getPathSum(res, new ArrayList<>(), root, targetSum);
        return res;
    }

    private void getPathSum(List<List<Integer>> res, List<Integer> tempList, TreeNode root, int sum) {
        tempList.add(root.val);
        if (sum - root.val == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(tempList));
        }

        if (root.left != null) getPathSum(res, tempList, root.left, sum - root.val);
        if (root.right != null) getPathSum(res, tempList, root.right, sum - root.val);

        tempList.remove(tempList.size() - 1);
    }
}
