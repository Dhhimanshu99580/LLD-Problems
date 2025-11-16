package chessgame;


public class Player {
    private String name;
    private String color;
    public Player(String name, String color) {
        this.name = name;
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public String getColor() {
        return color;
    }
}
