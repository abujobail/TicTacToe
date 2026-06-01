package model;

public abstract class GameEntity {
    private String name;
    private char mark;

    public GameEntity(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() { return name; }
    public char getMark()   { return mark; }

    public abstract int[] makeMove(Board board);

    @Override
    public String toString() {
        return name + " (" + mark + ")";
    }
}