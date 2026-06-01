package model;

import java.util.Random;

public class AIPlayer extends GameEntity {

    private Random random;

    public AIPlayer(char mark) {
        super("Computer", mark);
        this.random = new Random();
    }

    @Override
    public int[] makeMove(Board board) {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!board.isEmpty(row, col));
        return new int[]{row, col};
    }
}