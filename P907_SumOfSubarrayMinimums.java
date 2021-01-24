package Solutions;

import java.util.Stack;

public class P907_SumOfSubarrayMinimums {
    public int sumSubarrayMins(int[] arr) {
        // f[i] counts the num of subarrays that has arr[i] as minimum
        // res[i] = arr[i]*f[i]
        // left[i] is number of consecutive numbers to the left bigger than arr[i]; right[i] right
        // number of subarrays with min arr[i] will be (left[i]+1)*(right[i]+1)
        int n = arr.length;
        int[] left = new int[n], right = new int[n];
        Stack<int[]> stkL = new Stack<>();
        Stack<int[]> stkR = new Stack<>();
        for (int i = 0; i < n; i++) {
            int count = 1;
            while (!stkL.isEmpty() && stkL.peek()[0] >= arr[i]) {
                count += stkL.pop()[1];
            }
            stkL.push(new int[]{arr[i], count});
            left[i] = count;
        }
        for (int j = n - 1; j >= 0; j--) {
            int count = 1;
            while (!stkR.isEmpty() && stkR.peek()[0] > arr[j]) {
                count += stkR.pop()[1];
            }
            stkR.push(new int[]{arr[j], count});
            right[j] = count;
        }
        long mod = (int) 1e9 + 7, res = 0;
        for (int k = 0; k < n; k++) {
            res = (res + (long) arr[k] * left[k] * right[k]) % mod;
        }
        return (int) res;
    }
}
