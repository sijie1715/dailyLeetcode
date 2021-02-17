package Solutions;

public class P526_BeautifulArrangement {
    int count;

    public int countArrangement(int n) {
        count = 0;
        boolean[] visited = new boolean[n + 1];
        bt(1, n, visited);
        return count;
    }

    private void bt(int pos, int n, boolean[] visited) {
        if (pos > n) {
            count++;
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i] && (pos % i == 0 || i % pos == 0)) {
                visited[i] = true;
                bt(pos + 1, n, visited);
                visited[i] = false;
            }
        }
    }
}
