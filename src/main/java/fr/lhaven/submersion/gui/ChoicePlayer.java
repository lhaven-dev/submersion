package fr.lhaven.submersion.gui;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.game.GameManager;
import fr.lhaven.submersion.players.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Collections;

import static fr.lhaven.submersion.gui.MenuType.CHOICE_PLAYER;

public class ChoicePlayer {

    public static void ChoicePlayer(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9 * 6, "Choix des joueurs");

        for (Player p : Bukkit.getOnlinePlayers()) {
            ItemStack playerItem = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta playerItemMeta = (SkullMeta) playerItem.getItemMeta();
            if (playerItemMeta != null) {
                playerItemMeta.setOwningPlayer(p);
                playerItemMeta.setDisplayName(p.getName());
                playerItemMeta.setLore(Collections.singletonList("Spectateur : false"));

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

        ItemStack item = openInventory.getItem(slot);

        // Gestion du bouton retour
        if (item.getType() == Material.RED_WOOL) {
            ReturnButton.handleReturnButtonClick(player);
        }

        if (item == null) return;
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        if (skullMeta == null || skullMeta.getOwningPlayer() == null) return;

        Player targetPlayer = skullMeta.getOwningPlayer().getPlayer();

        if (targetPlayer != null) {
            // Récupérer la description pour déterminer l'état du joueur
            String lore = skullMeta.getLore() != null && !skullMeta.getLore().isEmpty() ? skullMeta.getLore().get(0) : "";

            // Si le joueur est en mode spectateur
            if (lore.equals("Spectateur : true")) {
                if (clickType.isLeftClick()) {
                    // Redevenir joueur normal
                    player.sendMessage(targetPlayer.getName() + " n'est plus en mode spectateur.");
                    GameManager.getInstance().removeSpecator(targetPlayer);

                    // Mettre à jour la tête avec le nouvel état
                    skullMeta.setLore(Collections.singletonList("Spectateur : false"));
                    item.setItemMeta(skullMeta);
                }
            }
            // Si le joueur est en mode normal
            else {
                if (clickType.isRightClick()) {
                    // Devenir spectateur
                    player.sendMessage(targetPlayer.getName() + " est maintenant en mode spectateur.");
                    GameManager.getInstance().setSpectator(targetPlayer);

                    // Mettre à jour la tête avec le nouvel état
                    skullMeta.setLore(Collections.singletonList("Spectateur : true"));
                    item.setItemMeta(skullMeta);
                }
            }
        }
    }
}