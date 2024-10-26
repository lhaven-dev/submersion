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

import static fr.lhaven.submersion.gui.MenuType.CHOICE_GAMEMODE;
import static fr.lhaven.submersion.gui.MenuType.CHOICE_PLAYER;

public class ChoiceGamemode {

public static void ChoiceGamemode(Player player) {
    Inventory inventory = Bukkit.createInventory(player, 9*3, "Choix du mode de jeu");

    ItemStack teamvsall = new ItemStack(Material.RED_BANNER);
    ItemMeta teamvsallMeta = teamvsall.getItemMeta();
    teamvsallMeta.setDisplayName("Team vs All");
    teamvsall.setItemMeta(teamvsallMeta);


    ItemStack battleRoyale = new ItemStack(Material.DIAMOND_SWORD);
    ItemMeta battleRoyaleMeta = battleRoyale.getItemMeta();
    battleRoyaleMeta.setDisplayName("Battle Royale");
    battleRoyale.setItemMeta(battleRoyaleMeta);

    ItemStack teamvsteam = new ItemStack(Material.IRON_SWORD);
    ItemMeta teamvsteamMeta = teamvsteam.getItemMeta();
    teamvsteamMeta.setDisplayName("Team vs Team");
    teamvsteam.setItemMeta(teamvsteamMeta);

    ItemStack Retour = new ItemStack(Material.RED_WOOL);
    ItemMeta RetourMeta = Retour.getItemMeta();
    RetourMeta.setDisplayName("Retour");
    Retour.setItemMeta(RetourMeta);

    inventory.setItem(11, teamvsall);
    inventory.setItem(13, battleRoyale);
    inventory.setItem(15, teamvsteam);
    inventory.setItem(26, Retour);
    player.closeInventory();
    player.setMetadata("OpenedMenu", new FixedMetadataValue(Submersion.getPlugin(Submersion.class), CHOICE_GAMEMODE.getMetaKey()));
    player.openInventory(inventory);
}

public static void handleChoiceGamemodeClick(Player player, int slot) {
    switch (slot) {
        case 11:
            GameManager.getInstance().createGame();
            GameManager.getInstance().setGameMode("Battle Royale");
            ChoiceMap.ChoiceMap(player);
            break;

        case 15:
            // Action pour le slot 15
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
