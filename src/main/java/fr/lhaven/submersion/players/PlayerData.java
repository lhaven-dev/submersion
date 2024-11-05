package fr.lhaven.submersion.players;

import fr.lhaven.submersion.utils.SubmersionScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {

    private String KilledBy = "none";

    private Player player;
    private boolean haveLanding = false;
    private PlayerState state = PlayerState.ALIVE; // État par défaut

    private int kills = 0;

    public int getKills() {
        return kills;
    }

    public void addKill() {
        kills++;
    }

    public PlayerData(UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public boolean isAlive() {
        return state == PlayerState.ALIVE;
    }

    public boolean isSpectator() {
        return state == PlayerState.SPECTATOR;
    }

    public boolean isDisconnected() {
        return state == PlayerState.DISCONNECTED;
    }



    public void setHaveLanding(boolean b) {
        haveLanding = b;
    }

    public boolean isHaveLanding() {
        return haveLanding;
    }

    public boolean isDisconnectedDead() {
        return state == PlayerState.DISCONNECTED_DEAD;
    }

    public void setKilledBy(String killedBy) {
        KilledBy = killedBy;
    }

    public String getKilledBy() {
        return KilledBy;
    }


    public boolean isDead() {
        return state == PlayerState.DEAD;
    }
}
