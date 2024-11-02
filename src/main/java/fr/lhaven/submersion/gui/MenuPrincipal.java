package fr.lhaven.submersion.gui;


import fr.lhaven.submersion.Submersion;

import fr.lhaven.submersion.game.GameManager;
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
        GamemodeMeta.setDisplayName("Scenario");
        Gamemode.setItemMeta(GamemodeMeta);


        ItemStack Map = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta MapMeta = Map.getItemMeta();
        MapMeta.setDisplayName("map");
        Map.setItemMeta(MapMeta);

        ItemStack Players = new ItemStack(Material.YELLOW_BANNER);
        ItemMeta PlayersMeta = Map.getItemMeta();
        PlayersMeta.setDisplayName("Players");
        Players.setItemMeta(PlayersMeta);

        ItemStack Start = new ItemStack(Material.YELLOW_BANNER);
        ItemMeta Startmeta = Map.getItemMeta();
        Startmeta.setDisplayName("Start");
        Start.setItemMeta(Startmeta);



        ItemStack Retour = new ItemStack(Material.RED_WOOL);
        ItemMeta RetourMeta = Retour.getItemMeta();
        RetourMeta.setDisplayName("Retour");
        Retour.setItemMeta(RetourMeta);

        inventory.setItem(10, Gamemode);
        inventory.setItem(12, Map);
        inventory.setItem(14, Players);
        inventory.setItem(16, Start);
        inventory.setItem(26, Retour);
        player.closeInventory();
        player.setMetadata("OpenedMenu", new FixedMetadataValue(Submersion.getPlugin(Submersion.class),MENU_PRINCIPAL.getMetaKey()));
        player.openInventory(inventory);
    }

    public static void handleMenuPrincipalClick(Player player, int slot) {
        switch (slot) {
            case 10:
                ChoiceScenario.openChoiceScenario(player);
                break;
            case 16:
                GameManager.getInstance().startGame();
                break;
            case 12:
                ChoiceMap.openChoiceMap(player);
                break;
            case 14:
                ChoicePlayer.openPlayerChoiceMenu(player);
                break;
            case 26:
                ReturnButton.handleReturnButtonClick(player);
                break;

            default:
                break;
        }
    }
}

