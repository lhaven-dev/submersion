package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.caisse.CaisseManager;
import fr.lhaven.submersion.caisse.TypeCaisse;
import fr.lhaven.submersion.commands.StructGenCommand;
import fr.lhaven.submersion.item.Shield;
import fr.lhaven.submersion.item.TypeShield;
import fr.lhaven.submersion.utils.Structure.StructureManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OnChestListener implements Listener {


    CaisseManager caisseManager;

    public OnChestListener() {

    }

    @EventHandler
    public void onChestOpen(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        // Vérifie si l'événement est un clic droit ou gauche sur un bloc
        ItemStack itemInHand = p.getInventory().getItemInMainHand();

        if (itemInHand.getType() == Material.PAPER) {
            Shield.getInstance().UseShield(p, getShieldType(itemInHand));
            return;  // Ignore si ce n'est pas du papier
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                SelectStructure(p,event);

            }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = event.getClickedBlock();
            clickChest(p, b);
        }

        }


    private void clickChest(Player p, Block b) {
        CaisseManager caisseManager = CaisseManager.getInstance();
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
private void SelectStructure(Player p , PlayerInteractEvent event)
{
    // Vérifie si le joueur tient une pelle en or
    ItemStack itemInHand = p.getInventory().getItemInMainHand();
    if (itemInHand.getType() == Material.GOLDEN_SHOVEL) {
        Location clickedLocation = event.getClickedBlock().getLocation();

        // Clic droit : définit le coin 1 (coin gauche)
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            StructureManager.getInstance().setCorner1(clickedLocation);
            p.sendMessage("Coin gauche sélectionné : " + clickedLocation);
        }

        // Clic gauche : définit le coin 2 (coin droit)
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            StructureManager.getInstance().setCorner2(clickedLocation);
            p.sendMessage("Coin droit sélectionné : " + clickedLocation);
        }

    }
}
    private TypeShield getShieldType(ItemStack item) {
        if (item.isSimilar(Shield.getInstance().getSmallShield())) {
            System.out.println("Vous avez un shield SMALL !"  );
            return TypeShield.SMALL;
        } else if (item.isSimilar(Shield.getInstance().getMediumShield())) {
            System.out.println("Vous avez un shield MEDIUM !"  );
            return TypeShield.MEDIUM;
        } else if (item.isSimilar(Shield.getInstance().getBigShield())) {
            System.out.println("Vous avez un shield LARGE !"  );
            return TypeShield.LARGE;
        }
        return null; // Retourne null si aucun type ne correspond
    }
}