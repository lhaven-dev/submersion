package fr.lhaven.submersion.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.Player;

public class OnPlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // Vérifier si le joueur est dans l'eau
        if (player.getLocation().getBlock().getType() == Material.WATER) {
            // Appliquer un effet de poison au joueur
            PotionEffect poisonEffect = new PotionEffect(PotionEffectType.POISON, 100, 0); // Durée: 5 secondes, niveau: 2
            player.addPotionEffect(poisonEffect);
        }
    }
}
