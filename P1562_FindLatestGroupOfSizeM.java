package Solutions;

public class P1562_FindLatestGroupOfSizeM {
    public int findLatestStep(int[] arr, int m) {
        // length[x] tracks group length at x; count[y] tracks # of y-length groups
        // for x=arr[i],left=length[x-1],rigth=length[x+1]
        // length[x-left]=length[x+right]=left+right+1
        // ***this only updates ends of groups. Inside groups are always filled
        // update res if count[m]>0
        int n = arr.length, res = -1;
        if (n == m) return n;
        int[] length = new int[n + 2]; // 1-indexed; need to check both sides
        int[] count = new int[n + 1]; // length=0~n

        for (int i = 0; i < n; i++) {
            int left = length[arr[i] - 1], right = length[arr[i] + 1];
            length[arr[i] - left] = length[arr[i] + right] = left + right + 1;
            count[left]--;
            count[right]--;
            count[left + right + 1]++;
            if (count[m] > 0) res = i + 1;
        }
        return res;
    }
}
