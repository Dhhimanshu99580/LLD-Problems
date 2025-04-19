package tictactoe;

import java.sql.SQLOutput;

public class Board {
    int size;
    MoveType[][] board = new MoveType[size][size];
    public Board(int size) {
        this.size = size;
        board = new MoveType[size][size];
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                board[i][j] = null;
            }
        }
    }
    public void showAvailableSlots() {
        System.out.println(board);
    }
    public boolean isValidMove(int row, int col) {
        if(row>=0 && row<size && col>-0 && col<size) {
            if(board[row][col]==null) {
                return true;
            } else {
                System.out.println("This slot is already taken");
                return false;
            }
        }
        return true;
    }
    public boolean insertMove(int row, int col, Player player) {
        board[row][col] = player.getMoveType();
        return true;
    }
}
