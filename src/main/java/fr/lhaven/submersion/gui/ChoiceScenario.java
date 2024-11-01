package fr.lhaven.submersion.gui;

import fr.lhaven.submersion.Submersion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import static fr.lhaven.submersion.gui.MenuType.CHOICE_SCENARIO;


public class ChoiceScenario {

public static void ChoiceScenario(Player player) {
    Inventory inventory = Bukkit.createInventory(player, 9*3, "Choix du Scenario");

    ItemStack ScenarioUn = new ItemStack(Material.RED_BANNER);
    ItemMeta ScenarioUnMeta = ScenarioUn.getItemMeta();
    ScenarioUnMeta.setDisplayName("Pluie Acide");
    ScenarioUn.setItemMeta(ScenarioUnMeta);


    ItemStack ScenarioTwo = new ItemStack(Material.DIAMOND_SWORD);
    ItemMeta ScenarioTwoMeta = ScenarioTwo.getItemMeta();
    ScenarioTwoMeta.setDisplayName(" Scenario 2");
    ScenarioTwo.setItemMeta(ScenarioTwoMeta);

    ItemStack ScenarioTrois = new ItemStack(Material.IRON_SWORD);
    ItemMeta ScenarioTroisMeta = ScenarioTrois.getItemMeta();
    ScenarioTroisMeta.setDisplayName("Scenario 3");
    ScenarioTrois.setItemMeta(ScenarioTroisMeta);

    ItemStack Retour = new ItemStack(Material.RED_WOOL);
    ItemMeta RetourMeta = Retour.getItemMeta();
    RetourMeta.setDisplayName("Retour");
    Retour.setItemMeta(RetourMeta);

    inventory.setItem(11, ScenarioUn);
    inventory.setItem(13, ScenarioTwo);
    inventory.setItem(15, ScenarioTrois);
    inventory.setItem(26, Retour);
    player.closeInventory();
    player.setMetadata("OpenedMenu", new FixedMetadataValue(Submersion.getPlugin(Submersion.class), CHOICE_SCENARIO.getMetaKey()));
    player.openInventory(inventory);
}

public static void handleChoiceScenarioClick(Player player, int slot) {
    switch (slot) {
        case 11:
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
