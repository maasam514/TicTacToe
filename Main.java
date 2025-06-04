/**
 * Main class for running a console-based Tic-Tac-Toe game with a human player vs. an AI (CPUPlayer).
 * The game uses a 3x3 board and allows the player to input moves using numbers 1-9.
 * The AI uses alpha-beta pruning to select its moves.
 *  @author Liza Benkadoum
 *  @author Mohammadsam Karimi
 */
public class Main {

    /**
     * Main entry point for the Tic-Tac-Toe game.
     */
    public static void main(String[] args) {
        Board board = new Board();
        board.startGame();
    }
}