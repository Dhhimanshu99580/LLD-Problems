package tictactoe;

public class Move {
    private int row;
    private int col;
    public Move(int row,int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isValidMove(Board board) {
        return row>=0 && row<board.getSize() && col>=0 && col<board.getSize()
                && board.getCell(row, col).isEmpty();
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
}
