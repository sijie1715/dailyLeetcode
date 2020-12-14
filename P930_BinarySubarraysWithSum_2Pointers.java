package Solutions;

public class P930_BinarySubarraysWithSum_2Pointers {
    public int numSubarraysWithSum(int[] A, int S) {
        int ans = 0;
        int iLo = 0, iHi = 0;
        int sumLo = 0, sumHi = 0;

        // right pointer j
        // every time we move j, we move iLo to 1st 0 and iHi to 1st 1
        // if sumLo reaches S, ans+=(iHi-iLo)+1
        for (int j = 0; j < A.length; j++) {
            sumLo += A[j];
            while (iLo < j && sumLo > S) {
                sumLo -= A[iLo++];
            }

            sumHi += A[j];
            while (iHi < j && (sumHi > S || sumHi == S && A[iHi] == 0)) {
                sumHi -= A[iHi++];
            }

            if (sumLo == S) ans += iHi - iLo + 1;
        }
        return ans;
    }
}
