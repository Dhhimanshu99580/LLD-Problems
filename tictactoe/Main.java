package tictactoe;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(3);
        Player player1 = new Player("Player 1", Symbol.X);
        Player player2 = new Player("Player 2", Symbol.O);
        GameEngine gameEngine = new GameEngine(board, player1, player2, Status.IN_PROGRESS);
        gameEngine.start();
    }
}
