import java.util.Scanner;

/**
 * Main class for running a console-based Tic-Tac-Toe game with a human player vs. an AI (CPUPlayer).
 * The game uses a 3x3 board and allows the player to input moves using numbers 1-9.
 * The AI uses alpha-beta pruning to select its moves.
 *  @author Liza Benkadoum
 *  @author Mohammadsam Karimi
 */
public class Main {

    /**
     * Converts a string input (1-9) to a corresponding Move object representing a cell on the board.
     *
     * @param input the user input as a string, expected to be "1" through "9"
     * @return a Move object corresponding to the board position, or null if input is invalid
     */
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

    /**
     * Checks if a given move is valid on the current board.
     *
     * @param board the current game board
     * @param move the move to validate
     * @return true if the move is available, false otherwise
     */
    private static boolean isValidMove(Board board, Move move) {
        System.out.println("Checking move: " + move.getRow() + "," + move.getCol());
        return board.getAvailableMoves().contains(move);
    }

    /**
     * Main entry point for the Tic-Tac-Toe game.
     * Handles the game loop, user input, move validation, and AI move selection.
     *
     * @param args command-line arguments (not used)
     */
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

            System.out.print("Enter a move position [1 - 9] or 'quit' to exit: ");
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
            System.out.println("AI played: " + (aiMoveAB.getRow() * 3 + aiMoveAB.getCol() + 1));
        }
        scanner.close();
    }
}