package tictactoe;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> deleteList = new ArrayList<>();
        deleteList.add(2);
        deleteList.add(1);
        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(1);
        arrayList.removeAll(deleteList);

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
                boolean badParameters = true;
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

                //Player p1 = new Player('X', player1.equals("easy") ? Player.PlayerType.AI : Player.PlayerType.HUMAN);
                //Player p2 = new Player('O', player2.equals("easy") ? Player.PlayerType.AI : Player.PlayerType.HUMAN);

                board.printSimpleBoard();
                while(board.getGameState() == Board.GameStatus.ONGOING) {
                    int nextMove = 0;
                    do {
                        boolean invalidMove = true;
                        if(board.getCurrentPlayer() == 'X') {
                            nextMove = p1.getNextMove(board);
                        } else {
                            nextMove = p2.getNextMove(board);
                        }
                    }while(!board.setCell(nextMove));
                    board.printSimpleBoard();
                }
                switch(board.getGameState()) {
                    case Board.GameStatus.O_WIN -> System.out.println("O wins");
                    case Board.GameStatus.X_WIN -> System.out.println("X wins");
                    case Board.GameStatus.ONGOING -> System.out.println("Game not finished");
                    case Board.GameStatus.DRAW -> System.out.println("Draw");
                }
            }
        } while (!Objects.equals(command, "exit"));

    }


/*
    private void OneEasyGame() {
        Board board = new Board();
        Player player = new Player('O', Player.PlayerType.AI);
        Scanner scanner = new Scanner(System.in);

        board.printSimpleBoard();
        while(board.getGameState() == Board.GameStatus.ONGOING) {
            boolean validInput = false;
            if(board.getCurrentPlayer() == 'X') {
                do {
                    int x;
                    int y;
                    System.out.print("Enter the coordinates: > ");
                    try {
                        x = scanner.nextInt();
                        y = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("You should enter numbers!");
                        scanner.nextLine();
                        continue;
                    }

                    if (board.setCell(x, y)) {
                        validInput = true;
                    }
                } while (!validInput);
            } else {
                System.out.println("Making move level \"easy\"");
                do {
                    validInput = board.setCell(player.getNextMove(board));
                } while (!validInput);
            }
            board.printSimpleBoard();
        }
        switch(board.getGameState()) {
            case Board.GameStatus.O_WIN -> System.out.println("O wins");
            case Board.GameStatus.X_WIN -> System.out.println("X wins");
            case Board.GameStatus.ONGOING -> System.out.println("Game not finished");
            case Board.GameStatus.DRAW -> System.out.println("Draw");
        }

    }

    private void SimpleGame() {
        // Get the current status of the board
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the cells: > ");
        String initialStatus = scanner.nextLine();
        Board board = new Board(initialStatus);
        board.printSimpleBoard();

        boolean validInput = false;
        Board.GameStatus winner = Board.GameStatus.ONGOING;

        do {
            int x;
            int y;
            System.out.print("Enter coordinates: > ");
            try {
                x = scanner.nextInt();
                y = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
                continue;
            }

            if(board.setCell(x, y)) {
                validInput = true;
                board.printSimpleBoard();
            }

            winner = board.getGameState();

        } while(!validInput);

        switch(winner) {
            case Board.GameStatus.O_WIN -> System.out.println("O wins");
            case Board.GameStatus.X_WIN -> System.out.println("X wins");
            case Board.GameStatus.ONGOING -> System.out.println("Game not finished");
            case Board.GameStatus.DRAW -> System.out.println("Draw");
        }
    }
 */
}