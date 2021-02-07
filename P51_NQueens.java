package Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P51_NQueens {
    int n;
    boolean[] checkCol;
    boolean[] check45;
    boolean[] check135;
    char[][] board;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        checkCol = new boolean[n];
        check45 = new boolean[2 * n - 1];
        check135 = new boolean[2 * n - 1];
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        List<List<String>> res = new ArrayList<>();
        setQueens(res, 0);
        return res;

    }

    private void setQueens(List<List<String>> res, int row) {
        if (row == n) {
            List<String> solution = new ArrayList<>();
            for (char[] solutionRow : board) {
                solution.add(new String(solutionRow));
            }
            res.add(solution);
            return;
        }

        for (int col = 0; col < n; col++) {
            int idx45 = row + col;
            int idx135 = (n - 1) - (row - col);
            if (checkCol[col] || check45[idx45] || check135[idx135]) continue;
            checkCol[col] = check45[idx45] = check135[idx135] = true;
            board[row][col] = 'Q';
            setQueens(res, row + 1);
            checkCol[col] = check45[idx45] = check135[idx135] = false;
            board[row][col] = '.';
        }
    }
}
