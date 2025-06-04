import java.util.ArrayList;

/**
 * Represents a Tic-Tac-Toe board.
 * @author Liza Benkadoum
 * @author Mohammadsam Karimi
 */
class Board
{

    private Mark[][] board;

    /**
     * Constructs a new empty 3x3 board.
     */
    public Board() {
        board = new Mark[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Mark.EMPTY;
            }
        }
    }

    /**
     * Places the specified mark on the board at the position specified by the move.
     *
     * @param m the move specifying the position
     * @param mark the mark to place (X or O)
     */
    public void play(Move m, Mark mark){
        this.board[m.getRow()][m.getCol()] = mark;
    }

    /**
     * Evaluates the board for the specified mark.
     *
     * @param mark the mark to evaluate for (X or O)
     * @return 100 for a win, -100 for a loss, 0 for a draw, 2 otherwise
     */
    public int evaluate(Mark mark){
        int temp =0;
        //colones
        for (int i = 0; i < 3; i++) {
            temp=0;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == mark) {
                    temp++;
                }
            }
            if (temp == 3) {
                if (mark == Mark.O) {
                    return -100;
                }
                if (mark == Mark.X) {
                    return 100;
                }
            }
        }
        //lignes
        for (int i = 0; i < 3; i++) {
            temp=0;
            for (int j = 0; j < 3; j++) {
                if (board[j][i] == mark) {
                    temp++;
                }
                if (temp == 3) {
                    if (mark == Mark.O) {
                        return -100;
                    }
                    if (mark == Mark.X) {
                        return 100;
                    }
                }
            }
        }
        //diagonales
        if (board[0][0]==Mark.X &&board[1][1]== Mark.X &&board[2][2]==Mark.X
                ||board[0][2]==Mark.X && board[1][1]==Mark.X && board[2][0]==Mark.X)
        {
            return 100;
        } else if (board[0][0]==Mark.O &&board[1][1]== Mark.O &&board[2][2]==Mark.O
                ||board[0][2]==Mark.O && board[1][1]==Mark.O && board[2][0]==Mark.O) {
            return -100;
        }
        if (getAvailableMoves().isEmpty()){
            return 0;
        }
        return 2;
    }

    /**
     * Checks if the game is over (win, loss, or draw).
     *
     * @return true if the game is over, false otherwise
     */
    public Boolean isGameOver(){
        return evaluate(Mark.X) == 100 || evaluate(Mark.O) == -100 || getAvailableMoves().isEmpty();
    }

    /**
     * Checks if the specified player has won the game.
     *
     * @param player the player to check (X or O)
     * @return true if the player has won, false otherwise
     */
    public boolean hasWinner(Mark player) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    /**
     * Returns a list of all available moves on the board.
     *
     * @return an ArrayList of available moves
     */
    public ArrayList<Move> getAvailableMoves() {
        ArrayList<Move> availableMoves = new ArrayList<>();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == Mark.EMPTY) {
                    availableMoves.add(new Move(row, col));
                }
            }
        }
        return availableMoves;
    }

    /**
     * Creates a deep copy of the board.
     *
     * @return a new Board object with the same state
     */
    public Board clone(){
        Board cpyBoard = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cpyBoard.board[i][j] = this.board[i][j];
            }
        }
        return cpyBoard;
    }

    /**
     * Returns a string representation of the board.
     *
     * @return the board as a string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Mark[] row : board) {
            for (Mark cell : row) {
                sb.append(cell == Mark.EMPTY ? "." : cell).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}