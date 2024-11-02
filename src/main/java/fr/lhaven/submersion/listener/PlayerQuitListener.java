package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.game.GameManager;
import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.players.PlayerManager;
import fr.lhaven.submersion.players.PlayerState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Récupérer le joueur qui se déconnecte
        Player player = event.getPlayer();
        // Récupérer les données du joueur dans PlayerManager
        PlayerData playerData = PlayerManager.getInstance().getPlayerData(player.getUniqueId());

        // Vérifier si les données du joueur existent
        if (playerData != null) {
            // Mettre à jour l'état du joueur à "déconnecté"
            playerData.setState(PlayerState.DISCONNECTED);
            // TODO: Gérer le cas où le joueur se déconnecte en plein jeu
        }
    }
}
