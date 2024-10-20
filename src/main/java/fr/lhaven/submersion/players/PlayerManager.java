package fr.lhaven.submersion.players;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {
    private static PlayerManager instance;
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

    public void addPlayer(Player player) {
        if (!playerDataMap.containsKey(player.getUniqueId())) {
            playerDataMap.put(player.getUniqueId(), new PlayerData(player));
        } else {
            System.out.println("L'utilisateur existe déjà : " + player.getUniqueId());
        }    }

    public PlayerData getPlayerData(UUID uuid) {
        PlayerData playerData = playerDataMap.get(uuid);
        if (playerData == null) {
            System.out.println("L'utilisateur avec l'UUID " + uuid + " n'existe pas.");
            return null; // ou lancer une exception selon tes besoins
        }

        return playerData;
    }

    public void removePlayer(UUID uuid) {
            // Vérifie si l'utilisateur existe avant de tenter de le supprimer
            if (playerDataMap.containsKey(uuid)) {
                playerDataMap.remove(uuid);
                System.out.println("L'utilisateur avec l'UUID " + uuid + " a été supprimé.");
            } else {
                System.out.println("L'utilisateur avec l'UUID " + uuid + " n'existe pas, aucune action effectuée.");
            }
        }


    public boolean isPlayerInGame(UUID uuid) {
        boolean inGame = playerDataMap.containsKey(uuid);

        // Affiche un message en fonction de la présence de l'utilisateur
        if (inGame) {
            System.out.println("L'utilisateur avec l'UUID " + uuid + " est dans le jeu.");
        } else {
            System.out.println("L'utilisateur avec l'UUID " + uuid + " n'est pas dans le jeu.");
        }

        return inGame;    }
}






