package Solutions;

import java.util.HashMap;
import java.util.Map;

public class P437_PathSumIII {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        Map<Integer, Integer> map = new HashMap<>();    // <sum, freq>
        map.put(0, 1);
        return pathSum(root, 0, targetSum, map);
    }

    private int pathSum(TreeNode root, int curSum, int sum, Map<Integer, Integer> map) {
        if (root == null) return 0;
        curSum += root.val;
        int curPathSum = map.getOrDefault(curSum - sum, 0);
        //update map and pass down
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        int res = curPathSum + pathSum(root.left, curSum, sum, map) + pathSum(root.right, curSum, sum, map);
        // backtracking, need to rewind map
        map.put(curSum, map.get(curSum) - 1);
        return res;
    }
}
