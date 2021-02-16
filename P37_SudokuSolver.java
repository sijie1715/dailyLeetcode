package Solutions;

public class P37_SudokuSolver {
    private boolean[][] rowCheck = new boolean[9][10];
    private boolean[][] colCheck = new boolean[9][10];
    private boolean[][] cubeCheck = new boolean[9][10];
    private char[][] board;

    public void solveSudoku(char[][] board) {
        this.board = board;
        // initialize checkers
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';
                rowCheck[i][num] = true;
                colCheck[j][num] = true;
                cubeCheck[cubeNum(i, j)][num] = true;
            }
        }
        backtracking(0, 0);
    }

    private boolean backtracking(int row, int col) {
        // check if Sudoku is finished
        while (row < 9 && board[row][col] != '.') {
            // if gets to end of row++, col->0
            // update row first before reset col
            row = col == 8 ? row + 1 : row;
            col = col == 8 ? 0 : col + 1;
        }
        if (row == 9) {
            return true;
        }
        // try every number at this location; if exists in row/col/cube, skip
        for (int i = 1; i <= 9; i++) {
            if (rowCheck[row][i] || colCheck[col][i] || cubeCheck[cubeNum(row, col)][i]) {
                continue;
            }
            // update -> backtrack -> restore
            rowCheck[row][i] = true;
            colCheck[col][i] = true;
            cubeCheck[cubeNum(row, col)][i] = true;
            board[row][col] = (char) (i + '0');

            if (backtracking(row, col)) {
                return true;
            }

            rowCheck[row][i] = false;
            colCheck[col][i] = false;
            cubeCheck[cubeNum(row, col)][i] = false;
            board[row][col] = '.';
        }
        return false;
    }

    private int cubeNum(int i, int j) {
        int row = i / 3;
        int col = j / 3;
        return row * 3 + col;
    }
}
