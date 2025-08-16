package tictactoe;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.print("Input command: > ");
            command = scanner.nextLine();
            String[] parameters = command.split(" ");

            if(parameters.length != 3) {
                System.out.println("Bad parameters!");
                continue;
            }

            if (parameters[0].equals("start")) {
                String player1 = parameters[1];
                String player2 = parameters[2];

                if (!player1.equals("easy") && !player1.equals("user") && !player1.equals("medium")
                        && !player1.equals("hard")) {
                    System.out.println("Bad parameters!");
                    continue;
                }

                if (!player2.equals("easy") && !player2.equals("user") && !player2.equals("medium")
                        && !player2.equals("hard")) {
                    System.out.println("Bad parameters!");
                    continue;
                }

                Board board = new Board();
                
                Player p1;
                Player p2;
                
                if(player1.equals("easy")) {
                    p1 = new IAPlayerEasy('X');
                } else if(player1.equals("medium")) {
                    p1 = new IAPlayerMedium('X');
                } else if(player1.equals("hard")) {
                    p1 = new IAPlayerHard('X');
                } else {
                    p1 = new HumanPlayer('X');
                }

                if(player2.equals("easy")) {
                    p2 = new IAPlayerEasy('O');
                } else if(player2.equals("medium")) {
                    p2 = new IAPlayerMedium('O');
                } else if(player2.equals("hard")) {
                    p2 = new IAPlayerHard('O');
                } else {
                    p2 = new HumanPlayer('O');
                }

                board.printSimpleBoard();
                while(board.getGameState() == Board.GameStatus.ONGOING) {
                    int nextMove;
                    do {
                        if(board.getCurrentPlayer() == 'X') {
                            nextMove = p1.getNextMove(board);
                        } else {
                            nextMove = p2.getNextMove(board);
                        }
                    }while(!board.setCell(nextMove));
                    board.printSimpleBoard();
                }
                switch(board.getGameState()) {
                    case O_WIN -> System.out.println("O wins");
                    case X_WIN -> System.out.println("X wins");
                    case ONGOING -> System.out.println("Game not finished");
                    case DRAW -> System.out.println("Draw");
                }
            }
        } while (!Objects.equals(command, "exit"));

    }
}