package Solutions;

import java.util.ArrayList;
import java.util.List;

public class P77_Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        doCombine(res, tempList, 0, n, k);
        return res;
    }

    private void doCombine(List<List<Integer>> res, List<Integer> tempList, int start, int n, int k) {
        if (tempList.size() == k) {
            res.add(new ArrayList(tempList));
            return;
        }
        for (int i = start; i < n; i++) {
            tempList.add(i + 1);
            doCombine(res, tempList, i + 1, n, k);
            tempList.remove(tempList.size() - 1);
        }
    }
}
