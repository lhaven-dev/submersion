package fr.lhaven.submersion.commands;

import fr.lhaven.submersion.Submersion;
import fr.lhaven.submersion.gui.ChoiceGamemode;
import fr.lhaven.submersion.gui.ChoiceMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class CreateGameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Vous devez être un joueur pour executer cette commande");
            return true;
        }
        Player player = (Player) sender;
        ChoiceGamemode.ChoiceGamemode(player);
        return true;

    }
}
