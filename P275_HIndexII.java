package Solutions;

public class P275_HIndexII {
    public int hIndex(int[] citations) {
        int l = 0, r = citations.length - 1, mid, n = citations.length;
        // answer not guaranteed, need to check last element
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (citations[mid] >= n - mid) r = mid - 1;
            else l = mid + 1;
        }
        return citations.length - l;
    }
}
