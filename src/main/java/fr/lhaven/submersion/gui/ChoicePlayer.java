package fr.lhaven.submersion.gui;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;

import static fr.lhaven.submersion.gui.MenuType.CHOICE_PLAYER;

public class ChoicePlayer {

    public static void ChoicePlayer(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9 * 9, "Choix des joueurs");

        for (Player p : Bukkit.getOnlinePlayers()) {
            ItemStack playerItem = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta playerItemMeta = (SkullMeta) playerItem.getItemMeta();
            if (playerItemMeta != null) {
                playerItemMeta.setOwningPlayer(p);
                playerItemMeta.setDisplayName(p.getName());
                playerItem.setItemMeta(playerItemMeta);
                inventory.addItem(playerItem);
            }
        }

        ItemStack retour = new ItemStack(Material.RED_WOOL);
        retour.getItemMeta().setDisplayName("Retour");
        inventory.setItem(inventory.getSize() - 1, retour);

        ItemStack undo = new ItemStack(Material.YELLOW_WOOL);
        undo.getItemMeta().setDisplayName("Annuler");
        inventory.setItem(inventory.getSize() - 2, undo);

        player.closeInventory();
        player.setMetadata("OpenedMenu", new FixedMetadataValue(Submersion.getPlugin(Submersion.class), CHOICE_PLAYER.getMetaKey()));
        player.openInventory(inventory);
    }

    public static void handleChoicePlayerClick(Player player, int slot, ClickType clickType) {
        Inventory openInventory = player.getOpenInventory().getTopInventory();

        if (!"Choix des joueurs".equals(player.getOpenInventory().getTitle())) return;

        ItemStack item = openInventory.getItem(slot);
        if (item == null) return;

        if (item.getType() == Material.PLAYER_HEAD) {
            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
            if (skullMeta != null && skullMeta.getOwningPlayer() != null) {
                Player targetPlayer = skullMeta.getOwningPlayer().getPlayer();

                if (targetPlayer != null) {
                    if (clickType.isLeftClick()) {
                        player.sendMessage(targetPlayer.getName() + " est maintenant en mode spectateur.");

                        // Passe le joueur en spectateur et remplace visuellement sa tête par un bloc de barrière
                        GameManager.getInstance().setSpectator(targetPlayer);
                        ItemStack barrierItem = new ItemStack(Material.BARRIER);
                        barrierItem.getItemMeta().setDisplayName(targetPlayer.getName());
                        openInventory.setItem(slot, barrierItem); // Remplace la tête par un bloc de barrière

                    } else if (clickType.isRightClick()) {
                        player.sendMessage(targetPlayer.getName() + " a été ajouté à la partie.");
                        GameManager.getInstance().AddToGame(targetPlayer);
                        openInventory.remove(item); // Retire la tête après action
                    }
                }
            }
        }
    }
}
