import java.util.ArrayList;
/**
 * Represents an AI player (CPU) for Tic-Tac-Toe using Minimax and Alpha-Beta pruning algorithms.
 * The CPU always plays as the specified Mark (typically X).
 *
 * @author Liza Benkadoum
 * @author Mohammadsam Karimi
 */

class CPUPlayer
{

    /**
     * Stores the number of nodes explored during the last Minimax or Alpha-Beta search.
     * This variable should be incremented at the start of each recursive call.
     */
    private int numExploredNodes;

    /**
     * The Mark used by the CPU player (usually X).
     */
    private final Mark cpuPlayer = Mark.X;

    /**
     * Constructs a CPUPlayer with the specified Mark.
     *
     * @param cpu the Mark representing the CPU player (X or O)
     */
    public CPUPlayer(Mark cpu) { }

    /**
     * Returns the number of nodes explored in the last search.
     *
     * @return the number of explored nodes
     */
    public int getNumOfExploredNodes() {
        return numExploredNodes;
    }

    /**
     * Returns a list of the best possible moves using the Minimax algorithm.
     * If multiple moves have the same best score, all are included.
     *
     * @param board the current game board
     * @return a list of best moves
     */
    public ArrayList<Move> getNextMoveMinMax(Board board) {
        numExploredNodes = 0;
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;
        for (Move move : board.getAvailableMoves()) {
            Board newBoard = board.clone();
            newBoard.play(move, cpuPlayer);
            numExploredNodes++;
            int score = minimax(newBoard, false);
            if (score > bestScore) {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
            } else if (score == bestScore) {
                bestMoves.add(move);
            }
        }
        return bestMoves;
    }

    /**
     * Alpha-Beta pruning algorithm to evaluate the board.
     *
     * @param board the current game board
     * @param isMaximizing true if maximizing player, false otherwise
     * @param alpha the current alpha value
     * @param beta the current beta value
     * @return the evaluated score for the board
     */
    private int alphabeta(Board board, boolean isMaximizing, int alpha, int beta) {
        if (board.hasWinner(Mark.X)) return 100;
        if (board.hasWinner(Mark.O)) return -100;
        if (board.getAvailableMoves().isEmpty()) return 0;

        numExploredNodes++;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (Move move : board.getAvailableMoves()) {
                Board newBoard = board.clone();
                newBoard.play(move, Mark.X);
                int score = alphabeta(newBoard, false, alpha, beta);
                bestScore = Math.max(bestScore, score);
                alpha = Math.max(alpha, bestScore);
                if (beta <= alpha) break;
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (Move move : board.getAvailableMoves()) {
                Board newBoard = board.clone();
                newBoard.play(move, Mark.O);
                int score = alphabeta(newBoard, true, alpha, beta);
                bestScore = Math.min(bestScore, score);
                beta = Math.min(beta, bestScore);
                if (beta <= alpha) break;
            }
            return bestScore;
        }
    }

    /**
     * Minimax algorithm to evaluate the board.
     *
     * @param board the current game board
     * @param isMaximizing true if maximizing player, false otherwise
     * @return the evaluated score for the board
     */
    private int minimax(Board board, boolean isMaximizing) {
        if (board.hasWinner(Mark.X)) return 100;
        if (board.hasWinner(Mark.O)) return -100;
        if (board.getAvailableMoves().isEmpty()) return 0;

        numExploredNodes++;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (Move move : board.getAvailableMoves()) {
                Board newBoard = board.clone();
                newBoard.play(move, Mark.X);
                int score = minimax(newBoard, false);
                bestScore = Math.max(bestScore, score);
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (Move move : board.getAvailableMoves()) {
                Board newBoard = board.clone();
                newBoard.play(move, Mark.O);
                int score = minimax(newBoard, true);
                bestScore = Math.min(bestScore, score);
            }
            return bestScore;
        }
    }

    /**
     * Returns a list of the best possible moves using the Alpha-Beta pruning algorithm.
     * If multiple moves have the same best score, all are included.
     *
     * @param board the current game board
     * @return a list of best moves
     */
    public ArrayList<Move> getNextMoveAB(Board board){
        numExploredNodes = 0;
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (Move move : board.getAvailableMoves()) {
            Board newBoard = board.clone();
            newBoard.play(move, cpuPlayer);
            numExploredNodes++;

            int score = alphabeta(newBoard, false, alpha, beta);

            if (score > bestScore) {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
                alpha = Math.max(alpha, bestScore);
            } else if (score == bestScore) {
                bestMoves.add(move);
            }
        }
        return bestMoves;
    }
}
