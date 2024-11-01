package fr.lhaven.submersion.players;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {
    private boolean alive = true;
    private Player player;
    private boolean isSpectator = false;

    private boolean isdisconnected = false;

    public PlayerData(UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
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

    public boolean isSpectator() {
        return this.isSpectator;
    }

    public void setSpectator(boolean spectator) {
        this.isSpectator = spectator;
    }

    public void setDisconnected(boolean disconnected) {
        this.isdisconnected = disconnected;
    }

    public boolean isdisconnected() {
        return this.isdisconnected;
    }
}

