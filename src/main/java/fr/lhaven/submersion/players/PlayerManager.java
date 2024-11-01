package fr.lhaven.submersion.players;
import fr.lhaven.submersion.utils.LarguageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerManager {
    private static PlayerManager instance;

    private int spectatorCount = 0;
    private int aliveCount = 0;
    private int disconnectedCount = 0;

    private int DeadCount = 0;
    private Map<UUID, PlayerData> playerDataMap = new HashMap<>();

    private PlayerManager() {
        playerDataMap = new HashMap<>(); // On initialise la map des Joueurs
    }

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    public void setAliveCount() {
        int aliveCount = 0;
        for (PlayerData playerData : playerDataMap.values()) {
            if (playerData.isAlive()) {
                aliveCount++;
            }
        }
        this.aliveCount = aliveCount;
    }

    public void setSpectatorCount() {
        int spectatorCount = 0;
        for (PlayerData playerData : playerDataMap.values()) {
            if (playerData.isSpectator()) {
                spectatorCount++;
            }
        }
        this.spectatorCount = spectatorCount;
    }
    public Map<UUID, PlayerData> getPlayerList() {
        if(playerDataMap == null){
            System.out.println("La liste des joueurs est vide");
        }
        else {
            return playerDataMap;
        }
        return null;
    }

    public void addPlayer(UUID uuid) {
        if (!playerDataMap.containsKey(uuid)) {
            playerDataMap.put(uuid, new PlayerData(uuid));
        } else {
            System.out.println("L'utilisateur existe déjà : " + playerDataMap.get(uuid).getPlayer().getName());
        }
    }

    public PlayerData getPlayerData(UUID uuid) {
        PlayerData playerData = playerDataMap.get(uuid);
        if (playerData == null) {
            System.out.println("L'utilisateur avec l'UUID " + uuid + " n'existe pas.");
            return null;
        }
        return playerData;
    }

    public void removePlayer(UUID uuid) {
            if (playerDataMap.containsKey(uuid)) {
                playerDataMap.remove(uuid);
                System.out.println("L'utilisateur avec l'UUID " + uuid + " a été supprimé.");
            } else {
                System.out.println("L'utilisateur avec l'UUID " + uuid + " n'existe pas, aucune action effectuée.");
            }
        }

    public void removeSpectator(UUID uuid) {
        playerDataMap.get(uuid).setSpectator(false);
        --spectatorCount;
    }

    public void SetAlive(UUID uuid) {
        playerDataMap.get(uuid).setAlive(false);
        ++aliveCount;
    }
    public void SetDead(UUID uuid) {
        playerDataMap.get(uuid).setAlive(false);
        --aliveCount;
    }

    public void SetDisconnected(UUID uuid) {
        playerDataMap.get(uuid).setDisconnected(true);
        ++disconnectedCount;
    }
    public void SetConnected(UUID uuid) {
        playerDataMap.get(uuid).setDisconnected(false);
        --disconnectedCount;
    }

    public void addSpectator(UUID uuid) {
        playerDataMap.get(uuid).setSpectator(true);
        ++spectatorCount;
    }

    public void randomTeleportPlayers() {
                LarguageManager.getInstance().Start();
    }



    public int getAlivePlayersCount() {
        return aliveCount;
    }
}






