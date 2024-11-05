package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.game.GameManager;
import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.players.PlayerManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.Bukkit.getLogger;

public class OnPlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerManager.getInstance().getPlayerData(player.getUniqueId());

        if(!GameManager.getInstance().isGameStarted()) {
            if(playerData == null) {
                firstJoin(player);
            }
            reconnectedBeforeGame(player);
        }
        if(GameManager.getInstance().isGameStarted()) {
            if(playerData.isDisconnected()) {
                reconnectedPlayerDuringGame(player);
            }
            else if(playerData.isAlive()) {
                reconnectedPlayerDuringGame(player);
            }
            else if(playerData.isDead()) {
                reconnecteDeadPlayerDuringGame(player);
            }
            else if(playerData.isDisconnectedDead()) {
                reconnectedDisconnectedDeadPlayerDuringGame(player);
            }
            else
            {
                getLogger().warning("Player " + player.getName() + " has no state");
            }
        }
        }


    private void firstJoin(Player player) {
        // Téléportation du joueur au spawn
        PlayerManager.getInstance().addPlayer(player.getUniqueId());
        PlayerManager.getInstance().setConnected(player.getUniqueId());
        Location spawn = new Location(player.getWorld(), 0, 100, 0);
        player.teleport(spawn);
        player.sendMessage("Téléportation au lobby");
    }
    private void reconnectedBeforeGame(Player player) {
        PlayerManager.getInstance().setAlive(player.getUniqueId());
    }

    private void reconnectedPlayerDuringGame(Player player) {
        PlayerManager.getInstance().setConnected(player.getUniqueId());
    }

    private void reconnecteDeadPlayerDuringGame(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
    }
    private void reconnectedDisconnectedDeadPlayerDuringGame(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
    }
}
