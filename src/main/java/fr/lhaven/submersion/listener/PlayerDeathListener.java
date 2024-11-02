package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.players.PlayerManager;
import fr.lhaven.submersion.players.PlayerState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        // Récupérer le joueur qui est mort
        Player player = event.getEntity();

        // Récupérer les données du joueur dans PlayerManager
        PlayerData playerData = PlayerManager.getInstance().getPlayerData(player.getUniqueId());

        // Vérifier si les données du joueur existent
        if (playerData != null) {
            // Mettre à jour l'état du joueur à "mort"
            playerData.setState(PlayerState.DEAD);
        }

        // Vous pouvez également ajouter des messages ou d'autres logiques ici, si nécessaire
    }
}
