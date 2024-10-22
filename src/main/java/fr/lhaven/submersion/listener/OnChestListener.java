package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.caisse.CaisseManager;
import fr.lhaven.submersion.caisse.TypeCaisse;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnChestListener implements Listener {

    CaisseManager caisseManager;
    @EventHandler
    public void onChestOpen(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        CaisseManager caisseManager = CaisseManager.getInstance();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = event.getClickedBlock();
            if (b.getType() == Material.CHEST) {
                p.sendMessage("Vous avez ouvert un coffre !");
                caisseManager.getLoot(TypeCaisse.COMMON,p);
                b.setType(Material.AIR);
            }
            if (b.getType() == Material.TRAPPED_CHEST) {
                p.sendMessage("Vous avez ouvert un Trapped Chest !");
                caisseManager.getLoot(TypeCaisse.RARE,p);
                // la tu lance la fonction de Larguage Coffre Rare
                b.setType(Material.AIR);
            }
            if (b.getType() == Material.ENDER_CHEST) {
                p.sendMessage("Vous avez ouvert un EnderChest !");
                caisseManager.getLoot(TypeCaisse.EPIC,p);
                // la tu lance la fonction de Larguage Coffre Epique
                b.setType(Material.AIR);
            }
        }
    }
}
