package Solutions;

public class P930_BinarySubarraysWithSum_DP {
    /*
    public int numSubarraysWithSum(int[] A, int S) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0, sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            if (map.containsKey(sum - S)) ans += map.get(sum - S);
            if (sum == S) ans++;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
     */
    public int numSubarraysWithSum(int[] A, int S) {
        int[] sumMap = new int[A.length + 1];
        int ans = 0, sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            if ((sum - S) >= 0) ans += sumMap[sum - S];
            if (sum == S) ans++;
            sumMap[sum] += 1;
        }
        return ans;
    }
}
