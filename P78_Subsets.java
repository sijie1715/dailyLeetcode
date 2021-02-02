package Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P78_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        // backtracking
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        bt(nums, res, tempList, 0);
        return res;
    }

    private void bt(int[] nums, List<List<Integer>> res, List<Integer> tempList, int s) {
        res.add(new ArrayList(tempList));
        for (int i = s; i < nums.length; i++) {
            tempList.add(nums[i]);
            bt(nums, res, tempList, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
