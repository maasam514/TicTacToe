import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class Board
{

    private Mark[][] board;

    // Ne pas changer la signature de cette méthode
    public Board() {
        board = new Mark[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Mark.EMPTY;
            }
        }
    }

    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark){
        this.board[m.getRow()][m.getCol()] = mark;
    }

    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark){
        int temp =0;
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

    public Boolean isGameOver(){
        return evaluate(Mark.X) == 100 || evaluate(Mark.O) == -100 || getAvailableMoves().isEmpty();
    }

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

    public Board clone(){
        Board cpyBoard = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cpyBoard.board[i][j] = this.board[i][j];
            }
        }
        return cpyBoard;
    }

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
