package chessgame;

public class Piece {
    private TypeOfPiece type;
    private Player owner;
    private Color color;
    public Piece(TypeOfPiece type, Player owner,Color color) {
        this.type = type;
        this.owner = owner;
        this.color = color;
    }
    public TypeOfPiece getType() {
        return type;
    }
    public Player getOwner() {
        return owner;
    }
    public Color getColor() {
        return color;
    }

}
