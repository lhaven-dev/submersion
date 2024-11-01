package fr.lhaven.submersion.gui;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

import static fr.lhaven.submersion.gui.MenuType.CHOICE_PLAYER;

public class ChoicePlayer {

    private static final List<Player> hiddenPlayers = new ArrayList<>();

    public static void ChoicePlayer(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9 * 6, "Choix des joueurs");

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (inventory.firstEmpty() == -1) {
                player.sendMessage("Too many players");
                break;
            }

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
        ItemMeta retourMeta = retour.getItemMeta();
        retourMeta.setDisplayName("Retour");
        retour.setItemMeta(retourMeta);
        inventory.setItem(inventory.getSize() - 1, retour);

        ItemStack undo = new ItemStack(Material.YELLOW_WOOL);
        ItemMeta undoMeta = undo.getItemMeta();
        undoMeta.setDisplayName("annuler");
        undo.setItemMeta(undoMeta);
        inventory.setItem(inventory.getSize() - 2, undo);

        player.closeInventory();
        player.setMetadata("OpenedMenu", new FixedMetadataValue(Submersion.getPlugin(Submersion.class), CHOICE_PLAYER.getMetaKey()));
        player.openInventory(inventory);
    }

    public static void handleChoicePlayerClick(Player player, int slot, ClickType clickType) {
        Inventory openInventory = player.getOpenInventory().getTopInventory();

        // Assure que l'inventaire ouvert est bien "Choix des joueurs"
        if (!"Choix des joueurs".equals(player.getOpenInventory().getTitle())) {
            return;
        }

        ItemStack item = openInventory.getItem(slot);
        if (item == null) {
            return;
        }

        // Vérification pour le bouton "Undo"
        if (item.getType() == Material.YELLOW_WOOL && "annuler".equals(item.getItemMeta().getDisplayName())) {
            if (!hiddenPlayers.isEmpty()) {
                Player lastHidden = hiddenPlayers.remove(hiddenPlayers.size() - 1);
                player.showPlayer(Submersion.getPlugin(Submersion.class), lastHidden);

                ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
                if (meta != null) {
                    meta.setOwningPlayer(lastHidden);
                    meta.setDisplayName(lastHidden.getName());
                    playerHead.setItemMeta(meta);
                    openInventory.addItem(playerHead); // Réaffiche le joueur dans l'inventaire
                }
                player.sendMessage("Undo: " + lastHidden.getName() + " est visible de nouveau.");
            } else {
                player.sendMessage("Pas de joueurs à restaurer.");
            }
            return;
        }

        if (item.getType() == Material.RED_WOOL && "Retour".equals(item.getItemMeta().getDisplayName())) {
            ReturnButton.handleReturnButtonClick(player);
        }

        // Gestion du clic sur une tête de joueur
        if (item.getType() == Material.PLAYER_HEAD) {
            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
            if (skullMeta != null && skullMeta.getOwningPlayer() != null) {
                Player targetPlayer = skullMeta.getOwningPlayer().getPlayer();

                if (targetPlayer != null) {
                    if (clickType.isLeftClick()) {
                        // Clic gauche : Mettre le joueur en mode spectateur
                        player.sendMessage(targetPlayer.getName() + " est maintenant en mode spectateur.");
                        hiddenPlayers.add(targetPlayer);
                        // Logique pour passer le joueur en spectateur
                        GameManager.getInstance().setSpectator(targetPlayer);
                    } else if (clickType.isRightClick()) {
                        // Clic droit : Ajouter le joueur à la partie
                        player.sendMessage(targetPlayer.getName() + " a été ajouté à la partie.");
                        hiddenPlayers.add(targetPlayer);
                        // Logique pour ajouter le joueur à la partie
                        GameManager.getInstance().AddToGame(targetPlayer);
                    }

                    openInventory.remove(item); // Retire la tête après action
                }
            }
        }
    }

}
