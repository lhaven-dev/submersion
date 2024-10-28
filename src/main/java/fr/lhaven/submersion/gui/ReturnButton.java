package fr.lhaven.submersion.gui;
import org.bukkit.entity.Player;


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
