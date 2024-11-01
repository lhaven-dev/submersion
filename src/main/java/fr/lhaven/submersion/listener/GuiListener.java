package fr.lhaven.submersion.listener;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.gui.MenuType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import static fr.lhaven.submersion.gui.ChoiceScenario.handleChoiceScenarioClick;
import static fr.lhaven.submersion.gui.ChoiceMap.handleChoiceMapClick;
import static fr.lhaven.submersion.gui.ChoicePlayer.handleChoicePlayerClick;
import static fr.lhaven.submersion.gui.MenuPrincipal.handleMenuPrincipalClick;

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
// Récupération du type de menu à partir de la métadonnée
        String menuTypeString = player.getMetadata(OPENED_MENU_KEY).get(0).asString();
        MenuType menuType = MenuType.fromMetaKey(menuTypeString);


        if (menuType != null) {
            event.setCancelled(true);
            switch (menuType) {
                case CHOICE_SCENARIO:
                    handleChoiceScenarioClick(player, event.getSlot());
                    break;

                case CHOICE_MAP:
                    handleChoiceMapClick(player, event.getSlot());
                    break;

                case CHOICE_PLAYER:
                    handleChoicePlayerClick(player, event.getSlot(), event.getClick());
                    break;

                case MENU_PRINCIPAL:
                    handleMenuPrincipalClick(player, event.getSlot());
                    break;

                default:
                    player.sendMessage("Menu non reconnu." + menuType);
                    break;
            }
        } else {
            player.sendMessage("Type de menu non valide.");
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
