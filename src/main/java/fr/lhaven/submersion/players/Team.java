package fr.lhaven.submersion.players;

import java.util.List;

public class Team {
    private String name;
    private int score;
    private List<Player> TeamPlayers;

    private String Color;


    public Team(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public void decrementScore() {
        score--;
    }

    public void addPlayer(Player player) {
    TeamPlayers.add(player);
    }

    public void removePlayer(Player player) {
        TeamPlayers.remove(player);
}
}
