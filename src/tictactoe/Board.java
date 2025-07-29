package tictactoe;

import java.util.Set;
import java.util.HashSet;

public class Board {
    private static final int BOARD_SIZE = 3;
    private static final char X_PLAYER = 'X';
    private static final char O_PLAYER = 'O';
    private static final char EMPTY_CELL = ' ';
    private final char[][] board;
    private int emptyCellCount;
    private char currentPlayer;
    Set<Integer> emptyCells;

    public enum GameStatus {
        ONGOING,
        X_WIN,
        O_WIN,
        DRAW
    }

    public  Board() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        this.emptyCellCount = BOARD_SIZE * BOARD_SIZE;
        this.emptyCells = new HashSet<>();

        for(int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            int row = i / BOARD_SIZE;
            int column = i % BOARD_SIZE;
            this.board[row][column] = EMPTY_CELL;
            this.emptyCells.add((row * 3) + column);
        }

        this.currentPlayer = X_PLAYER;
    }

    public Board(String initialPosition) {
        if(initialPosition == null || initialPosition.length() != BOARD_SIZE *BOARD_SIZE) {
            throw new IllegalArgumentException("initialPosition must be " + (BOARD_SIZE * BOARD_SIZE) + " long.");
        }
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        this.emptyCellCount = BOARD_SIZE * BOARD_SIZE;
        this.emptyCells = new HashSet<>();

        int xCount = 0;
        int oCount = 0;

        for(int i = 0; i < initialPosition.length(); i++) {
            int row = i / BOARD_SIZE;
            int column = i % BOARD_SIZE;
            char cell = initialPosition.charAt(i);
            if (cell == X_PLAYER) {
                this.board[row][column] = cell;
                this.emptyCellCount--;
                xCount++;
            } else if (cell == O_PLAYER) {
                this.board[row][column] = cell;
                this.emptyCellCount--;
                oCount++;
            } else {
                this.board[row][column] = EMPTY_CELL;
                this.emptyCells.add((row * 3) + column);
            }
        }
        // Determine who plays next based on pieces already on board
        if (xCount > oCount) {
            this.currentPlayer = O_PLAYER;
        } else {
            this.currentPlayer = X_PLAYER;
        }
    }

    public GameStatus getGameState() {
        char winner = findWinner();
        if (winner != EMPTY_CELL) {
            return winner == 'X' ? GameStatus.X_WIN: GameStatus.O_WIN;
        }

        if(emptyCellCount > 0) {
            return GameStatus.ONGOING;
        }

        return GameStatus.DRAW;
    }

    private char findWinner() {
        // Validate rows and columns
        for(int i = 0; i < 3; i++) {
            // Check rows
            if(board[i][0] != EMPTY_CELL && board[i][0] == board[i][1] && board[i][2] == board[i][0]) {
                return board[i][0];
            }
            // Check columns
            if(board[0][i] != ' ' && board[0][i] == board[1][i] && board[2][i] == board[0][i]) {
                return board[0][i];
            }
        }
        // Check diagonals.
        if(board[0][0] != EMPTY_CELL && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if(board[0][2] != EMPTY_CELL && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }
        return EMPTY_CELL;
    }

    public void printBoard() {
        int size = board.length;
        String horizontalLine = "-".repeat(Math.max(0, size * 4 + 1));

        System.out.println(horizontalLine);

        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j]);
                if (j < size - 1) {
                    System.out.print(" | "); // Added column separator
                } else {
                    System.out.print(" |");
                }
            }
            System.out.println();
            if (i < size - 1) {
                StringBuilder innerHorizontal = new StringBuilder("|");
                for (int k = 0; k < size; k++) {
                    innerHorizontal.append("---");
                    if (k < size - 1) {
                        innerHorizontal.append("+");
                    }
                }
                innerHorizontal.append("|");
                System.out.println(innerHorizontal);
            }
        }
        System.out.println(horizontalLine);
    }

    public void printSimpleBoard() {
        System.out.println("---------");
        for(int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("| ");
            for(int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public boolean setCell(int row, int col) {
        int x = row - 1;
        int y = col - 1;

        if (x >= BOARD_SIZE || y >= BOARD_SIZE) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        if (board[x][y] == X_PLAYER || board[x][y] == O_PLAYER) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        board[x][y] = currentPlayer;
        emptyCellCount--;
        switchPlayer();
        emptyCells.remove((x * 3) + y);
        return true;
    }

    public boolean setCell(int cell) {
        int row = cell / BOARD_SIZE;
        int column = cell % BOARD_SIZE;

        if (board[row][column] == X_PLAYER || board[row][column] == O_PLAYER) {
            return false;
        }

        board[row][column] = currentPlayer;
        emptyCellCount--;
        switchPlayer();
        emptyCells.remove(cell);
        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == X_PLAYER) ? O_PLAYER : X_PLAYER;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    public int[] getFreeCells() {
        Integer[] integerArray = emptyCells.toArray(new Integer[0]);
        int[] intArray = new int[integerArray.length];

        for(int i = 0; i < integerArray.length; i++) {
            intArray[i] = integerArray[i];
        }
        return intArray;
    }

    public char[][] getBoardState() {
        return board;
    }
}