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

    private final Set<Player> poisonedPlayers = new HashSet<>();

    public OnPlayerMoveListener() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getLocation().getBlock().getType() == Material.WATER) {
                        if (!poisonedPlayers.contains(player)) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0)); // Poison pendant 5 secondes
                            poisonedPlayers.add(player);
                        } else {
                            if (player.hasPotionEffect(PotionEffectType.POISON)) {
                                PotionEffect poisonEffect = player.getPotionEffect(PotionEffectType.POISON);
                                if (poisonEffect.getDuration() <= 20) { // Moins de 1 seconde restante
                                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0)); // Poison pendant 5 secondes
                                }
                            }
                        }
                    } else {
                        if (poisonedPlayers.contains(player)) {
                            poisonedPlayers.remove(player);
                        }
                    }
                }
            }
        }.runTaskTimer(Submersion.getPlugin(Submersion.class), 0, 20); // Vérifie toutes les secondes
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getBlock().getType() == Material.WATER) {
            if (!poisonedPlayers.contains(player)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0)); // Poison pendant 5 secondes
                poisonedPlayers.add(player);
            }
        } else {
            if (poisonedPlayers.contains(player)) {
                poisonedPlayers.remove(player);
            }
        }
    }
}
