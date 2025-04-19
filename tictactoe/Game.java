package tictactoe;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] argss) {
        List<Player> player = new ArrayList<>();
        player.add(new Player("Himanshu",new MoveO()));
        player.add(new Player("Ravi",new MoveX()));
        Board board = new Board(5);
        System.out.println("Lets start with Himanshu");
        System.out.println("Show Available slots");
        board.showAvailableSlots();
        System.out.println("Enter the row and column where you want to insert");
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        if(board.isValidMove(row,col)) {
            board.insertMove(row,col,player.get(0));
        }

        //check validy for players to win
        //if condition is met then return true;

    }
}
