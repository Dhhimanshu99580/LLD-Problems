package tictactoe;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] argss) {
        List<Player> player = new ArrayList<>();
        player.add(new Player("Himanshu",new MoveO()));
        player.add(new Player("Mansi",new MoveX()));
        Board board = new Board(5);
        System.out.println("Lets start with Himanshu");
        System.out.println("Show Available slots");
        int count=0;
        for(int i=0;i<5*5;i++) {
            count++;
            boolean mansi = false;
            if(i%2==0) {
                System.out.println("Himanshus turn");
            } else {
                mansi = true;
                System.out.println("Mansi's turn");
            }
            board.showAvailableSlots();
            System.out.println("Enter the row and column where you want to insert");
            Scanner sc = new Scanner(System.in);
            int row = sc.nextInt();
            int col = sc.nextInt();
            if(mansi&&board.isValidMove(row,col)) {
                board.insertMove(row,col,player.get(1));
            }
            if(board.isValidMove(row,col)) {
                board.insertMove(row,col,player.get(0));
            }
            if(count>=5+5-1) {
               boolean checkForHimanshu =  board.checkForWin(player.get(0));
               boolean checkForMansi =  board.checkForWin(player.get(1));
               if(checkForMansi||checkForHimanshu) break;
            }
        }


        //check validy for players to win
        //if condition is met then return true;

    }
}
