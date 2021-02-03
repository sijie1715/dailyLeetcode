package Solutions;

import java.util.ArrayList;
import java.util.List;

public class P46_Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        permuter(nums, res, new ArrayList<>(), visited);
        return res;
    }

    private void permuter(int[] nums, List<List<Integer>> res, List<Integer> tempList, boolean[] visited) {
        if (tempList.size() == nums.length) {
            res.add(new ArrayList(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            tempList.add(nums[i]);
            visited[i] = true;
            permuter(nums, res, tempList, visited);
            tempList.remove(tempList.size() - 1);
            visited[i] = false;
        }
    }
}
