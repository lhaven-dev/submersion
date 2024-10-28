package fr.lhaven.submersion.gui;


import fr.lhaven.submersion.Submersion;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import static fr.lhaven.submersion.gui.MenuType.MENU_PRINCIPAL;


public  class MenuPrincipal {

    public static void MenuPrincipal(Player player) {

        Inventory inventory = Bukkit.createInventory(player, 9*3, "Menu Principal");

        ItemStack Gamemode = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta GamemodeMeta = Gamemode.getItemMeta();
        GamemodeMeta.setDisplayName("mode de jeu");
        Gamemode.setItemMeta(GamemodeMeta);


        ItemStack Map = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta MapMeta = Map.getItemMeta();
        MapMeta.setDisplayName("map");
        Map.setItemMeta(MapMeta);



        ItemStack Retour = new ItemStack(Material.RED_WOOL);
        ItemMeta RetourMeta = Retour.getItemMeta();
        RetourMeta.setDisplayName("Retour");
        Retour.setItemMeta(RetourMeta);

        inventory.setItem(11, Gamemode);
        inventory.setItem(15, Map);
        inventory.setItem(26, Retour);
        player.closeInventory();
        player.setMetadata("OpenedMenu", new FixedMetadataValue(Submersion.getPlugin(Submersion.class),MENU_PRINCIPAL.getMetaKey()));
        player.openInventory(inventory);
    }

    public static void handleMenuPrincipalClick(Player player, int slot) {
        switch (slot) {
            case 11:
                ChoiceGamemode.ChoiceGamemode(player);
                break;

            case 15:
                ChoiceMap.ChoiceMap(player);
                break;

            case 26:
                ReturnButton.handleReturnButtonClick(player);
                break;

            default:
                break;
        }
    }
}

