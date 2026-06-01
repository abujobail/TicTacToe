package model;

public class Board {
    private char[][] grid;
    private static final int SIZE = 3;

    public Board() {
        grid = new char[SIZE][SIZE];
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                grid[i][j] = ' ';
    }

    public boolean placeMark(int row, int col, char mark) {
        if (isEmpty(row, col)) {
            grid[row][col] = mark;
            return true;
        }
        return false;
    }

    public boolean isEmpty(int row, int col) {
        return grid[row][col] == ' ';
    }

    public boolean checkWin(char mark) {
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][0] == mark && grid[i][1] == mark && grid[i][2] == mark) return true;
            if (grid[0][i] == mark && grid[1][i] == mark && grid[2][i] == mark) return true;
        }
        if (grid[0][0] == mark && grid[1][1] == mark && grid[2][2] == mark) return true;
        if (grid[0][2] == mark && grid[1][1] == mark && grid[2][0] == mark) return true;
        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (grid[i][j] == ' ') return false;
        return true;
    }

    public void reset() {
        initBoard();
    }

    public char getCell(int row, int col) {
        return grid[row][col];
    }
}