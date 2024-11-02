package fr.lhaven.submersion.players;

import fr.lhaven.submersion.utils.LarguageManager;
import fr.lhaven.submersion.utils.SubmersionScoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {
    private static PlayerManager instance;

    private int spectatorCount = 0;
    private int aliveCount = 0;
    private int disconnectedCount = 0;

    private Map<UUID, PlayerData> playerDataMap = new HashMap<>();

    private PlayerManager() {
        playerDataMap = new HashMap<>();
    }

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    public void setAliveCount() {
        aliveCount = 0;
        for (PlayerData playerData : playerDataMap.values()) {
            if (playerData.isAlive()) {
                aliveCount++;
            }
        }
    }

    public void setSpectatorCount() {
        spectatorCount = 0;
        for (PlayerData playerData : playerDataMap.values()) {
            if (playerData.isSpectator()) {
                spectatorCount++;
            }
        }
    }

    public void addPlayer(UUID uuid) {
        if (!playerDataMap.containsKey(uuid)) {
            playerDataMap.put(uuid, new PlayerData(uuid));
        } else {
            System.out.println("L'utilisateur existe déjà : " + playerDataMap.get(uuid).getPlayer().getName());
        }
    }

    public PlayerData getPlayerData(UUID uuid) {
        return playerDataMap.get(uuid);
    }

    public void removeSpectator(UUID uuid) {
        PlayerData data = playerDataMap.get(uuid);
        if (data != null) {
            data.setState(PlayerState.ALIVE);
            --spectatorCount;
        }
    }

    public void setAlive(UUID uuid) {
        PlayerData data = playerDataMap.get(uuid);
        if (data != null) {
            data.setState(PlayerState.ALIVE);
            ++aliveCount;
        }
    }

    public void setDead(UUID uuid) {
        PlayerData data = playerDataMap.get(uuid);
        if (data != null) {
            data.setState(PlayerState.DEAD);
            --aliveCount;
        }
        SubmersionScoreboard.getInstance().updateRemainingPlayers(aliveCount);    }

    public void setDisconnected(UUID uuid) {
        PlayerData data = playerDataMap.get(uuid);
        if (data != null) {
            data.setState(PlayerState.DISCONNECTED);
            ++disconnectedCount;
        }
    }

    public void setConnected(UUID uuid) {
        PlayerData data = playerDataMap.get(uuid);
        if (data != null) {
            data.setState(PlayerState.ALIVE); // Ou un autre état si nécessaire
            --disconnectedCount;
        }
    }

    public void addSpectator(UUID uuid) {
        PlayerData data = playerDataMap.get(uuid);
        if (data != null) {
            data.setState(PlayerState.SPECTATOR);
            ++spectatorCount;
        }
    }

    public void randomTeleportPlayers() {
        LarguageManager.getInstance().Start();
    }

    public int getAlivePlayersCount() {
        return aliveCount;
    }
}
