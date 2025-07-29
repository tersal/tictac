package tictactoe;

public class Player {
    protected final char playerShape;

    public Player(char shape) {
        this.playerShape = shape;
    }

    public int getNextMove(Board board) {
        return 0;
    }

    public char getPlayerShape() {
        return playerShape;
    }
}
