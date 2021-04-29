package Solutions;

import java.util.ArrayList;
import java.util.List;

public class P315_CountOfSmallerNumbersAfterSelf {
    // use mergesort, and the number of elements merged from right side (rightcount) is the number of smaller elements
    private int[] count;

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        count = new int[nums.length];
        int[] indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }
        mergesort(nums, indexes, 0, nums.length - 1);
        // int[] -> List<Integer> either use stream or iterate
        for (int i = 0; i < nums.length; i++) {
            res.add(count[i]);
        }
        return res;
    }

    private void mergesort(int[] nums, int[] indexes, int s, int e) {
        if (s >= e) return;
        int mid = s + (e - s) / 2;
        mergesort(nums, indexes, s, mid);
        mergesort(nums, indexes, mid + 1, e);
        merge(nums, indexes, s, e);
    }

    private void merge(int[] nums, int[] indexes, int s, int e) {
        int mid = s + (e - s) / 2;
        int l = s, r = mid + 1;
        int rightcount = 0;
        int[] newIndexes = new int[e - s + 1];

        int sortIdx = 0;
        while (l <= mid && r <= e) {
            // merge right side
            if (nums[indexes[r]] < nums[indexes[l]]) {
                newIndexes[sortIdx] = indexes[r];
                r++;
                rightcount++;
            } else {
                newIndexes[sortIdx] = indexes[l];
                count[indexes[l]] += rightcount;
                l++;
            }
            sortIdx++;
        }
        // if left side not finished, add rightcount to all indexes count
        while (l <= mid) {
            newIndexes[sortIdx] = indexes[l];
            count[indexes[l]] += rightcount;
            l++;
            sortIdx++;
        }
        while (r <= e) {
            newIndexes[sortIdx++] = indexes[r++];
        }
        // update indexes
        for (int i = s; i <= e; i++) {
            indexes[i] = newIndexes[i - s];
        }
    }
}
