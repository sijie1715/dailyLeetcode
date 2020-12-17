package Solutions;

public class P1004_MaxConsecutiveOnesIII {
    public int longestOnes(int[] A, int K) {
        int iLo = 0, iHi = 0;
        int res = 0, zeros = 0;

        for (; iHi < A.length; iHi++) {
            if (A[iHi] == 0) zeros++;
            while (zeros > K) {
                if (A[iLo++] == 0) zeros--;
            }
            res = Math.max(res, iHi - iLo + 1);
        }
        return res;
    }

//    public int longestOnes(int[] A, int K) {
//        int iLo = 0, iHi = 0;
//        for (; iHi < A.length; iHi++) {
//            if (A[iHi] == 0) K--;
//            // only increment iLo when K<0
//            if (K < 0 && A[iLo++] == 0) {
//                K++;
//            }
//        }
//        return iHi - iLo;
//    }
}
