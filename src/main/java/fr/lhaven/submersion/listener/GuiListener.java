package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.game.GameManager;
import fr.lhaven.submersion.gui.ChoiceMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import static fr.lhaven.submersion.gui.ChoiceGamemode.handleChoiceGamemodeClick;
import static fr.lhaven.submersion.gui.ChoiceMap.handleChoiceMapClick;

public class GuiListener implements Listener {

    private static final String OPENED_MENU_KEY = "OpenedMenu";

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) {
            return; // Sort si ce n'est pas un joueur
        }

        // Vérifie si le joueur a la métadonnée "OpenedMenu"
        if (!player.hasMetadata(OPENED_MENU_KEY)) {
            return; // Sort si aucune métadonnée n'est présente
        }

        String menuType = player.getMetadata(OPENED_MENU_KEY).get(0).asString();
        event.setCancelled(true); // Annule l'événement pour éviter des actions indésirables

        switch (menuType) {
            case "Submersion_ChoiceGamemode":
                handleChoiceGamemodeClick(player, event.getSlot());
                break;

            case "Submersion_ChoiceMap":
                handleChoiceMapClick(player, event.getSlot());
                break;

            default:
                // Optionnel : gestion d'un menu non reconnu
                player.sendMessage("Menu non reconnu.");
                break;
        }
        }


    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (player.hasMetadata(OPENED_MENU_KEY)) {
            player.removeMetadata(OPENED_MENU_KEY, Submersion.getPlugin(Submersion.class));
            Bukkit.getLogger().info("DEBUG: Menu fermé pour " + player.getName());
        }
        }
}
