package tictactoe;

import java.util.Scanner;

public class GameEngine {
    private Board board;
    private Player player1;
    private Player player2;
    private Status gameStatus;
    public GameEngine(Board board, Player player1, Player player2, Status gameStatus) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.gameStatus = gameStatus;
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        Move move;
        System.out.println("Game started between " + player1.getName() + " and " + player2.getName());
        Player currentPlayer = player1;
        while(gameStatus != Status.WIN && gameStatus != Status.DRAW) {
            board.printBoard();
            System.out.println(currentPlayer.getName() + "'s turn. Enter row and column (0-based index):");
            System.out.println("Your Symbol is " + currentPlayer.getSymbol());
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            move = new Move(row, col);
            if(move.isValidMove(board)) {
                board.updateCell(move, currentPlayer.getSymbol());
                if(checkWin(currentPlayer.getSymbol())) {
                    gameStatus = Status.WIN;
                    System.out.println(currentPlayer.getName() + " wins!");
                    System.out.println("Final Board:");
                    board.printBoard();
                } else if(board.isBoardFull()) {
                    gameStatus = Status.DRAW;
                    System.out.println("Game is a draw!");
                    System.out.println("Final Board:");
                    board.printBoard();
                } else {
                    currentPlayer = (currentPlayer == player1) ? player2 : player1;
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

    }
    private boolean checkWin(Symbol symbol) {
        int size = board.getSize();
        for(int i=0;i<size;i++) {
            int count =0;
            for(int j=0;j<size;j++) {
                if(board.getCell(i,j).getSymbol() == symbol) {
                    count++;
                }
            }
            if(count==size) {
                return true;
            }
        }
        //check column for win
        for(int j=0;j<size;j++) {
            int count =0;
            for(int i=0;i<size;i++) {
                if(board.getCell(i,j).getSymbol() == symbol) {
                    count++;
                }
            }
            if(count==size) {
                return true;
            }
        }
        //check diagonal for win
        int count =0;
        for(int i=0;i<size;i++) {
            if(board.getCell(i,i).getSymbol() == symbol) {
                count++;
            }
        }
        if(count==size) {
            return true;
        }
        count =0;
        for(int i=0;i<size;i++) {
            if(board.getCell(i,size-1-i).getSymbol() == symbol) {
                count++;
            }
        }
        return count == size;
    }

}
