package model;

public class Player extends GameEntity {

    public Player(String name, char mark) {
        super(name, mark);
    }

    @Override
    public int[] makeMove(Board board) {
        return null;
    }
}