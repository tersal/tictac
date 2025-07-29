package tictactoe;

public class IAPlayerHard extends Player {
    private final char opponentShape;

    public IAPlayerHard(char shape) {
        super(shape);
        this.opponentShape = (shape == 'X') ? 'O' : 'X';
    }

    @Override
    public int getNextMove(Board board) {
        System.out.println("Making move level \"hard\"");

        int[] validMoves = board.getFreeCells();
        if (validMoves.length == 0) {
            return -1; // No valid moves
        }

        int bestMove = validMoves[0];
        int bestScore = Integer.MIN_VALUE;

        // Evaluate each possible move
        for (int move : validMoves) {
            // Create a copy of the board to test the move
            Board testBoard = createBoardCopy(board);

            // Make the move
            testBoard.setCell(move);

            // Calculate the score for this move using MinMax
            int score = minmax(testBoard, false, Integer.MIN_VALUE, Integer.MAX_VALUE);

            // Keep track of the best move
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    /**
     * MinMax algorithm with Alpha-Beta pruning
     * @param board Current board state
     * @param isMaximizing True if it's the AI's turn (maximizing player)
     * @param alpha Alpha value for pruning
     * @param beta Beta value for pruning
     * @return The best score for the current player
     */
    private int minmax(Board board, boolean isMaximizing, int alpha, int beta) {
        Board.GameStatus gameState = board.getGameState();

        // Terminal states
        if (gameState == Board.GameStatus.X_WIN) {
            return (playerShape == 'X') ? 10 : -10;
        } else if (gameState == Board.GameStatus.O_WIN) {
            return (playerShape == 'O') ? 10 : -10;
        } else if (gameState == Board.GameStatus.DRAW) {
            return 0;
        }

        if (isMaximizing) {
            // AI's turn - maximize score
            int maxScore = Integer.MIN_VALUE;
            int[] validMoves = board.getFreeCells();

            for (int move : validMoves) {
                Board testBoard = createBoardCopy(board);
                testBoard.setCell(move);

                int score = minmax(testBoard, false, alpha, beta);
                maxScore = Math.max(maxScore, score);
                alpha = Math.max(alpha, score);

                // Alpha-Beta pruning
                if (beta <= alpha) {
                    break;
                }
            }
            return maxScore;
        } else {
            // Opponent's turn - minimize score
            int minScore = Integer.MAX_VALUE;
            int[] validMoves = board.getFreeCells();

            for (int move : validMoves) {
                Board testBoard = createBoardCopy(board);
                testBoard.setCell(move);

                int score = minmax(testBoard, true, alpha, beta);
                minScore = Math.min(minScore, score);
                beta = Math.min(beta, score);

                // Alpha-Beta pruning
                if (beta <= alpha) {
                    break;
                }
            }
            return minScore;
        }
    }

    /**
     * Creates a deep copy of the board for testing moves
     * @param original The original board to copy
     * @return A new Board instance with the same state
     */
    private Board createBoardCopy(Board original) {
        char[][] boardState = original.getBoardState();
        StringBuilder position = new StringBuilder();

        // Convert 2D array back to string format for Board constructor
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                position.append(boardState[i][j]);
            }
        }

        return new Board(position.toString());
    }
}