package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.players.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.Bukkit.getLogger;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        getLogger().info("Event triggered PlayerJoinEvent");
        getLogger().info("Joueur " + player.getName());
        PlayerManager.getInstance().addPlayer(player);
    }
}
