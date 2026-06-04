package controller;

public class Scoreboard {
    private int playerWins;
    private int computerWins;
    private int draws;

    public void addPlayerWin()   { playerWins++; }
    public void addComputerWin() { computerWins++; }
    public void addDraw()        { draws++; }

    public int getPlayerWins()   { return playerWins; }
    public int getComputerWins() { return computerWins; }
    public int getDraws()        { return draws; }

    public String getScore() {
        return "You: " + playerWins +
               "  Computer: " + computerWins +
               "  Draws: " + draws;
    }

    public void reset() {
        playerWins = 0;
        computerWins = 0;
        draws = 0;
    }
}