package tictactoe;

import java.util.Random;

public class IAPlayerEasy extends Player {
    protected final Random ranGenerator;

    public IAPlayerEasy(char shape) {
        super(shape);
        this.ranGenerator = new Random();
    }

    @Override
    public int getNextMove(Board board) {
        System.out.println("Making move level \"easy\"");
        int[] validMoves = board.getFreeCells();;
        return validMoves[ranGenerator.nextInt(validMoves.length)];
    }
}
