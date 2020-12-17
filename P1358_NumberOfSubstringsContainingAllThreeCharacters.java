package Solutions;

public class P1358_NumberOfSubstringsContainingAllThreeCharacters {
    public int numberOfSubstrings(String s) {
        int iLo = 0, res = 0;
        int[] count = new int[]{0, 0, 0};
        for (int iHi = 0; iHi < s.length(); iHi++) {
            count[s.charAt(iHi) - 'a']++;
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                count[s.charAt(iLo) - 'a']--;
                iLo++;
            }
            res += iLo;
            // move iLo until (contains abc) not satisfied
            // [iLo-1~iHi] contains abc
            // subarrays that extends back also satisfy (contains abc)
            // [0:iLo-1]+[iLo~iHi] all satisfy; count=[0:iLo-1]=iLo
        }
        return res;
    }
}
