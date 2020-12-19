package Solutions;

public class P1248_CountNumberOfNiceSubarrays {
//     public int numberOfSubarrays(int[] nums, int k) {
//         // [1,1,2,1,1]
//         // [2,2,2,1,2,2,1,2,2,2]
//         int i = 0, res = 0;
//         int evenCount = 0;
//         for (int j = 0; j < nums.length; j++) {
//             if (nums[j] % 2 == 1) {
//                 k--;
//                 evenCount = 0;

//             }
//             while (k == 0) {
//                 if (nums[i++] % 2 == 1) k++;
//                 evenCount++;
//             }
//             res += evenCount;
//         }
//         return res;
//     }

    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        int i = 0, res = 0;
        for (int j = 0; j < nums.length; j++) {
            k -= nums[j] & 1;
            while (k < 0) k += nums[i++] & 1;
            res += j - i + 1;
        }
        return res;
    }
}
