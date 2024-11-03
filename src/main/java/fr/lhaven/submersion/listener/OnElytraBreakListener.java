package fr.lhaven.submersion.listener;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OnElytraBreakListener implements Listener {

        @EventHandler
        public void onElytraUse(EntityToggleGlideEvent event) {
            Player player = event.getEntity().getServer().getPlayer(event.getEntity().getUniqueId());
            player.removePotionEffect(PotionEffectType.SLOW_FALLING);
            ItemStack item = player.getInventory().getChestplate();
            if (item.getType() == Material.ELYTRA && item.getDurability() == item.getType().getMaxDurability() -1) {
                player.getInventory().remove(item);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 3000, 0));
                player.sendMessage("Votre élytre s'est cassée, parachute ouvert !");
                }
        }
    }

