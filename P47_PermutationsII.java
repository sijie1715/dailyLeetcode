package Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P47_PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        permute(nums, res, tempList, visited);
        return res;
    }

    private void permute(int[] nums, List<List<Integer>> res, List<Integer> tempList, boolean[] visited) {
        if (tempList.size() == nums.length) res.add(new ArrayList(tempList));

        for (int i = 0; i < nums.length; i++) {
            // skip duplicates
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
            if (visited[i]) continue;

            tempList.add(nums[i]);
            visited[i] = true;
            permute(nums, res, tempList, visited);
            tempList.remove(tempList.size() - 1);
            visited[i] = false;
        }
    }
}
