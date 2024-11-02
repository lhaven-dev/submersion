package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.players.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.Bukkit.getLogger;

public class OnPlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        getLogger().info("Joueur " + player.getName());

        // Vérifier si le joueur existe déjà dans PlayerManager
        PlayerData playerData = PlayerManager.getInstance().getPlayerData(player.getUniqueId());

        if (playerData == null) {
            // Si le joueur n'existe pas, on le crée et l'ajoute
            PlayerManager.getInstance().addPlayer(player.getUniqueId());
            playerData = PlayerManager.getInstance().getPlayerData(player.getUniqueId()); // Récupérer les données après ajout
            // Ici, vous pouvez également initialiser d'autres données si nécessaire
            player.sendMessage("Bienvenue dans le jeu !");
        } else {
            // Si le joueur existe déjà, on le marque comme "connecté"
                PlayerManager.getInstance().setConnected(player.getUniqueId());
        }
    }
}
