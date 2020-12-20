package Solutions;

public class P1658_MinimumOperationsToReduceXToZero {
//     public int minOperations(int[] nums, int x) {
//         // finding the longest subarray with sum=numsSum-x
//         int numsSum = 0;
//         for (int num : nums) numsSum += num;
//         if (numsSum < x) return -1;
//         if (numsSum == x) return nums.length;

//         // HashMap to store prefix sum and index
//         int res = Integer.MIN_VALUE, sum = 0, target = numsSum - x;
//         Map<Integer, Integer> map = new HashMap<>();
//         // need to put sum=0(<0,-1>) in map
//         map.put(0, -1);
//         for (int i = 0; i < nums.length; i++) {
//             sum += nums[i];
//             if (map.containsKey(sum - target)) {
//                 res = Math.max(res, i - map.get(sum - target));
//             }
//             map.put(sum, i);
//         }
//         return (res == Integer.MIN_VALUE) ? -1 : nums.length - res;
    // }

    public int minOperations(int[] nums, int x) {
        int numsSum = 0;
        for (int num : nums) numsSum += num;
        if (numsSum < x) return -1;
        if (numsSum == x) return nums.length;

        int i = 0, res = Integer.MIN_VALUE, target = numsSum - x;
        for (int j = 0; j < nums.length; j++) {
            target -= nums[j];
            if (target == 0) res = Math.max(res, j - i + 1);
            while (target < 0) {
                target += nums[i++];
                if (target == 0) res = Math.max(res, j - i + 1);
            }
        }
        return (res == Integer.MIN_VALUE) ? -1 : nums.length - res;
    }
}
