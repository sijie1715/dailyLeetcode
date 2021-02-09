package Solutions;

import java.util.ArrayList;
import java.util.List;

public class P39_CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        getCombinationSum(res, tempList, target, 0, candidates);
        return res;
    }

    private void getCombinationSum(List<List<Integer>> res, List<Integer> tempList, int target, int s, int[] candidates) {
        if (target == 0) {
            res.add(new ArrayList(tempList));
            return;
        }
        for (int i = s; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                tempList.add(candidates[i]);
                getCombinationSum(res, tempList, target - candidates[i], i, candidates);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
