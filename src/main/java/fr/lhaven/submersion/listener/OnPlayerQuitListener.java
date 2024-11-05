package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.game.GameManager;
import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.players.PlayerManager;
import fr.lhaven.submersion.players.PlayerState;
import fr.lhaven.submersion.utils.DisconnectedManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerManager.getInstance().getPlayerData(player.getUniqueId());
        if (playerData != null) {
            if (!GameManager.getInstance().isGameStarted()) {
                disconnectedPlayerBeforeGame(player);
            } else {
                disconnectPlayerDuringGame(player);
            }
        }
    }
    private void disconnectPlayerDuringGame(Player player) {
        PlayerManager.getInstance().setDisconnected(player.getUniqueId());
        DisconnectedManager.getInstance().addDisconnectedPlayer(player.getUniqueId());
    }

    private void disconnectedPlayerBeforeGame(Player player) {
        PlayerManager.getInstance().setDisconnected(player.getUniqueId());
    }
}
