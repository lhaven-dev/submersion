package fr.lhaven.submersion.gui;

import fr.lhaven.submersion.Submersion;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.awt.*;

import static fr.lhaven.submersion.gui.MenuType.MENU_PRINCIPAL;

public class ReturnButton {
    public static void ReturnButton() {
    }
    public static void handleReturnButtonClick(Player player) {
        if(player.hasMetadata("OpenedMenu")) {
            // Récupération de la valeur de la métadonnée comme une chaîne
            String menuTypeString = player.getMetadata("OpenedMenu").get(0).asString();
            MenuType menuType = MenuType.fromMetaKey(menuTypeString);


            switch (menuType)
            {
                case CHOICE_GAMEMODE, CHOICE_MAP, CHOICE_PLAYER:
                    MenuPrincipal.MenuPrincipal(player);
                    break;
                case MENU_PRINCIPAL:
                    player.closeInventory();
                    break;
            }
        }
    }
}
