package tictactoe;

public class Board {
    private Cell[][] cells;
    private int size;

    public Board(int size) {
        this.size = size;
        cells = new Cell[size][size];
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public boolean isBoardFull() {
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                if(cells[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    public void updateCell(Move move,Symbol symbol) {
        cells[move.getRow()][move.getCol()].setSymbol(symbol);
    }
    public void printBoard() {
        for(int i=0;i<size;i++) {
           for(int j=0;j<size;j++) {
               if(cells[i][j].isEmpty()) {
                   System.out.print("-");
               }else {
                   System.out.print(cells[i][j].getSymbol());
               }
               if(j<size-1) {
                   System.out.print(" | ");
               }
           }
            System.out.println();
        }
    }
    public Cell getCell(int row,int col) {
        return cells[row][col];
    }
    public int getSize() {
        return size;
    }

}
