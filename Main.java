import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        CPUPlayer ai = new CPUPlayer(Mark.X);
        Mark opponent = Mark.O;
        Mark cpuPlayer = Mark.X;

        while (true) {
            System.out.println(board);

            if (board.isGameOver()) {
                if (board.evaluate(cpuPlayer) == 100) {
                    System.out.println(cpuPlayer + " (AI) wins!");
                } else if (board.evaluate(opponent) == -100) {
                    System.out.println(opponent + " (Player) wins!");
                } else {
                    System.out.println("It's a draw!");
                }
                break;
            }

            System.out.print("Enter move (1-9) or 'quit' to exit: ");
            String input = scanner.nextLine();

            if (input.equals("quit")) {
                System.out.println("Game exited.");
                break;
            }

            Move playerMove = getMoveFromInput(input);
            if (playerMove == null || !isValidMove(board, playerMove)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            board.play(playerMove, opponent);

            if (board.isGameOver()) {
                System.out.println(board);
                if (board.evaluate(opponent) == -100) {
                    System.out.println(opponent + " (Player) wins!");
                } else {
                    System.out.println("It's a draw!");
                }
                break;
            }

            System.out.println("AI is thinking...");
            Move aiMoveAB = ai.getNextMoveAB(board).get(0);
            board.play(aiMoveAB, cpuPlayer);
            //utilize alphabeta au lieu de minimax
            //Move aiMove = ai.getNextMoveMinMax(board).get(0);
            //board.play(aiMove, cpuPlayer);
            System.out.println("AI played: " + (aiMoveAB.getRow() * 3 + aiMoveAB.getCol() + 1));
        }
        scanner.close();
    }

    private static Move getMoveFromInput(String input) {
        switch (input) {
            case "1": return new Move(0, 0);
            case "2": return new Move(0, 1);
            case "3": return new Move(0, 2);
            case "4": return new Move(1, 0);
            case "5": return new Move(1, 1);
            case "6": return new Move(1, 2);
            case "7": return new Move(2, 0);
            case "8": return new Move(2, 1);
            case "9": return new Move(2, 2);
            default: return null;
        }
    }

    private static boolean isValidMove(Board board, Move move) {
        System.out.println("Checking move: " + move.getRow() + "," + move.getCol());
        return board.getAvailableMoves().contains(move);
    }

}
