package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.players.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
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

        // Vérifie si le joueur a une élytre et touche le sol
        if (player.isOnGround()) {
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() == Material.ELYTRA) {
                ItemStack boots = player.getInventory().getBoots();
                ItemStack plastron = new ItemStack(Material.LEATHER_CHESTPLATE);
                if (boots != null && boots.getType() == Material.LEATHER_BOOTS) {

                    // Récupérer les méta-données et la couleur des bottes
                    LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
                    Color color = bootsMeta.getColor();

                    // Créer un plastron en cuir avec la même couleur que les bottes
                    LeatherArmorMeta meta = (LeatherArmorMeta) plastron.getItemMeta();
                    meta.setColor(color);
                    plastron.setItemMeta(meta);
                }
                player.getInventory().setChestplate(plastron);

                player.getInventory().setChestplate(plastron);

                // Enlever l'effet de chute lente
                player.removePotionEffect(PotionEffectType.SLOW_FALLING);

                // Informer le joueur de l'équipement
                player.sendMessage("Vous avez atterri, Equipement du plastron.");

                // Mettre à jour l'état du joueur
                PlayerManager.getInstance().getPlayerData(player.getUniqueId()).setHaveLanding(true);
            }

                }
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
