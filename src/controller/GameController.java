package controller;

import model.Board;
import model.GameEntity;
import model.Player;
import model.AIPlayer;

public class GameController {
    private Board board;
    private GameEntity player1;
    private GameEntity player2;
    private GameEntity currentPlayer;
    private boolean gameOver;

    public GameController(boolean vsAI) {
        board = new Board();
        player1 = new Player("Player 1", 'X');
        player2 = vsAI ? new AIPlayer('O') : new Player("Player 2", 'O');
        currentPlayer = player1;
        gameOver = false;
    }

    public boolean makeMove(int row, int col) {
        if (gameOver) return false;
        if (!board.placeMark(row, col, currentPlayer.getMark())) return false;
        return true;
    }

    public boolean checkWin() {
        return board.checkWin(currentPlayer.getMark());
    }

    public boolean checkDraw() {
        return board.isFull();
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public GameEntity getCurrentPlayer() { return currentPlayer; }
    public Board getBoard()              { return board; }
    public boolean isGameOver()          { return gameOver; }
    public void setGameOver(boolean g)   { gameOver = g; }

    public void resetGame() {
        board.reset();
        currentPlayer = player1;
        gameOver = false;
    }

    public int[] getAIMove() {
        if (currentPlayer instanceof AIPlayer) {
            return currentPlayer.makeMove(board);
        }
        return null;
    }
}