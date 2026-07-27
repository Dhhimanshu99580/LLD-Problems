package tictactoe;


public class Cell {
    private Symbol symbol;

    public boolean isEmpty() {
      return symbol==null;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
    public Symbol getSymbol() {
        return symbol;
    }
}
