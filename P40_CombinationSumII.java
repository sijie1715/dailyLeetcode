package Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P40_CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        boolean[] visited = new boolean[candidates.length];
        Arrays.sort(candidates);
        getCombinationSum(res, tempList, visited, target, 0, candidates);
        return res;
    }

    private void getCombinationSum(List<List<Integer>> res, List<Integer> tempList, boolean[] visited, int target, int s, int[] candidates) {
        if (target == 0) {
            res.add(new ArrayList(tempList));
            return;
        }
        for (int i = s; i < candidates.length; i++) {
            // skip duplicates
            if (i > 0 && candidates[i] == candidates[i - 1] && !visited[i - 1]) {
                continue;
            }
            // don't go over target
            if (candidates[i] <= target) {
                tempList.add(candidates[i]);
                visited[i] = true;
                getCombinationSum(res, tempList, visited, target - candidates[i], i + 1, candidates);
                tempList.remove(tempList.size() - 1);
                visited[i] = false;
            }
        }
    }
}
