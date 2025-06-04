/**
 * Represents a move on a board with a row and column.
 *  @author Liza Benkadoum
 *  @author Mohammadsam Karimi
 */
class Move
{
    /** The row index of the move. */
    private int row;
    /** The column index of the move. */
    private int col;

    /**
     * Constructs a Move with default values (-1, -1).
     */
    public Move(){
        row = -1;
        col = -1;
    }

    /**
     * Constructs a Move with the specified row and column.
     * @param r the row index
     * @param c the column index
     */
    public Move(int r, int c){
        row = r;
        col = c;
    }

    /**
     * Returns the row index of the move.
     * @return the row index
     */
    public int getRow(){
        return row;
    }

    /**
     * Returns the column index of the move.
     * @return the column index
     */
    public int getCol(){
        return col;
    }

    /**
     * Sets the row index of the move.
     * @param r the new row index
     */
    public void setRow(int r){
        row = r;
    }

    /**
     * Sets the column index of the move.
     * @param c the new column index
     */
    public void setCol(int c){
        col = c;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Move move = (Move) obj;
        return row == move.row && col == move.col;
    }

}