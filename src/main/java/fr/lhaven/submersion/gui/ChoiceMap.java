package fr.lhaven.submersion.gui;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.map.MapManager;
import fr.lhaven.submersion.map.Terrain.Island;
import fr.lhaven.submersion.map.Terrain.Volcano;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

import static fr.lhaven.submersion.gui.MenuType.CHOICE_MAP;

public class ChoiceMap {

    public static void openChoiceMap(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9 * 3, "Choix de la map");

        // Récupération de l'instance de MapManager
        MapManager mapManager = MapManager.getInstance();

        ItemStack island = createMapItem(Material.GRASS_BLOCK, "Island", mapManager.getMapName().equals("Island"));
        ItemStack volcano = createMapItem(Material.LAVA_BUCKET, "Volcano", mapManager.getMapName().equals("Volcano"));

        ItemStack retour = new ItemStack(Material.RED_WOOL);
        ItemMeta retourMeta = retour.getItemMeta();
        retourMeta.setDisplayName("Retour");
        retour.setItemMeta(retourMeta);

        inventory.setItem(11, island);
        inventory.setItem(15, volcano);
        inventory.setItem(26, retour);

        player.closeInventory();
        player.setMetadata("OpenedMenu", new FixedMetadataValue(Submersion.getPlugin(Submersion.class), CHOICE_MAP.getMetaKey()));
        player.openInventory(inventory);
    }

    private static ItemStack createMapItem(Material material, String mapName, boolean isSelected) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(mapName);

        // Ajout du lore qui indique la carte sélectionnée
        List<String> lore = new ArrayList<>();
        lore.add(isSelected ? "§aCarte actuelle sélectionnée" : "§7Cliquez pour sélectionner cette carte");
        meta.setLore(lore);

        item.setItemMeta(meta);
        return item;
    }

    public static void handleChoiceMapClick(Player player, int slot) {
        MapManager mapManager = MapManager.getInstance();

        switch (slot) {
            case 11:
                mapManager.setTerrain(new Island()); // Remplace par la classe concrète de terrain pour "Island"
                updateInventoryWithLore(player, "Island");
                break;
            case 15:
                mapManager.setTerrain(new Volcano()); // Remplace par la classe concrète de terrain pour "Volcano"
                updateInventoryWithLore(player, "Volcano");
                break;
            case 26:
                ReturnButton.handleReturnButtonClick(player);
                break;
            default:
                // Optionnel : gestion d'un slot non reconnu
                break;
        }
    }

    private static void updateInventoryWithLore(Player player, String selectedMap) {
        Inventory inventory = player.getOpenInventory().getTopInventory();

        // Met à jour le lore des items en fonction de la carte sélectionnée
        inventory.setItem(11, createMapItem(Material.GRASS_BLOCK, "Island", selectedMap.equals("Island")));
        inventory.setItem(15, createMapItem(Material.LAVA_BUCKET, "Volcano", selectedMap.equals("Volcano")));

        player.updateInventory(); // Actualise l'inventaire côté client
    }
}
