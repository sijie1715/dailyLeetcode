package Solutions;

import java.util.ArrayList;
import java.util.List;

public class P216_CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        getSum(res, tempList, 1, k, n);
        return res;
    }

    private void getSum(List<List<Integer>> res, List<Integer> tempList, int s, int k, int n) {
        if (k == 0 && n == 0) {
            res.add(new ArrayList(tempList));
            return;
        }
        if (k == 0 || n == 0) {
            return;
        }
        for (int i = s; i <= 9; i++) {
            // don't go over target
            if (i <= n) {
                tempList.add(i);
                getSum(res, tempList, i + 1, k - 1, n - i);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
