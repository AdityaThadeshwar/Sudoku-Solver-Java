package com.aditya;

public class SudokuSolver {

    static final int GRID_SIZE = 9;

    public static void main(String[] args) {

        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        System.out.println("The Board\n");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("\n\nSOLVED\n");
            printBoard(board);
        } else
            System.out.println("\n\nSudoku Unsolvable");

    }

    //Check if the number is present in row
    private static boolean isNumberInRow(int[][] board, int number, int row) {

        for (int i = 0; i < GRID_SIZE; i++) {

            if (board[row][i] == number)
                return true;
        }

        return false;
    }

    //Check if the number is present in column
    private static boolean isNumberInColumn(int[][] board, int number, int column) {

        for (int i = 0; i < GRID_SIZE; i++) {

            if (board[i][column] == number)
                return true;
        }

        return false;
    }

    //Check if the number is present in the local 3x3 grid
    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {

        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {

                if (board[i][j] == number)
                    return true;
            }
        }

        return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    private static boolean solveSudoku(int[][] board) {
        //loop over entire puzzle
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {

                //Test numbers in empty place
                if (board[row][column] == 0) {
                    for (int number = 1; number <= GRID_SIZE; number++) {
                        if (isValidPlacement(board, number, row, column)) {
                            board[row][column] = number;

                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("--------- --------- ---------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(" " + board[row][column] + " ");
            }
            System.out.println();
        }
    }

}
