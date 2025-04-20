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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
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

    public boolean checkForWin(Player player) {
        if(checkForRow(player.getMoveType())||checkForColumn(player.getMoveType())||checkForDiagonal(player.getMoveType())) {
            System.out.println(player.name + "has won the match with moveType as"+ player.getMoveType());
            return true;
        }
        return false;
    }
    private boolean checkForRow(MoveType moveType) {
        int count=0;
        for(int i=0;i<size;i++) {
            if(board[i][0]==moveType) {
                 count=1;
                for(int j=1;j<size;j++) {
                    if(board[i][j]==moveType) {
                        count++;
                    }
                }
            }
            if(count==size) return true;
        }
        return false;
    }

    private boolean checkForColumn(MoveType moveType) {
        int count=0;
        for(int i=0;i<size;i++) {
            if (board[0][i] == moveType) {
                count = 1;
                for (int j = 1; j < size; j++) {
                    if (board[j][i] == moveType) {
                        count++;
                    }
                }
            }
            if (count == size) return true;
        }
        return false;
    }

    private boolean checkForDiagonal(MoveType moveType){
        int count;
        if(board[0][0]==moveType) {
            count=1;
            for(int i=1;i<size;i++) {
                if(board[i][i]==moveType){
                    count++;
                }
            }
            if(count==size) return true;
        }
        if(board[size-1][size-1]==moveType) {
            count=1;
            for(int i=1;i<size;i++) {
                if(board[size-1][i]==moveType) {
                    count++;
                }
            }
            if(count==size) return true;
        }
        return false;
    }
}
