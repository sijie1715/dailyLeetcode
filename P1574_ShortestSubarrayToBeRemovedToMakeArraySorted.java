package Solutions;

public class P1574_ShortestSubarrayToBeRemovedToMakeArraySorted {
    public int findLengthOfShortestSubarray(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left < arr.length - 1 && arr[left] <= arr[left + 1]) left++;
        if (left == arr.length - 1) return 0;

        while (right > 0 && arr[right] >= arr[right - 1]) right--;

        int i = 0, j = right;
        int res = Math.min(arr.length - left - 1, right);
        while (i <= left && j <= arr.length - 1) {
            if (arr[i] <= arr[j]) {
                res = Math.min(res, j - i - 1);
                i++;
            } else {
                j++;
            }
        }
        return res;
    }
}
