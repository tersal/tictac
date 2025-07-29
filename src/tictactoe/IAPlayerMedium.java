package tictactoe;

public class IAPlayerMedium extends IAPlayerEasy {

    protected char[][] currentBoardState;

    public IAPlayerMedium(char shape) {
        super(shape);
    }

    @Override
    public int getNextMove(Board board) {
        int[] validMoves = board.getFreeCells();
        System.out.println("Making move level \"medium\"");

        if (validMoves.length == 0) {
            return -1; // No valid moves
        }

        char[][] boardState = board.getBoardState();
        char myShape = getPlayerShape();
        char opponentShape = (myShape == 'X') ? 'O' : 'X';

        int winningMove = findWinningMove(boardState, validMoves, myShape);
        if (winningMove != -1) {
            return winningMove;
        }

        int blockingMove = findWinningMove(boardState, validMoves, opponentShape);
        if (blockingMove != -1) {
            return blockingMove;
        }

        return validMoves[ranGenerator.nextInt(validMoves.length)];
    }

    private int findWinningMove(char[][] boardState, int[] validMoves, char playerShape) {
        int boardSize = boardState.length;

        for (int moveIndex : validMoves) {
            int row = moveIndex / boardSize;
            int col = moveIndex % boardSize;

            char[][] tempBoard = copyBoardState(boardState);
            tempBoard[row][col] = playerShape;

            if (isWinningBoard(tempBoard, playerShape)) {
                return moveIndex;
            }
        }

        return -1;
    }

    private char[][] copyBoardState(char[][] boardState) {
        int size = boardState.length;
        char[][] copy = new char[size][size];

        for (int i = 0; i < size; i++) {
            System.arraycopy(boardState[i], 0, copy[i], 0, size);
        }

        return copy;
    }

    private boolean isWinningBoard(char[][] boardState, char playerShape) {
        int size = boardState.length; // Assuming it's a square board

        // Check rows
        for (char[] chars : boardState) {
            boolean rowWin = true;
            for (int col = 0; col < size; col++) {
                if (chars[col] != playerShape) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) return true;
        }

        // Check columns
        for (int col = 0; col < size; col++) {
            boolean colWin = true;
            for (char[] chars : boardState) {
                if (chars[col] != playerShape) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) return true;
        }

        // Check main diagonal (top-left to bottom-right)
        boolean diagWin = true;
        for (int i = 0; i < size; i++) {
            if (boardState[i][i] != playerShape) {
                diagWin = false;
                break;
            }
        }
        if (diagWin) return true;

        // Check other diagonal (top-right to bottom-left)
        diagWin = true;
        for (int i = 0; i < size; i++) {
            if (boardState[i][size - 1 - i] != playerShape) {
                diagWin = false;
                break;
            }
        }
        return diagWin;
    }
}

