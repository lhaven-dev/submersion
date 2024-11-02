package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.Submersion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class OnPlayerMoveListener implements Listener {



    public class WaterEffectListener implements Listener {

        private final Set<Player> poisonedPlayers = new HashSet<>();

        // Runnable pour appliquer le poison
        public WaterEffectListener() {
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.getLocation().getBlock().getType() == Material.WATER) {
                            // Appliquer l'effet de poison si le joueur n'est pas déjà empoisonné
                            if (!poisonedPlayers.contains(player)) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1)); // Poison pendant 5 secondes
                                poisonedPlayers.add(player);
                            }
                        } else {
                            // Retirer le joueur de la liste s'il n'est plus dans l'eau
                            poisonedPlayers.remove(player);
                        }
                    }
                }
            }.runTaskTimer(Submersion.getPlugin(Submersion.class), 0, 20); // Toutes les 20 ticks (1 seconde)
        }

        @EventHandler
        public void onPlayerMove(PlayerMoveEvent event) {
            Player player = event.getPlayer();

            // Si le joueur entre dans l'eau, on applique l'effet de poison
            if (player.getLocation().getBlock().getType() == Material.WATER) {
                if (!poisonedPlayers.contains(player)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1)); // Poison pendant 5 secondes
                    poisonedPlayers.add(player);
                }
            } else {
                // Retirer le joueur de la liste s'il n'est plus dans l'eau
                poisonedPlayers.remove(player);
            }
        }
    }
}
