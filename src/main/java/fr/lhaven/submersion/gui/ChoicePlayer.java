package fr.lhaven.submersion.gui;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.players.PlayerData;
import fr.lhaven.submersion.players.PlayerManager;
import fr.lhaven.submersion.game.GameManager;
import fr.lhaven.submersion.players.PlayerState;
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
import java.util.UUID;

import static fr.lhaven.submersion.gui.MenuType.CHOICE_PLAYER;

public class ChoicePlayer {

    public static void openPlayerChoiceMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9 * 6, "Choix des joueurs");

        // Récupérer tous les joueurs en ligne et ajouter leurs têtes à l'inventaire
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            System.out.println(onlinePlayer.getName());
            UUID playerUUID = onlinePlayer.getUniqueId();
            PlayerData playerData = PlayerManager.getInstance().getPlayerData(playerUUID);
            ItemStack playerItem = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta playerItemMeta = (SkullMeta) playerItem.getItemMeta();

            if (playerItemMeta != null && playerData != null) {
                System.out.println(playerData.getState());
                playerItemMeta.setOwningPlayer(onlinePlayer);
                playerItemMeta.setDisplayName(onlinePlayer.getName());
                String spectatorStatus = playerData.isSpectator() ? "Spectateur : true" : "Spectateur : false";
                playerItemMeta.setLore(Collections.singletonList(spectatorStatus));
                playerItem.setItemMeta(playerItemMeta);
                inventory.addItem(playerItem);
            }
        }

        // Création des boutons "Retour" et "Annuler"
        ItemStack retour = new ItemStack(Material.RED_WOOL);
        ItemMeta retourMeta = retour.getItemMeta();
        if (retourMeta != null) {
            retourMeta.setDisplayName("Retour");
            retour.setItemMeta(retourMeta);
        }
        inventory.setItem(inventory.getSize() - 1, retour);

        player.closeInventory();
        player.setMetadata("OpenedMenu", new FixedMetadataValue(Submersion.getPlugin(Submersion.class), CHOICE_PLAYER.getMetaKey()));
        player.openInventory(inventory);
    }

    public static void handleChoicePlayerClick(Player player, int slot, ClickType clickType) {
        Inventory openInventory = player.getOpenInventory().getTopInventory();
        ItemStack item = openInventory.getItem(slot);

        // Gestion du bouton retour
        if (item != null && item.getType() == Material.RED_WOOL) {
            ReturnButton.handleReturnButtonClick(player);
            return;
        }

        if (item == null || !(item.getItemMeta() instanceof SkullMeta)) return;

        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        Player targetPlayer = skullMeta.getOwningPlayer() != null ? skullMeta.getOwningPlayer().getPlayer() : null;

        if (targetPlayer != null) {
            // Récupérer la description pour déterminer l'état du joueur
            String lore = skullMeta.getLore() != null && !skullMeta.getLore().isEmpty() ? skullMeta.getLore().get(0) : "";

            PlayerData targetPlayerData = PlayerManager.getInstance().getPlayerData(targetPlayer.getUniqueId());

            if (lore.equals("Spectateur : true")) {
                if (clickType.isLeftClick() && targetPlayerData != null) {
                    // Redevenir joueur normal
                    player.sendMessage(targetPlayer.getName() + " n'est plus en mode spectateur.");
                    PlayerManager.getInstance().removeSpectator(targetPlayer.getUniqueId());
                    updatePlayerItemLore(item, skullMeta, false);
                }
            } else {
                if (clickType.isRightClick() && targetPlayerData != null) {
                    // Devenir spectateur
                    player.sendMessage(targetPlayer.getName() + " est maintenant en mode spectateur.");
                    PlayerManager.getInstance().addSpectator(targetPlayer.getUniqueId());
                    updatePlayerItemLore(item, skullMeta, true);
                }
            }
        }
    }

    private static void updatePlayerItemLore(ItemStack item, SkullMeta skullMeta, boolean isSpectator) {
        skullMeta.setLore(Collections.singletonList("Spectateur : " + (isSpectator ? "true" : "false")));
        item.setItemMeta(skullMeta);
    }
}
