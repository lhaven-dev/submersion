package fr.lhaven.submersion.utils;

import fr.lhaven.submersion.players.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class DisconnectedManager {

    private static DisconnectedManager instance;
    private final HashMap<UUID, Long> disconnectedPlayers = new HashMap<>(); // Stocke les joueurs déconnectés et l'heure de déconnexion

    private DisconnectedManager() {
        startDisconnectionCheck();
    }
    public static DisconnectedManager getInstance() {
        if (instance == null) {
            instance = new DisconnectedManager();
        }
        return instance;
    }

    public void addDisconnectedPlayer(UUID playerUUID) {
        if(disconnectedPlayers.containsKey(playerUUID)) {
            return;
        }
        disconnectedPlayers.put(playerUUID, System.currentTimeMillis()); // Stocke le moment de la déconnexion
    }

    public void removeDisconnectedPlayer(UUID playerUUID) {
        if(!disconnectedPlayers.containsKey(playerUUID)) {
            return;
        }
        disconnectedPlayers.remove(playerUUID); // Enlève le joueur de la liste
    }

    private void startDisconnectionCheck() {
        new BukkitRunnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                disconnectedPlayers.forEach((playerUUID, disconnectTime) -> {
                    // Vérifie si plus de 5 minutes (300 000 ms) se sont écoulées
                    if ((currentTime - disconnectTime) >= 300_000) {
                        handlePlayerDeath(playerUUID);
                    }
                });
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("submersion"), 0, 600L); // 600 ticks = 30 secondes
    }

    private void handlePlayerDeath(UUID playerUUID) {
        disconnectedPlayers.remove(playerUUID);
        PlayerManager.getInstance().setDisconnectedDead(playerUUID);
        System.out.println("Le joueur " + playerUUID + " est passé en statut 'dead'.");
    }
}
