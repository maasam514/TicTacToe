import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer
{

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;
    private final Mark cpuPlayer = Mark.X;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu) { }

    // Ne pas changer cette méthode
    public int getNumOfExploredNodes() {
        return numExploredNodes;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
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

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
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
