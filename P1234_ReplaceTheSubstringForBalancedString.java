package Solutions;

public class P1234_ReplaceTheSubstringForBalancedString {
    public int balancedString(String s) {
        int[] counts = new int[128];
        for (char c : s.toCharArray()) {
            counts[c]++;
        }
        int k = s.length() / 4, i = 0;
        int res = s.length();

        for (int j = 0; j < s.length(); j++) {
            counts[s.charAt(j)]--;
            // at this point exclusion window is [i,j], empty window is i=j+1
            // when i==j, a[i] is excluded, res=1
            while (i < s.length() && counts['Q'] <= k && counts['W'] <= k && counts['E'] <= k && counts['R'] <= k) {
                res = Math.min(res, j - i + 1);
                counts[s.charAt(i++)]++;
            }
        }
        return res;
    }
}
