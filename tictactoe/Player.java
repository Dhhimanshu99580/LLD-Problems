package tictactoe;

import java.awt.*;

public class Player {
    String name;
    Moves move;
    public Player(String name, Moves move) {
        this.name = name;
        this.move = move;
    }

    public MoveType getMoveType() {
        return move.type;
    }
}
