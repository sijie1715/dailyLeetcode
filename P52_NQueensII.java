package Solutions;

public class P52_NQueensII {
    int n, res;
    boolean[] checkCol;
    boolean[] check45;
    boolean[] check135;

    public int totalNQueens(int n) {
        this.n = n;
        checkCol = new boolean[n];
        check45 = new boolean[2 * n - 1];
        check135 = new boolean[2 * n - 1];

        res = 0;
        setQueens(0);
        return res;

    }

    private void setQueens(int row) {
        if (row == n) {
            res++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int idx45 = row + col;
            int idx135 = (n - 1) - (row - col);
            if (checkCol[col] || check45[idx45] || check135[idx135]) continue;
            checkCol[col] = check45[idx45] = check135[idx135] = true;
            setQueens(row + 1);
            checkCol[col] = check45[idx45] = check135[idx135] = false;
        }
    }
}
