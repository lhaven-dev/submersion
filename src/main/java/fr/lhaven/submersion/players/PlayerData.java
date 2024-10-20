package fr.lhaven.submersion.players;

import fr.lhaven.submersion.players.team.Team;
import org.bukkit.entity.Player;

public class PlayerData {
    private final Player player;
    private Team team;
    private boolean alive;

    public PlayerData(Player player) {
        this.player = player;
        this.alive = true; // Le joueur commence en vie
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }
}

