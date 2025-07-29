package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner scanner;

    public HumanPlayer(char shape) {
        super(shape);
        scanner = new Scanner(System.in);
    }

    @Override
    public int getNextMove(Board board) {
        boolean validInput = false;
        int x = 0;
        int y = 0;
        int cell = 0;
        do {
            System.out.print("Enter the coordinates: > ");
            try {
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        } while(!validInput);

        cell = (x * 3) + y;

        return cell;
    }
}
