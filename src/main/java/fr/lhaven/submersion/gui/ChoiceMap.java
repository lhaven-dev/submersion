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



import static fr.lhaven.submersion.gui.MenuType.CHOICE_MAP;


public  class ChoiceMap {

    public static void ChoiceMap(Player player) {

        Inventory inventory = Bukkit.createInventory(player, 9*3, "Choix de la map");

        ItemStack Island = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta IslandMeta = Island.getItemMeta();
        IslandMeta.setDisplayName("Island");
        Island.setItemMeta(IslandMeta);


        ItemStack Volcano = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta VolcanoMeta = Volcano.getItemMeta();
        VolcanoMeta.setDisplayName("Volcano");
        Volcano.setItemMeta(VolcanoMeta);



        ItemStack Retour = new ItemStack(Material.RED_WOOL);
        ItemMeta RetourMeta = Retour.getItemMeta();
        RetourMeta.setDisplayName("Retour");
        Retour.setItemMeta(RetourMeta);

        inventory.setItem(11, Island);
        inventory.setItem(15, Volcano);
        inventory.setItem(26, Retour);
        player.closeInventory();
        player.setMetadata("OpenedMenu", new FixedMetadataValue(Submersion.getPlugin(Submersion.class),CHOICE_MAP.getMetaKey()));
        player.openInventory(inventory);
    }

    public static void handleChoiceMapClick(Player player, int slot) {
        switch (slot) {
            case 11:
                GameManager.getInstance().setMap("Island");
                player.sendMessage("Game map set to Island");
                ChoicePlayer.ChoicePlayer(player);
                break;

            case 15:
                GameManager.getInstance().setMap("Volcano");
                player.sendMessage("Game map set to Volcano");
                ChoicePlayer.ChoicePlayer(player);
                break;

            case 26:
                ReturnButton.handleReturnButtonClick(player);
                break;

            default:
                // Optionnel : gestion d'un slot non reconnu
                break;
        }
    }
}
