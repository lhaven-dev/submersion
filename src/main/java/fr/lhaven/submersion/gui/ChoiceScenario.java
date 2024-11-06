package fr.lhaven.submersion.gui;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.game.GameManager;
import fr.lhaven.submersion.scenario.ScenarioManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

import static fr.lhaven.submersion.gui.MenuType.CHOICE_SCENARIO;

public class ChoiceScenario {

    public static void openChoiceScenario(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9 * 3, "Choix du Scénario");
        ScenarioManager scenarioManager = ScenarioManager.getInstance();

        ItemStack pluieAcide = createScenarioItem(Material.RED_BANNER, "Pluie Acide",false);
        ItemStack scenarioDeux = createScenarioItem(Material.DIAMOND_SWORD, "Scénario 2", false); // Remplace par le vrai statut si nécessaire
        ItemStack scenarioTrois = createScenarioItem(Material.IRON_SWORD, "Scénario 3", false); // Remplace par le vrai statut si nécessaire

        ItemStack retour = new ItemStack(Material.RED_WOOL);
        ItemMeta retourMeta = retour.getItemMeta();
        retourMeta.setDisplayName("Retour");
        retour.setItemMeta(retourMeta);

        inventory.setItem(11, pluieAcide);
        inventory.setItem(13, scenarioDeux);
        inventory.setItem(15, scenarioTrois);
        inventory.setItem(26, retour);

        player.closeInventory();
        player.setMetadata("OpenedMenu", new FixedMetadataValue(Submersion.getPlugin(Submersion.class), CHOICE_SCENARIO.getMetaKey()));
        player.openInventory(inventory);
    }

    private static ItemStack createScenarioItem(Material material, String scenarioName, boolean isActive) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(scenarioName);

        // Ajout du lore qui indique l'état du scénario
        List<String> lore = new ArrayList<>();
        lore.add(isActive ? "§aScénario actuellement activé" : "§7Cliquez pour activer ce scénario");
        meta.setLore(lore);

        item.setItemMeta(meta);
        return item;
    }

    public static void handleChoiceScenarioClick(Player player, int slot) {
        ScenarioManager scenarioManager = ScenarioManager.getInstance();

        switch (slot) {
            case 11: // Pluie Acide
                if (GameManager.getInstance().isGameCreated()) {
                //    boolean isPluieAcideActive = scenarioManager.getScenarioStatus("Pluie acide");
                //    if (!isPluieAcideActive) {
                    //        scenarioManager.activateScenario("Pluie acide");
                   // } else {
                    //    scenarioManager.deactivateScenario("Pluie acide");
                   // }
                    // Mettre à jour l'inventaire pour refléter l'état du scénario
                    //updateInventoryWithLore(player, "Pluie Acide", scenarioManager.getScenarioStatus("Pluie acide"));
                }
                break;

            case 13:
                // Action pour le slot 13 (si nécessaire)
                break;

            case 15:
                // Action pour le slot 15 (si nécessaire)
                break;

            case 26: // Retour
                ReturnButton.handleReturnButtonClick(player);
                break;

            default:
                // Optionnel : gestion d'un slot non reconnu
                break;
        }
    }

    private static void updateInventoryWithLore(Player player, String scenarioName, boolean isActive) {
        Inventory inventory = player.getOpenInventory().getTopInventory();
        ScenarioManager scenarioManager = ScenarioManager.getInstance();

        inventory.setItem(11, createScenarioItem(Material.RED_BANNER, "Pluie Acide", scenarioName.equals("Pluie Acide") && isActive));
        inventory.setItem(13, createScenarioItem(Material.DIAMOND_SWORD, "Scénario 2", false)); // Ajuste avec l'état réel si nécessaire
        inventory.setItem(15, createScenarioItem(Material.IRON_SWORD, "Scénario 3", false)); // Ajuste avec l'état réel si nécessaire

        player.updateInventory(); // Actualise l'inventaire côté client
    }
}
